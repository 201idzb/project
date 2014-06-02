package logmanager;

import java.util.ArrayList;
import java.util.List;


//na podstawie konfiguracji inicjalizuje wymagane adaptery
//oraz klase QueueManager zarz�dzaj�ca kolejkowaniem zdarze�
//nadzoruje przetwarzanie kolejki (decyduje kiedy
//zdarzenia s� wysy�ane do adaptera wyj�ciowego). 

//klasa QueueManager implementuje struktur� kolejki 
//pozwalaj�c na kolejkowanie zdarze� w trybie FIFO.

public class QueueManager {
	private static List<Event> queue = new ArrayList<Event>();
	private static long maxSize = 0;
	private static long actSize = 0;
	
	public QueueManager(final long maxSize) {
		System.out.println("Utworzono kolejke log�w");
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

	//Pierwsza metoda jest wywo�ywana przez adapter
	//wej�ciowy w celu wys�ania zdarzenia do kolejki. 
	//Jesli operacja si� powiedzie, zwracana jest 
	//warto�� true, w innym przypadku false. 
	//Druga metoda jest wywo�ywana przez QueueManager
	//w momencie kiedy komponent podejmuje 
	//decyzj� wyrzucenia zdarze� z kolejki oraz
	//przes�ania ich do adaptera zapisu. 
}
