package logmanager;

import java.util.ArrayList;
import java.util.List;

/**
 * klasa QueueManager implementuje strukturê kolejki 
 * pozwalaj¹c na kolejkowanie zdarzeñ w trybie FIFO.
 * @author Kajetan Hryñczuk
 *
 */

public class QueueManager {
	private static List<Event> queue = new ArrayList<Event>();
	private static long maxSize = 0;
	private static long actSize = 0;
	/**
	 * Konstruktor klasy QueueManager
	 * @param maxSize wymaga podania maksymalnej dopuszczalnej iloœci logów jak¹ mo¿e przechowac kolejka
	 */
	public QueueManager(final long maxSize) {
		System.out.println("Utworzono kolejke logów");
		QueueManager.maxSize = maxSize;
	}
	/**
	 * Synchronizowana metoda zabezpieczj¹ca kolejke przed równoleg³ym dostpem do niej
	 * @param addOrDel true jeœli zamtierzamy dodac event, a else jeœli pobrac
	 * @param event jeœli dodajemy event to przekazujemy tutaj zdarzenie a jeœli pobieramy to przekazujemy tutaj null
	 * @return jeœli pobieramy to zwraca zdarzenie a jeœli dodajemy to zawsze zwraca null
	 */
	private synchronized Event eventManager(final boolean addOrDel, final Event event) {
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
	 * pobieranie eventow
	 * @param event zdarzenie przesy³ane do kolejki
	 * @return true jeœli kolejka przyjmnie zdarzenie, a false jeœli nie przyjmnie
	 */
	public boolean acceptEvent(Event event) {
		if (actSize < maxSize) {
			eventManager(true, event);
			++actSize;
			return true;
		} else { return false; }
	}
	
	/**
	 * wysylanie eventow
	 * @return jeœli kolejka zawiera logi zwróci zdarzenie, a jeœli nie zawiera zwróci zdarzenie zawieraj¹ce opis: "pobrano pusty log"
	 */
	public Event sendEvents() {
		if (!queue.isEmpty()) {
			Event tmp = eventManager(false, null);
			--actSize;
			return tmp; 
		} else { 
			Event event = new Event(null, "pobrano pusty log", null);
			return event; 
		}
	}
	/**
	 * Metoda zwracaj¹ca aktualn¹ iloœc logów w kolejce
	 * @return zwraca aktualn¹ iloœc logów przechowywan¹ w kolejce
	 */
	public long getActSize() { 
		return actSize;
	}

}
