package out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import logmanager.Configuration;
import logmanager.Event;

/**
 * wyjsciowy adapter zapisu do pliku.
 * @author Konrad B³aszczuk
 *
 */
public class DBOutputAdapter extends Thread implements OutputAdapter {
	/**  Obiekt do klasy Configuration.  */
	private Configuration config;

	/**  Konstruktor wypisujacy utworzenie adaptera.  */
	public DBOutputAdapter() { 
		System.out.println("Utworzono Adapter Wyjsciowy");
	}
	
    /**
     * Metoda sluzaca do polaczenia z obiektem
     * konfiguracji za pomoca referencji z rdzenia aplikacji.
     * @param cnfg obiekt zawierajacy konfiguracje
     */
	public final void setupConfig(final Configuration cnfg) { 
		this.config = cnfg;
	}
	
    /**
     * do obslugi funkcji testowej.
     * @param tmp test
     * @return null
     */
    public final String test(final String tmp) { 
    	return tmp;
    }
    
	 /**
     * funkcja wywolania.
     */
    public final void exec() { start(); }
    
    /**
     * metoda obslugi watku.
     */
    public final void run() {
    	System.out.println("Odpalono adapter zapisu");
    	final int timeToSleep = 500;
    	//utrzymywanie watku przy zyciu
    	while (true) {
    		try {
    			Thread.sleep(timeToSleep);
    			} catch (InterruptedException ex) {
    				Thread.currentThread().interrupt();
    			} 
    		}  
    }
    
    /**
     * metoda do przechwytywania Eventow.
     * @param batch 
     * @return true 
     */
	public final synchronized boolean storeEvents(final List<Event> batch) { 
		//polaczenie z baza

		try {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://" 
				+ config.getDBHost() 
				+ ":" + config.getPort() 
				+ "/" + config.getDBName(),
				config.getDBUserName(), config.getDBPassword());
		
		//System.out.println("przechwycono eventy");
		for (@SuppressWarnings("rawtypes") 
		Iterator iterator = batch.iterator(); 
		iterator.hasNext();) {
			Event event = (Event) iterator.next();

			//insert do bazy
			Statement st = conn.createStatement();
			st.executeUpdate("INSERT into logs VALUES(NULL, \""
					+ event.getTimestamp() 
					+ "\", \"" 
					+ event.getLoglevel() 
					+ "\", \"" 
					+ event.getDetails() 
					+ "\")");

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