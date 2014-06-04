package in;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import logmanager.Configuration;
import logmanager.QueueManager;
import logmanager.Event;

/**
 * Wejœciowy adapter zapisu
 * @author Mateusz Ratajczyk
 *
 */
public class FileInputAdapter extends Thread implements InputAdapter {
	private Configuration config;
	private QueueManager queue;

    private String data = "";
    private String[] timestamp = null;
    private String[] loglevel = null;
    private String[] details = null;
    private String[] parts;
	private Scanner scanner;
	
	public FileInputAdapter() {		
		System.out.println("Utworzono Adapter Wejsciowy"); 
	}
	public void setupConfig(final Configuration config) { 
		this.config = config;
	}
	public void connectToQueueManager(final QueueManager queue) { 
		this.queue = queue;
	}
	
    public void exec() { start(); }
    
	@Override
	public String test(final String tmp) { return null; }
    public void run() {
    	try {
			scanner = new Scanner(new File(config.getLocInput()));
			System.out.println("Odczytano sciezke do pliku!");
		} catch (FileNotFoundException e) {
			System.out.println("Blad odczytu pliku!");
		}
    	
    	//dopoki jest nastepna linia
		while (scanner.hasNextLine()) {
		    timestamp = new String[(int) config.getBatchSize()];
		    loglevel = new String[(int) config.getBatchSize()];
		    details = new String[(int) config.getBatchSize()];
			
		    for (int i = 0; i < config.getBatchSize(); ++i) {	
		    	//jezeli nie ma nastepnej linii to konczy
		    	if (!scanner.hasNextLine()) { break; } 
				data = scanner.nextLine();
				parts = data.split(" "); //wyszukiwanie spacji
				
				//jesli nie jest to pusta linia
				if (!(data.equals(""))) { 
					timestamp[i] = data.substring(1, 0 + parts[0].length() - 1);
					
					loglevel[i] = data.substring(parts[0].length() + 1,
							parts[0].length() + 1 + parts[1].length());
					
					details[i] = data.substring(parts[0].length() 
							+ parts[1].length() + 3,
							data.length());
				}
			}
		    
		    //tworzenie zdarzen
		    for (int i = 0; i < config.getBatchSize(); ++i) {
		    	if (timestamp[i] == null) { break; }
				Event a = new Event(timestamp[i], loglevel[i], details[i]);
				System.out.println((queue.acceptEvent(a)) 
					? "Dodano Event(" + timestamp[i] + "," + " " 
						+ loglevel[i] + "," + " " 
						+ details[i] + ")" 
					: "Nie udalo sie dodac Eventu(" + timestamp[i] + "," + " " 
						+ loglevel[i] + "," + " " 
						+ details[i] + ")");
			}
		}
		System.out.println("Odczytano plik!");
		scanner.close();
    }
}