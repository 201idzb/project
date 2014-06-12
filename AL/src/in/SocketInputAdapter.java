package in;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import testandexceptions.InvalidEventException;
import logmanager.Configuration;
import logmanager.Event;
import logmanager.QueueManager;

/**
 * wejœciowy adapter odczytu z TCP/IP.
 * @author Albert Ka³kowski
 */
public class SocketInputAdapter extends Thread implements InputAdapter {
    /**  Obiekt do klasy Configuration.  */
    private Configuration config;
    /**  Obiekt do klasy QueueManager.  */
    private QueueManager queue;


    /**
     * Klasa LOG przechowuje obiekt logu.
     */
public class LOG {
    /**  Zmienna do przechowywania kolumny timestamp (czas zdarzenia). */
     private Timestamp timestamp;
    /**  Zmienna do przechowywania kolumny loglevel (typ zdarzenia).  */
     private String loglevel;
    /**  Zmienna do przechowywania kolumny details (opis zdarzenia).  */
     private String details;

}
    /**
     * Metoda sluzaca do polaczenia z obiektem
     * konfiguracji za pomoca referencji z rdzenia aplikacji.
     * @param cnfg obiekt zawierajacy konfiguracje
     */
    public final void setupConfig(final Configuration cnfg) {
        this.config = cnfg;
    }

    /**
     * Metoda sluzaca do polaczenia z obiektem
     * kolejki za pomoca referencji z rdzenia aplikacji.
     * @param que referencja do kolejki
     */
    public final void connectToQueueManager(final QueueManager que) {
        this.queue = que;
    }



    /**
     * funkcja wywolania.
     */
    public final void exec() {
        start();
    }


    /**
     * testowa metoda.
     * @param tmp paramter tekstu
     * @return null nic
     */
    public final String test(final String tmp) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * metoda parsowania logu.
     * @param s linia tekstu
     * @return zwraca obiekt log
     * @throws ParseException wyjatek parsowania blednej lini
     */
    public final LOG parseLOG(final String s) throws ParseException {
       LOG lg = new LOG();
       /**  Format czasu zdarzenia.  */
       DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
       String[] parts = s.split(" ");
        Date date;
        date = (Date) df.parse((s.substring(1,
                   0 + parts[0].length() - 1)));

       lg.timestamp = new Timestamp(date.getTime());

       lg.loglevel = s.substring(parts[0].length() + 1,
               parts[0].length() + 1 + parts[1].length());

       lg.details = s.substring(parts[0].length()
               + parts[1].length() + 1 + 1 + 1
               , s.length());

       return lg;
    }

    /**
     * metoda obslugi watku.
     */
    @SuppressWarnings("resource")
    public final void run() {
        System.out.println("SocketInputAdapter aktywny");
        System.out.println("Nasluchuje na porcie :"
               + Integer.toString(config.getSocketPort()));
        LOG log = new LOG();
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(config.getSocketPort());
        } catch (IOException e1) {
            System.out.println("port : "
       + Integer.toString(config.getSocketPort())
        + " jest w uzyciu");
            System.exit(1);

        }

        while (true) {

            try {
                    Event a;
                    String inputLine;
                    Socket clientSocket = serverSocket.accept();
                    BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                          clientSocket.getInputStream()));
                    while ((inputLine = in.readLine()) != null) {
                        System.out.println(inputLine);

                        try {
                            if (!(inputLine.equals(""))) {
                                log = parseLOG(inputLine);

                            }
                                a = new Event(log.timestamp,
                                        log.loglevel,
                                        log.details);
                                while (!queue.acceptEvent(a)) {
                                    System.out.println("nie dodalo "
                                + inputLine);
                                }
                                System.out.println("dodalo " + inputLine);
                            } catch (InvalidEventException e) {
                              System.out.println("nie dodano eventu");
                            } catch (ParseException e) {
                               System.out.println("niepoprawne dane");
                            }
                    }

            } catch (IOException e) {
                System.out.println("Koniec pliku /  "
                        + "po³aczenie zerwane / Timeout");
                System.out.println(e);
            }
        }
    }

}
