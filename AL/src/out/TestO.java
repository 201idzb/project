
package out;

import java.util.Iterator;
import java.util.List;

import logmanager.Configuration;
import logmanager.Event;
import logmanager.QueueManager;
/**
 * testowy adapter zapisu.
 * @author Kajetan Hryñczuk
 *
 */
public class TestO extends Thread implements OutputAdapter {
/** obiekt zawierajacy konfiguracje. */
@SuppressWarnings("unused")
private Configuration config;
/** obiekt zawierajacy kolejke. */
@SuppressWarnings("unused")
private QueueManager queue;

/** konstruktor. */
public TestO() {
System.out.println("Utworzono Testowy Adapter Wyjœciowy");
}

/**
 * przypisanie obiektu konf.
 * @param configTmp obiekt do zapisania
 */
public final void setupConfig(final Configuration configTmp) {
this.config = configTmp;
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
    System.out.println("Odpalono adapter zapisu");

    //utrzymywanie watku przy zyciu
    while (true) {
    try {
    Thread.sleep(500);
    } catch (InterruptedException ex) {
    Thread.currentThread().interrupt();
    }
    }
    }

    /**
     * funkcja przechwytujaca.
     * @param batch lista zarzen do wyslania
     * @return true jesli odebralo false jesli nie
     */
public final boolean storeEvents(final List<Event> batch) {
System.out.println("przechwycono eventy");
for (@SuppressWarnings("rawtypes") Iterator iterator =
batch.iterator(); iterator.hasNext();) {
Event event = (Event) iterator.next();
System.out.println(event);
}

return true; //jesli ok
}
}