package in;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



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
     * do obslugi funkcji testowej.
     * @param tmp test
     * @return null
     */
    public final String test(final String tmp) {
        return tmp;
    }

    /**
     * funkcja wywolania.
     */
    public final void exec() {
        start();
    }

    /**
     * metoda liczaca iteracje w petli.
     * @param ind index
     * @return iterations
     */
    public final int setIterations(final int ind) {
        this.iterations = ind;
        return iterations;
    }

    /**
     * metoda ustawiajaca Stringa data.
     * @param dat data
     * @return dat
     */
    public final String setData(final String dat) {
        this.data = dat;
        return dat;
    }


    /**  Zmienna do przechowywania linii z loga.  */
    private String data = null;

    /**  Zmienna do przechowywania ilosci iteracji w petli.  */
    private int iterations = 0;

    /**
     * metoda obslugi watku.
     * @param s linia tekstu
     * @return zwraca czas w formie timestamp
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
    public final void run() {
        System.out.println("SocketInputAdapter aktywny");
        System.out.println("Nasluchuje na porcie :"
               + Integer.toString(config.getSocketPort()));
        LOG[] log = new LOG[(int) config.getBatchSize()];
        final int timeToSleep = 500;
        int portNumber = config.getSocketPort();
        while (true) {

            try (ServerSocket serverSocket = new ServerSocket(portNumber);

                    Socket clientSocket = serverSocket.accept();
                    PrintWriter out = new PrintWriter(
                            clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                          clientSocket.getInputStream()));) {
                   String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    System.out.println(inputLine);


                    for (int i = 0; i < config.getBatchSize(); ++i) {
                        // jezeli nie ma nastepnej linii to konczy

                        data = inputLine;

                        // jesli nie jest to pusta linia
                        if (!(data.equals(""))) {
                           log[i] = parseLOG(data);

                        }
                    }

                    // tworzenie zdarzen
                    for (int i = 0; i < config.getBatchSize(); ++i) {
                        if (log[i] == null) {
                            break;
                        }

                        Event a = new Event(log[i].timestamp, log[i].loglevel,
                                log[i].details);


                        while (!queue.acceptEvent(a)) {
                            sleep(timeToSleep);
                            System.out.println("nie dodano ponawianie");
                        }
                        System.out.println("dodano");

                    }
                }
                System.out.println("Odczytano plik!");

            } catch (IOException e) {
                System.out.println(
                    "Exception caught when trying to listen on port "
                    + portNumber
                    + " or listening for a connection");

                System.out.println(e.getMessage());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ParseException e) {
            System.out.println("nie poprawne dane wejsciowe");
                e.printStackTrace();
            }
        }
    }
}
