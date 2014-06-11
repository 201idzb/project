package logmanager;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import testandexceptions.InvalidEventException;

/**
 * klasa QueueManager implementuje struktur� kolejki.
 * pozwalaj�c na kolejkowanie zdarze� w trybie FIFO.
 * @author Kajetan Hry�czuk
 *
 */


public class QueueManager {
/**  lista zdarzen.  */
private static List<Event> queue = new ArrayList<Event>();
/**  maksymlany rozmiar kolejki.  */
private static long maxSize = 0;
/**  aktualny rozmiar kolejki.  */
private static long actSize = 0;
/**
 * Konstruktor klasy QueueManager.
 * @param maxSizeTmp wymaga podania maksymalnej dopuszczalnej ilo�ci
 * log�w jak� mo�e przechowac kolejka
 */
public QueueManager(final long maxSizeTmp) {
System.out.println("Utworzono kolejke log�w");
QueueManager.maxSize = maxSizeTmp;
}
/**
 * Synchronizowana metoda zabezpieczj�ca kolejke
 * przed r�wnoleg�ym dostpem do niej.
 * @param addOrDel true je�li zamtierzamy dodac event, a else je�li pobrac
 * @param event je�li dodajemy event to przekazujemy tutaj
 * zdarzenie a je�li pobieramy to przekazujemy tutaj null
 * @return je�li pobieramy to zwraca zdarzenie
 * a je�li dodajemy to zawsze zwraca null
 */
private synchronized Event eventManager(final boolean addOrDel,
        final Event event) {
//dodanie eventu
if (addOrDel) {
queue.add(event);
return null;
} else {
//pobranie eventu
Event tmp = queue.remove(0);
return tmp;
}
}

/**
 * pobieranie eventow.
 * @param event zdarzenie przesy�ane do kolejki
 * @return true je�li kolejka przyjmnie zdarzenie, a false je�li nie przyjmnie
 * @throws InvalidEventException jesli zdarzenie jest bledne
 */
public final boolean acceptEvent(final Event event)
        throws InvalidEventException {
if (event == null) {
    throw new
    InvalidEventException("PROBOWANO DODAC PUSTY EVENT");
}
if (actSize < maxSize) {
eventManager(true, event);
++actSize;
return true;
} else { return false; }
}

/**
 * wysylanie eventow.
 * @return je�li kolejka zawiera logi zwr�ci zdarzenie, a je�li
 * nie zawiera zwr�ci zdarzenie zawieraj�ce opis: "pobrano pusty log"
 */
public final Event sendEvents() {
if (!queue.isEmpty()) {
Event tmp = eventManager(false, null);
--actSize;
//System.out.println(actSize);
return tmp;
} else {
Timestamp tmpTime = new Timestamp(0);
Event event = null;
try {
    event = new Event(tmpTime, "pobrano pusty log", "details");
} catch (InvalidEventException e) {
    e.printStackTrace();
}
return event;
}
}
/**
 * Metoda zwracaj�ca aktualn� ilo�c log�w w kolejce.
 * @return zwraca aktualn� ilo�c log�w przechowywan� w kolejce
 */
public final long getActSize() {
return actSize;
}

}
