
package testandexceptions;



import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;


/** klasa testujaca FileInputAdapter.
 * @author Mateusz Ratajczyk
*/
public class TestFileInputAdapter {
    /**  Zmienna do przechowywania kolumny timestamp (czas zdarzenia). */
    private Timestamp[] timestamp = null;
    /**  Zmienna do przechowywania kolumny loglevel (typ zdarzenia).  */
    private String[] loglevel = null;
    /**  Zmienna do przechowywania kolumny details (opis zdarzenia).  */
    private String[] details = null;
    /**  Zmienna do przechowywania kolejnych spacji w linii z loga.  */
    private String[] parts;

    // Testy akceptacyjne  - "czarnoskrzynkowe":

    // Naprawiono metode createEvents()
    //
    // Podczas testow znaleziono blad : if (timestamp[i] == null) { break; }
    // Powodowalo to, ze nie wszystkie Zdarzenia byly dodawane,
    // zamiast 32 zdarzen (z 38 linii loga) dodalo jedynie 18.
    // Zamieniono ta linijke na if (timestamp[i] != null) { ... }
    // i metoda dodawala 32 zdarzenia z 38 linii loga
    // (poprawnie, 6 linii bylo pustych)


    /** metoda testujaca
     * sprawdza czy poprawnie wycina kolumne Timestamp.
     */
    @Test
    public final void testTimestamp1() {
        timestamp = new Timestamp[1];
        parts = getLog1().split(" "); //wyszukiwanie spacji
        for (int i = 0; i < 1; ++i) {
            /**  Format czasu zdarzenia.  */
            DateFormat df = new SimpleDateFormat(
                    "yyyy-MM-dd'T'HH:mm:ss.SSS");

            Date tmp = null;
            try {
                tmp = (Date) df.parse((getLog1().substring(1,
                        0 + parts[0].length() - 1)));
                } catch (ParseException e) {
                    e.printStackTrace();
                    }
            timestamp[i] = new Timestamp(tmp.getTime());
            }
            assertEquals("Wartosci sa rowne ", timestamp[0], getTimestamp1());
    }

    /** metoda testujaca
     * sprawdza czy poprawnie wycina kolumne Timestamp.
     */
    @Test
    public final void testTimestamp2() {
        timestamp = new Timestamp[1];
        parts = getLog2().split(" "); //wyszukiwanie spacji
        for (int i = 0; i < 1; ++i) {
            /**  Format czasu zdarzenia.  */
            DateFormat df = new SimpleDateFormat(
                    "yyyy-MM-dd'T'HH:mm:ss.SSS");

            Date tmp = null;
            try {
        tmp = (Date) df.parse((getLog2().substring(1,
            0 + parts[0].length() - 1)));
            } catch (ParseException e) {
        e.printStackTrace();
        }
            timestamp[i] = new Timestamp(tmp.getTime());
    }
        assertEquals("Wartosci sa rowne ", timestamp[0], getTimestamp2());
    }

    /**
     * metoda testujaca.
    * sprawdza czy poprawnie wycina kolumne loglevel.
    */
    @Test
    public final void testLogLevel1() {
    loglevel = new String[1];
    parts = getLog1().split(" "); //wyszukiwanie spacji
    for (int i = 0; i < 1; ++i) {
        loglevel[i] = getLog1().substring(parts[0].length() + 1,
        parts[0].length() + 1 + parts[1].length());
    }
    assertEquals("Wartosci sa rowne ", loglevel[0], getLogLevel1());
    }

    /** metoda testujaca
     * sprawdza czy poprawnie wycina kolumne loglevel.
     */
    @Test
    public final void testLogLevel2() {
    loglevel = new String[1];
    parts = getLog2().split(" "); //wyszukiwanie spacji
    for (int i = 0; i < 1; ++i) {
        loglevel[i] = getLog2().substring(parts[0].length() + 1,
        parts[0].length() + 1 + parts[1].length());
    }
    assertEquals("Wartosci sa rowne ", loglevel[0], getLogLevel2());
    }

