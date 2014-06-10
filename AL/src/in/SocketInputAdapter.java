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

    /**  Konstruktor wypisujacy utworzenie adaptera.  */
    public SocketInputAdapter() {
        System.out.println("SocketInputAdapter aktywny , "
                + "Port: " + config.getSocketPort());
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

    /**  Format czasu zdarzenia.  */
    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
    /**  Zmienna do przechowywania linii z loga.  */
    private String data = null;
    /**  Zmienna do przechowywania kolumny timestamp (czas zdarzenia). */
    private Timestamp[] timestamp = null;
    /**  Zmienna do przechowywania kolumny loglevel (typ zdarzenia).  */
    private String[] loglevel = null;
    /**  Zmienna do przechowywania kolumny details (opis zdarzenia).  */
    private String[] details = null;
    /**  Zmienna do przechowywania kolejnych spacji w linii z loga.  */
    private String[] parts;
    /**  Zmienna do przechowywania ilosci iteracji w petli.  */
    private int iterations = 0;
    /**  Czas zdarzenia wykorzystywany do rzutowania.  */
    private Date date;

    /**
     * metoda obslugi watku.
     */
    public final void run() {

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
                    timestamp = new Timestamp[(int) config.getBatchSize()];
                    loglevel = new String[(int) config.getBatchSize()];
                    details = new String[(int) config.getBatchSize()];

                    for (int i = 0; i < config.getBatchSize(); ++i) {
                        // jezeli nie ma nastepnej linii to konczy

                        data = inputLine;
                        parts = data.split(" "); // wyszukiwanie spacji

                        // jesli nie jest to pusta linia
                        if (!(data.equals(""))) {

                            try {
                                date = (Date) df.parse((data.substring(1,
                                        0 + parts[0].length() - 1)));
                            } catch (ParseException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }

                            timestamp[i] = new Timestamp(date.getTime());

                            loglevel[i] = data.substring(parts[0].length() + 1,
                                    parts[0].length() + 1 + parts[1].length());

                            details[i] = data.substring(parts[0].length()
                                    + parts[1].length() + 1 + 1 + 1
                                    , data.length());
                        }
                    }

                    // tworzenie zdarzen
                    for (int i = 0; i < config.getBatchSize(); ++i) {
                        if (timestamp[i] == null) {
                            break;
                        }

                        Event a = new Event(timestamp[i], loglevel[i],
                                details[i]);


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
            }
        }
    }
}
