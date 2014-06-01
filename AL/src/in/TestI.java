package in;
package in;

import java.sql.Timestamp;

import logmanager.Configuration;
import logmanager.QueueManager;
import logmanager.Event;
import logmanager.Event.LogLevel;

public class TestI extends Thread implements InputAdapter 
{
    @SuppressWarnings("unused")
    private Configuration config;
    private QueueManager queue;
	
    public TestI() { System.out.println("Utworzono Testowy Adapter Wejściowy"); }
    public void setupConfig(Configuration config) { this.config=config; }
    public void connectToQueueManager(QueueManager queue) { this.queue=queue; }
    public String test(String tmp) { return tmp; }
    public void exec() { start(); }
    
    public void run()
    {
    	for (int i = 0; i < 110; i++) 
    	{
    		//Timestamp tmp = null;
    		Event a=new Event("timestamp", "logLevel", "details");
    		
    		System.out.println((queue.acceptEvent(a))?"Dodano Event":"Nie udalo sie dodac Eventu");
		}
    }
}