     /** metoda testujaca
     * sprawdza czy poprawnie wycina kolumne details.
     */
    @Test
    public final void testDetails1() {
    details = new String[1];
    parts = getLog1().split(" "); //wyszukiwanie spacji
    for (int i = 0; i < 1; ++i) {
        details[i] = getLog1().substring(parts[0].length()
            + parts[1].length() + 1 + 1 + 1,
            getLog1().length());
    }
    assertEquals("Wartosci sa rowne ", details[0], getDetails1());
    }

    /** metoda testujaca
     * sprawdza czy poprawnie wycina kolumne details.
     */
    @Test
    public final void testDetails2() {
    details = new String[1];
    parts = getLog2().split(" "); //wyszukiwanie spacji
    for (int i = 0; i < 1; ++i) {
        details[i] = getLog2().substring(parts[0].length()
            + parts[1].length() + 1 + 1 + 1,
            getLog2().length());
    }
    assertEquals("Wartosci sa rowne ", details[0], getDetails2());
    }


    /** metoda zwracajaca 1 linijke z loga.
     * @return linijka z loga
     */
    public final String getLog1() {
    return "(2014-05-22T07:24:45.0813) INFO"
        + "  [is.iWeb.sentinel.logic.business."
        + "Configurator.log(Configurator.java:201)]"
        + " (Thread-379544) AO sn=CPE12 Value OK :"
        + "Device.ManagementServer."
        + "ConnectionRequestUsername "
        + " user==user";
    }

    /** metoda zwracajaca 1 linijke z loga.
     * @return linijka z loga
     */
    public final String getLog2() {
    return "(2014-05-22T07:24:45.0863) INFO "
        + " [org.jboss.stdio.AbstractLoggingWriter."
        + "write(AbstractLoggingWriter.java:71)]"
        + " (default task-54) URI null";
    }

    /** metoda zwracajaca sparsowany Timestamp.
     * @return Timestamp string
     */
    public final Timestamp getTimestamp1() {
    String tmp = "2014-05-22T07:24:45.0813";
    /**  Format czasu zdarzenia.  */
    DateFormat df = new SimpleDateFormat(
        "yyyy-MM-dd'T'HH:mm:ss.SSS");
    Date tmpdata = null;
    try {
        tmpdata = (Date) df.parse(tmp);
    } catch (ParseException e) {
        e.printStackTrace();
    }
    Timestamp time = new Timestamp(tmpdata.getTime());
    return time;
    }

    /** metoda zwracajaca sparsowany Timestamp.
     * @return Timestamp string
     */
    public final Timestamp getTimestamp2() {
    String tmp = "2014-05-22T07:24:45.0863";
    /**  Format czasu zdarzenia.  */
    DateFormat df = new SimpleDateFormat(
        "yyyy-MM-dd'T'HH:mm:ss.SSS");
    Date tmpdata = null;
    try {
        tmpdata = (Date) df.parse(tmp);
    } catch (ParseException e) {
        e.printStackTrace();
    }
    Timestamp time = new Timestamp(tmpdata.getTime());
    return time;
    }

    /** metoda zwracajaca "INFO".
     * @return "INFO" string
     */
    public final String getLogLevel1() {
    return "INFO";
    }

    /** metoda zwracajaca "INFO".
     * @return "INFO" string
     */
    public final String getLogLevel2() {
    return "INFO";
    }

    /** metoda zwracajaca kolumne details.
     * @return details string
     */
    public final String getDetails1() {
    return "[is.iWeb.sentinel.logic.business."
        + "Configurator.log(Configurator.java:201)]"
        + " (Thread-379544) AO sn=CPE12 Value OK :"
        + "Device.ManagementServer."
        + "ConnectionRequestUsername "
        + " user==user";
    }

    /** metoda zwracajaca kolumne details.
     * @return details string
     */
    public final String getDetails2() {
    return "[org.jboss.stdio.AbstractLoggingWriter."
        + "write(AbstractLoggingWriter.java:71)]"
        + " (default task-54) URI null";
    }
}