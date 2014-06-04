package logmanager;

import java.util.ArrayList;
import java.util.List;

/**
 * klasa QueueManager implementuje struktur� kolejki 
 * pozwalaj�c na kolejkowanie zdarze� w trybie FIFO.
 * @author Kajetan Hry�czuk
 *
 */

public class QueueManager {
	private static List<Event> queue = new ArrayList<Event>();
	private static long maxSize = 0;
	private static long actSize = 0;
	/**
	 * Konstruktor klasy QueueManager
	 * @param maxSize wymaga podania maksymalnej dopuszczalnej ilo�ci log�w jak� mo�e przechowac kolejka
	 */
	public QueueManager(final long maxSize) {
		System.out.println("Utworzono kolejke log�w");
		QueueManager.maxSize = maxSize;
	}
	/**
	 * Synchronizowana metoda zabezpieczj�ca kolejke przed r�wnoleg�ym dostpem do niej
	 * @param addOrDel true je�li zamtierzamy dodac event, a else je�li pobrac
	 * @param event je�li dodajemy event to przekazujemy tutaj zdarzenie a je�li pobieramy to przekazujemy tutaj null
	 * @return je�li pobieramy to zwraca zdarzenie a je�li dodajemy to zawsze zwraca null
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
	 * @param event zdarzenie przesy�ane do kolejki
	 * @return true je�li kolejka przyjmnie zdarzenie, a false je�li nie przyjmnie
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
	 * @return je�li kolejka zawiera logi zwr�ci zdarzenie, a je�li nie zawiera zwr�ci zdarzenie zawieraj�ce opis: "pobrano pusty log"
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
	 * Metoda zwracaj�ca aktualn� ilo�c log�w w kolejce
	 * @return zwraca aktualn� ilo�c log�w przechowywan� w kolejce
	 */
	public long getActSize() { 
		return actSize;
	}

}
