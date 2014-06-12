package testandexceptions;



import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Test;
/**
 * klasa logu.
 */
public class SocketInputAdapterTest {
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
 * sprawdzenie parsowania LogLevel.
 */
    @Test
    public final void testLogLevel() {
        LOG lg = new LOG();
        String[] parts = getLOG().split(" ");
        lg.loglevel = getLOG().substring(parts[0].length() + 1,
                   parts[0].length() + 1 + parts[1].length());
        assertEquals("Wartosci sa rowne", lg.loglevel, getLogLevel());
    }


    /**
     * sprawdzenie parsowania Details.
     */
    @Test
    public final void testDetails() {
        LOG lg = new LOG();
        String[] parts = getLOG().split(" ");
         lg.details = getLOG().substring(parts[0].length()
                    + parts[1].length() + 1 + 1 + 1
                    , getLOG().length());
        assertEquals("Wartosci sa rowne", lg.details, getDetails());
    }

    /**
     * sprawdzenie parsowania Timestamp.
     */
    @Test
    public final void testTimestamp() {
        LOG lg = new LOG();
         DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            String[] parts =  getLOG().split(" ");
             Date date = null;
             try {
                date = (Date) df.parse((getLOG().substring(1,
                            0 + parts[0].length() - 1)));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            lg.timestamp = new Timestamp(date.getTime());

        assertEquals("Wartosci sa rowne", lg.timestamp, getTimestamp());
    }
    /**
     * zwraca string info.
     * @return info
     */
    public final String getLogLevel() {
        return "INFO";
    }
    /**
     * zwraca log.
     * @return log
     */
    public final String getLOG() {
        return "(2014-05-22T07:24:45.0813) INFO"
                + "  [is.iWeb.sentinel.logic.business."
                + "Configurator.log(Configurator.java:201)]"
                + " (Thread-379544) AO sn=CPE12 Value OK :"
                + "Device.ManagementServer.ConnectionRequestUsername "
                + " user==user";
    }
    /**
     * zwraca details.
     * @return details
     */
    public final String getDetails() {
        return "[is.iWeb.sentinel.logic.business."
                + "Configurator.log(Configurator.java:201)]"
                + " (Thread-379544) AO sn=CPE12 Value OK :"
                + "Device.ManagementServer.ConnectionRequestUsername "
                + " user==user";
    }
    /**
     * zwraca timestamp.
     * @return timestamp
     */
    public final Timestamp getTimestamp() {
         DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
         Date date = null;
         String s = "2014-05-22T07:24:45.0813";
         try {
            date = (Date) df.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Timestamp t = new Timestamp(date.getTime());
        return t;
    }

}

