package logmanager;

import java.util.ArrayList;
import java.util.List;




//na podstawie konfiguracji inicjalizuje wymagane adaptery oraz klase QueueManager zarz�dzaj�ca kolejkowaniem zdarze�
//nadzoruje przetwarzanie kolejki (decyduje kiedy zdarzenia s� wysy�ane do adaptera wyj�ciowego). 

//lasa QueueManager implementuje struktur� kolejki pozwalaj�c na kolejkowanie zdarze� w trybie FIFO.

public class QueueManager 
{

	private static List<Event> queue = new ArrayList<Event>();
	private static long maxSize=0;
	private static long actSize=0;
	
	public QueueManager(long maxSize)
	{
		System.out.println("Utworzono kolejke log�w");
		QueueManager.maxSize=maxSize;
	}
	
	public boolean acceptEvent(Event event) //pobieranie eventow
	{
		if(actSize<maxSize)
		{
			queue.add(event);
			++actSize;
			return true;
		}
		else return false;
	}
	
	public boolean sendEvents() //wysylanie eventow
	{
		if(!queue.isEmpty())
		{
			Event tmp=queue.remove(0);
			--actSize;
			return true;
		}
		else return false; 
	}
	
	//public boolean storeEvents(List<Event> batch) { return false; }
	
	//Pierwsza metoda jest wywo�ywana przez adapter wej�ciowy w celu wys�ania zdarzenia do kolejki. 
	//Jesli operacja si� powiedzie, zwracana jest warto�� true, w innym przypadku false. 
	//Druga metoda jest wywo�ywana przez QueueManager w momencie kiedy komponent podejmuje 
	//decyzj� wyrzucenia zdarze� z kolejki oraz przes�ania ich do adaptera zapisu. 


}
