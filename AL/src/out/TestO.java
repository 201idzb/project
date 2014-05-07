package out;

import out.OutputAdapter;

import java.sql.Timestamp;
import java.util.List;

import logmanager.Configuration;
import logmanager.Event;
import logmanager.QueueManager;
import logmanager.Event.LogLevel;


public class TestO extends Thread implements OutputAdapter 
{
	private Configuration config;
	private QueueManager queue;
	
	public TestO() { System.out.println("Utworzono Testowy Adapter Wyjœciowy"); }
	public void setupConfig(Configuration config) { this.config=config; }
	//public boolean storeEvents(List<Event> batch) { return false; }
    public String test(String tmp) { return tmp; }
    public void exec() { start(); }
    
    public void run()
    {
    	/*for (int i = 0; i < 110; i++) 
    	{
    		Timestamp tmp = null;
    		Event a=new Event(tmp, "yolo", LogLevel.INFO);
    		
    		System.out.println((queue.acceptEvent(a))?"Dodano Event":"Nie udalo sie dodac Eventu");
		}*/
    	System.out.println("Odpalono adapter zapisu");
    }
}
