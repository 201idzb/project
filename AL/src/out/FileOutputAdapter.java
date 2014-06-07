package out;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;

import logmanager.Configuration;
import logmanager.Event;
/**
 * wyjœciowy adapter zapisu do pliku.
 * @author Mateusz Ratajczyk
 *
 */
public class FileOutputAdapter extends Thread implements OutputAdapter {
	/**  Obiekt do klasy Configuration.  */
    private Configuration config;
    /**  Zmienna do obslugi zapisywania danych do pliku.  */
    private Writer out;
    /**  Zmienna do przechowywania linii z loga.  */
    private String tosave = "";
    
    /**  Konstruktor wypisujacy utworzenie adaptera.  */
    public FileOutputAdapter() { 
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
	public final boolean storeEvents(final List<Event> batch) { 
		//System.out.println("FileOutputAdapter - Przechwycono zdarzenia.");
		for (@SuppressWarnings("rawtypes") Iterator iterator = batch.iterator();
		iterator.hasNext();) {
			Event event = (Event) iterator.next();
			
			//dopisywanie zdarzen do string`a + na koncu linii znak [Enter]
			tosave = tosave + "{Timestamp: \"" + event.getTimestamp() 
					+ "\", Loglevel: \"" + event.getLoglevel() 
					+ "\", Details: \"" + event.getDetails() 
					+ "\"}" + System.getProperty("line.separator");
		}
		try {
			//System.out.println("Zapisuje do pliku...");
			out = new BufferedWriter(new OutputStreamWriter(
				  new FileOutputStream(config.getLocOutput())));
			out.write(tosave);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) { 
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//System.out.println("Zapisano do pliku!");
		return true; //jesli ok
	} 
}