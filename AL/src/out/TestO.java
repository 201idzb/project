package out;

import out.OutputAdapter;

import java.util.Iterator;
import java.util.List;

import logmanager.Configuration;
import logmanager.Event;
import logmanager.QueueManager;


public class TestO extends Thread implements OutputAdapter 
{
	@SuppressWarnings("unused")
	private Configuration config;
	@SuppressWarnings("unused")
	private QueueManager queue;
	
	public TestO() { System.out.println("Utworzono Testowy Adapter Wyjœciowy"); }
	public void setupConfig(Configuration config) { this.config=config; }
    public String test(String tmp) { return tmp; }
    public void exec() { start(); }
    
    public void run()
    {
    	System.out.println("Odpalono adapter zapisu");
    	
    	//utrzymywanie watku przy zyciu
    	while(true) { try { Thread.sleep(500); } catch(InterruptedException ex) {Thread.currentThread().interrupt(); } }  
    }
    
	public boolean storeEvents(List<Event> batch) //funkcja przechwytujaca
	{ 
		System.out.println("przechwycono eventy");
		for (@SuppressWarnings("rawtypes") Iterator iterator = batch.iterator(); iterator.hasNext();) 
		{
			Event event = (Event) iterator.next();
			System.out.println(event );
		}
		
		return true; //jesli ok
		//return false; //jesli nie ok
	}
    
}
