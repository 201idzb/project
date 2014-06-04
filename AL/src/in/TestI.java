package in;

import java.sql.Timestamp;

import logmanager.Configuration;
import logmanager.QueueManager;
import logmanager.Event;
/**
 * testowy adapter wejœciowy
 * @author Kajetan Hryñczuk
 *
 */
public class TestI extends Thread implements InputAdapter {
	@SuppressWarnings("unused")
	private Configuration config;
	private QueueManager queue;
	
	public TestI() { 
		System.out.println("Utworzono Testowy Adapter Wejœciowy");
	}
	
	public void setupConfig(Configuration config) { 
		this.config=config;
	}
	
	public void connectToQueueManager(QueueManager queue) { 
		this.queue=queue; 
	}
	
    public String test(String tmp) { 
    	return tmp;
    }
    
    public void exec() { start(); }
    
    public void run() {
    	//for (int i = 0; i < 110000; i++) {
    	while(true) {
    		Timestamp x=new Timestamp(500000);
    		Event a = new Event(x, "WARNING", "details");
    		System.out.println((queue.acceptEvent(a)) 
    				? "Dodano Event" 
    				: "Nie udalo sie dodac Eventu");
    		try {
    			Thread.sleep(1);
    			} catch (InterruptedException ex) {
    				Thread.currentThread().interrupt();
    			} 
    		
		}
    }
}