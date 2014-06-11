package testandexceptions;

import java.sql.Timestamp;

import logmanager.Event;
import logmanager.QueueManager;

import org.junit.Assert;


/**
 * klasa testowa dla kolejki.
 * @author Kajetan Hrynczuk
 *
 */
public class QueueTest {

    /**
     * Spr konstruktora.
     * @throws Exception  blad konstruktora
     */
    @org.junit.Test(expected = InvalidEventException.class)
    public final void shouldThrowInvalidEventException() throws Exception {
        final int x = 100;
        QueueManager queue = new QueueManager(x);
         queue.acceptEvent(null);
    }

    /**
     * Spr konstruktora i akcpetowania.
     * @throws InvalidEventException blad konstruktora
     */
    @org.junit.Test
    public final void testAdd() throws InvalidEventException {
        final int x = 100;
        final long y = 500;
        QueueManager queue = new QueueManager(x);
        Timestamp q = new Timestamp(y);
        Event a = new Event(q, "WARNING", "details");
        Assert.assertEquals(true, queue.acceptEvent(a));
    }

    /**
     * spr rozmiaru bufora.
     * @throws InvalidEventException blad konstruktora
     */
    @org.junit.Test
    public final void testBatchSize() throws InvalidEventException {
        final int batchSize = 100;
        final long y = 500;
        QueueManager queue = new QueueManager(batchSize);
        Timestamp q = new Timestamp(y);
        Event a = new Event(q, "WARNING", "details");
        for (int i = 1; i < batchSize; ++i) {
            Assert.assertEquals(true, queue.acceptEvent(a));
                }
        Assert.assertFalse(queue.acceptEvent(a));
    }


}
