package out;

import out.OutputAdapter;

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

public class FileOutputAdapter extends Thread implements OutputAdapter 
{
	private Configuration config;
	private Writer out;
	private String tosave = "";
	public FileOutputAdapter() { System.out.println("Utworzono Adapter Wyjœciowy"); }
	public void setupConfig(Configuration config) { this.config=config; }
    public String test(String tmp) { return tmp; }
    public void exec() { start(); }

    
    public void run()
    {
    	//utrzymywanie watku przy zyciu
    	while(true) { try { Thread.sleep(500); } catch(InterruptedException ex) {Thread.currentThread().interrupt(); } }  
    }
    
	public boolean storeEvents(List<Event> batch) //funkcja przechwytujaca
	{ 
		System.out.println("FileOutputAdapter - Przechwycono zdarzenia.");
		for (@SuppressWarnings("rawtypes") Iterator iterator = batch.iterator(); iterator.hasNext();) 
		{
			Event event = (Event) iterator.next();
			
			tosave = tosave + 
					"{Timestamp: \""
			+ event.getTimestamp() + 
					"\", Loglevel: \""
			+ event.getLoglevel() + 
					"\", Details: \""
			+ event.getDetails() + 
					"\"}"
			+ System.getProperty("line.separator");
		
		}
		try {
			System.out.println("Zapisuje do pliku...");
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(config.getLocOutput())));
			out.write(tosave);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {e.printStackTrace();} 
		  catch (IOException e) {e.printStackTrace();}
		
		System.out.println("Zapisano!");
		return true; //jesli ok
		//return false; //jesli nie ok
	}
    
}
