/**
 * Pakiet logmanager
 */
package logmanager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Klasa ³aduj¹ca konfiguracje adaptera logów do programu zczytuje konfiguracje
 * aplikacji z pliku Konfiguracja przechowywana jest w objekcie typu.
 * Configuration
 * @author Kajetan Hryñczuk
 */
public class Configuration {
    /** Wejsciowy adapter. */
    private static String inputAdapter;
    /** Wyjsciowy adapter. */
    private static String outputAdapter;
    /** PLiki odczytu i zapisu . */
    private static String locInput, locOutput;
    /** Maksymlana ilosc przechowywanych logow w programie. */
    private static long maxDBSize;
    /** Rozmiar paczki logow. */
    private static long batchSize;
    /** Konfiguracja bazy danych. */
    private static String socketPort, dbPort, dbName, dbHost, dbUserName,
            dbPassword, dbTableName;

    /** podzielnik pliku konfiguracyjnego. */
    private static final String DELIMITER = ":";

    /**
     * Metoda oczyszczaja linie z komentarza.
     * @param tmpLineArg
     *            String zawierajacy linie z pliku konfiguracyjnego
     * @return parametr oczyszczony z komentarza
     */
    private String getValFromLine(final String tmpLineArg) {
        String tmpLine = tmpLineArg;
        if (tmpLine != null) {
            if (tmpLine.contains(DELIMITER)) {
                tmpLine = tmpLine.substring(tmpLine.indexOf(DELIMITER) + 1);
            }
            return tmpLine;
        } else {
            throw new RuntimeException("Plik konfiguracyjny "
                    + "zawiera niepelne dane");
        }
    }

    /**
     * Metoda oczyszczaja linie z komentarza.
     * @param tmpLineArg
     *            String zawierajacy linie z pliku konfiguracyjnego
     * @return parametr oczyszczony z komentarza
     */
    private Integer getValFromLineInInt(final String tmpLineArg) {
        String tmpLine = tmpLineArg;
        if (tmpLine != null) {
            if (tmpLine.contains(DELIMITER)) {
                tmpLine = tmpLine.substring(tmpLine.indexOf(DELIMITER) + 1);
            }
            return Integer.parseInt(tmpLine);
        } else {
            throw new RuntimeException("Plik konfiguracyjny zawiera "
                    + "niepelne dane");
        }
    }

    /**
     * Konstruktor objektu typu Configuration.
     */
    public Configuration() {
        try {

            // tworzy plik konfiguracyjny conf.dat
            FileReader confHandle = new FileReader("conf.dat");
            BufferedReader bufferReader = new BufferedReader(confHandle);

            inputAdapter = getValFromLine(bufferReader.readLine());
            outputAdapter = getValFromLine(bufferReader.readLine());
            maxDBSize = getValFromLineInInt(bufferReader.readLine());
            batchSize = getValFromLineInInt(bufferReader.readLine());
            locInput = getValFromLine(bufferReader.readLine());
            locOutput = getValFromLine(bufferReader.readLine());
            dbPort = getValFromLine(bufferReader.readLine());
            dbName = getValFromLine(bufferReader.readLine());
            dbHost = getValFromLine(bufferReader.readLine());
            dbUserName = getValFromLine(bufferReader.readLine());
            dbPassword = getValFromLine(bufferReader.readLine());
            dbTableName = getValFromLine(bufferReader.readLine());
            socketPort = getValFromLine(bufferReader.readLine());

            bufferReader.close();
        } catch (IOException ex) {
            System.out.println("ERR: nie mozna odczyt pliku "
                    + "konfiguracyjnego(" + ex + ")");
        }
    }

    // metody zwracajace zmienne z pliku konfiguracyjnego
    /**
     * Metoda zwracaj¹ca nazwe wejœciowego adaptera.
     * @return Zwraca nazwe wejœciowego adaptera
     */
    public final String getInputAdapter() {
        return inputAdapter;
    }

    /**
     * Metoda zwracaj¹ca nazwe wyjœciowego adaptera.
     * @return Zwraca nazwe wyjœciowego adaptera
     */
    public final String getOutputAdapter() {
        return outputAdapter;
    }

    /**
     * Metoda zwracaj¹ca maksymalny dostpny rozmiar bazy logów.
     * @return Zwraca maksymalny dostepny rozmiar bazy logów jak¹ mo¿e buforowac
     *         aplikacja
     */
    public final long getMaxDBSize() {
        return maxDBSize;
    }

    /**
     * Metoda zwracaj¹ca rozmiar jednej paczki danych przesy³anej przez
     * adaptery.
     * @return zwraca rozmiar jednej paczki danych przesy³anej przez adaptery
     */
    public final long getBatchSize() {
        return batchSize;
    }

    /**
     * Metoda zwracaj¹ca plik wejœciowy.
     * @return zwraca plik wejœciowy
     */
    public final String getLocInput() {
        return locInput;
    }

    /**
     * Metoda Zwracaj¹ca plik wyjœciowy.
     * @return zwraca plik wyjœciowy
     */
    public final String getLocOutput() {
        return locOutput;
    }

    /**
     * Metoda Zwracaj¹ca port sql.
     * @return zwraca port sql
     */
    public final String getDBPort() {
        return dbPort;
    }

    /**
     * Metoda Zwracaj¹ca nazwe DB.
     * @return zwraca nazwe DB
     */
    public final String getDBName() {
        return dbName;
    }

    /**
     * Metoda Zwracaj¹ca host DB.
     * @return zwraca host DB
     */
    public final String getDBHost() {
        return dbHost;
    }

    /**
     * Metoda Zwracaj¹ca uzytkownika DB.
     * @return zwraca uzytkownika DB
     */
    public final String getDBUserName() {
        return dbUserName;
    }

    /**
     * Metoda Zwracaj¹ca haslo DB.
     * @return zwraca haslo DB
     */
    public final String getDBPassword() {
        return dbPassword;
    }

    /**
     * Metoda Zwracaj¹ca tabele DB.
     *  * @return zwraca tabele DB
     */
    public final String getDBTableName() {
        return dbTableName;
    }

    /**
     * Metoda Zwracaj¹ca port wejsciowego adaptera socket.
     * @return zwraca port nasluchiwania
     */
    public final int getSocketPort() {
        return Integer.parseInt(socketPort);
    }
}