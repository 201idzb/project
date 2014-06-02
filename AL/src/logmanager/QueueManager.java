package logmanager;

import java.util.ArrayList;
import java.util.List;


//na podstawie konfiguracji inicjalizuje wymagane adaptery
//oraz klase QueueManager zarz¹dzaj¹ca kolejkowaniem zdarzeñ
//nadzoruje przetwarzanie kolejki (decyduje kiedy
//zdarzenia s¹ wysy³ane do adaptera wyjœciowego). 

//klasa QueueManager implementuje strukturê kolejki 
//pozwalaj¹c na kolejkowanie zdarzeñ w trybie FIFO.

public class QueueManager {
	private static List<Event> queue = new ArrayList<Event>();
	private static long maxSize = 0;
	private static long actSize = 0;
	
	public QueueManager(final long maxSize) {
		System.out.println("Utworzono kolejke logów");
		QueueManager.maxSize = maxSize;
	}
	
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
	
	//pobieranie eventow
	public boolean acceptEvent(Event event) {
		if (actSize < maxSize) {
			eventManager(true, event);
			++actSize;
			return true;
		} else { return false; }
	}
	
	//wysylanie eventow
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
	
	public long getActSize() { 
		return actSize;
	}

	//Pierwsza metoda jest wywo³ywana przez adapter
	//wejœciowy w celu wys³ania zdarzenia do kolejki. 
	//Jesli operacja siê powiedzie, zwracana jest 
	//wartoœæ true, w innym przypadku false. 
	//Druga metoda jest wywo³ywana przez QueueManager
	//w momencie kiedy komponent podejmuje 
	//decyzjê wyrzucenia zdarzeñ z kolejki oraz
	//przes³ania ich do adaptera zapisu. 
}
