package logmanager;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import testandexceptions.InvalidEventException;

/**
 * klasa QueueManager implementuje strukturê kolejki.
 * pozwalaj¹c na kolejkowanie zdarzeñ w trybie FIFO.
 * @author Kajetan Hryñczuk
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
 * @param maxSizeTmp wymaga podania maksymalnej dopuszczalnej iloœci
 * logów jak¹ mo¿e przechowac kolejka
 */
public QueueManager(final long maxSizeTmp) {
System.out.println("Utworzono kolejke logów");
QueueManager.maxSize = maxSizeTmp;
}
/**
 * Synchronizowana metoda zabezpieczj¹ca kolejke
 * przed równoleg³ym dostpem do niej.
 * @param addOrDel true jeœli zamtierzamy dodac event, a else jeœli pobrac
 * @param event jeœli dodajemy event to przekazujemy tutaj
 * zdarzenie a jeœli pobieramy to przekazujemy tutaj null
 * @return jeœli pobieramy to zwraca zdarzenie
 * a jeœli dodajemy to zawsze zwraca null
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
 * @param event zdarzenie przesy³ane do kolejki
 * @return true jeœli kolejka przyjmnie zdarzenie, a false jeœli nie przyjmnie
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
 * @return jeœli kolejka zawiera logi zwróci zdarzenie, a jeœli
 * nie zawiera zwróci zdarzenie zawieraj¹ce opis: "pobrano pusty log"
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
 * Metoda zwracaj¹ca aktualn¹ iloœc logów w kolejce.
 * @return zwraca aktualn¹ iloœc logów przechowywan¹ w kolejce
 */
public final long getActSize() {
return actSize;
}

}
