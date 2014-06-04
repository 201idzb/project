package out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import logmanager.Configuration;
import logmanager.Event;
import logmanager.QueueManager;

public class DBOutputAdapter extends Thread implements OutputAdapter {

	private Configuration config;
	@SuppressWarnings("unused")
	private QueueManager queue;
	
	public DBOutputAdapter() { 
		System.out.println("Utworzono Adapter Wyjsciowy");
	}
	public void setupConfig(final Configuration config) { 
		this.config = config;
	}
    public String test(final String tmp) { 
    	return tmp;
    }
    
    public void exec() { start(); }
    
    public void run() {
    	System.out.println("Odpalono adapter zapisu");
    	
    	//utrzymywanie watku przy zyciu
    	while (true) {
    		try {
    			Thread.sleep(500);
    			} catch (InterruptedException ex) {
    				Thread.currentThread().interrupt();
    			} 
    		}  
    }
    
    //funkcja przechwytujaca
	synchronized public boolean storeEvents(final List<Event> batch) { 
		//polaczenie z baza

		try {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://" + config.getDBHost() + ":" + config.getPort() + "/" + config.getDBName(),
				config.getDBUserName(), config.getDBPassword());
		
		//System.out.println("przechwycono eventy");
		for (@SuppressWarnings("rawtypes") Iterator iterator = batch.iterator(); iterator.hasNext();) 
		{
			Event event = (Event) iterator.next();

			//insert do bazy
			Statement st = conn.createStatement();
			st.executeUpdate("INSERT into logs VALUES(NULL, \""+ event.getTimestamp() +"\", \"" + event.getLoglevel() + "\", \"" + event.getDetails() + "\")");
			

			
			//System.out.println(event);
		}
		//disconnect z bazy

		conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true; //jesli ok
	}
}