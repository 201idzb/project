package in;

import java.sql.Timestamp;

import testandexceptions.InvalidEventException;
import logmanager.Configuration;
import logmanager.QueueManager;
import logmanager.Event;
/**
 * testowy adapter wejœciowy.
 * @author Kajetan Hryñczuk
 *
 */
public class TestI extends Thread implements InputAdapter {
/** obiekt zawierajacy konfiguracje. */
@SuppressWarnings("unused")
private Configuration config;
/** obiekt zawierajacy kolejke. */
private QueueManager queue;

/** konstruktor. */
public TestI() {
System.out.println("Utworzono Testowy Adapter Wejœciowy");
}

/**
 * przypisanie obiektu konf.
 * @param configTmp obiekt do zapisania
 */
public final void setupConfig(final Configuration configTmp) {
this.config = configTmp;
}

/**
 * polaczenie z kolejka.
 * @param queueTmp kolejka do zapisania
 */
public final void connectToQueueManager(final QueueManager queueTmp) {
this.queue = queueTmp;
}

/**
 * funkcja testowa.
 * @param tmp testowe wejscie
 * @return wypisanie wejscia
 */
    public final String test(final String tmp) {
    return tmp;
    }

    /**
     * funkcja wzwolania.
     */
    public final void exec() { start(); }

    /**
     * funkcja uruchamiajaca.
     */
    public final void run() {
        final long q = 500000;
    //for (int i = 0; i < 110000; i++) {
    while (true) {
    Timestamp x = new Timestamp(q);
    Event a = null;
    try {
        a = new Event(x, "WARNING", "details");
    } catch (InvalidEventException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
    }
    try {
        System.out.println((queue.acceptEvent(a))
        ? "Dodano Event"
        : "Nie udalo sie dodac Eventu");
    } catch (InvalidEventException e) {
        e.printStackTrace();
    }
    try {
    Thread.sleep(1);
    } catch (InterruptedException ex) {
    Thread.currentThread().interrupt();
    }
}
    }
}