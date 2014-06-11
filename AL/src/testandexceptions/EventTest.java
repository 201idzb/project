package testandexceptions;



import java.sql.Timestamp;

import logmanager.Event;



/**
 * klasa testowa dla Eventu.
 * @author Kajetan Hrynczuk
 *
 */
public class EventTest {
     /**
     * spr dla pustego timaStampa.
     * @throws Exception blad konstruktora
     */
    @org.junit.Test(expected = InvalidEventException.class)
    public final void emptyTimestamp() throws Exception {
        Timestamp tmpTime = null;
        new Event(tmpTime, "pobrano pusty log", "details");
    }
    /**
     * Spr dla pustego opisu.
     * @throws Exception blad konstruktora
     */
    @org.junit.Test(expected = InvalidEventException.class)
    public final void emptyDetails() throws Exception {
        Timestamp tmpTime = new Timestamp(0);
        new Event(tmpTime, null, "details");
    }
    /**
     * spr dla pustego loglevel.
     * @throws Exception blad konstruktora
     */
    @org.junit.Test(expected = InvalidEventException.class)
    public final void emptyLogLvl() throws Exception {
        Timestamp tmpTime = new Timestamp(0);
        new Event(tmpTime, "pobrano pusty log", null);
    }


}
