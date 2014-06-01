package in;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import logmanager.Configuration;
import logmanager.QueueManager;
import logmanager.Event;

public class FileInputAdapter extends Thread implements InputAdapter 
{
    private Configuration config;
    private QueueManager queue;

    public String data = "";
    public String[] timestamp = new String[1000];
    public String[] loglevel = new String[1000];
    public String[] details = new String[1000];
    public String[] parts;
    private int iterations = 0;
    
	private Scanner scanner;
	
	public FileInputAdapter() { 
		for(int i=0; i<timestamp.length; i++) timestamp[i] = "";
		for(int i=0; i<loglevel.length; i++) loglevel[i] = "";
		for(int i=0; i<details.length; i++) details[i] = "";
		
		System.out.println("Utworzono Adapter Wejsciowy"); 
	}
	
	public void setupConfig(Configuration config) { this.config=config; }
	public void connectToQueueManager(QueueManager queue) { this.queue=queue; }
	
    public String test(String tmp) { return tmp; }
    public void exec() { start(); }
    
    public String setData(String data){
    	this.data = data;
    	return data;
    }
    
    public String getData(){
    	return data;
    }
    
    public int setIterations(int index){
    	this.iterations = index;
    	return iterations;
    }
    
    public void run()
    {
    	int index = 0;
    	try {
			scanner = new Scanner(new File(config.getLocInput()));
			System.out.println("Odczytano sciezke do pliku!");
		} catch (FileNotFoundException e) {System.out.println("B��d odczytu pliku!");}
    	
		while(scanner.hasNextLine()) {
			data = scanner.nextLine();
			parts = data.split(" ");
			
			if(!(data.equals(""))){ //jesli nie jest to pusta linia
				timestamp[index] = data.substring(1, 0 + parts[0].length()-1);
				loglevel[index] = data.substring(parts[0].length()+1, parts[0].length()+1 + parts[1].length());
				details[index] = data.substring(parts[0].length() + parts[1].length() + 3, data.length());
				
				index += 1;
			}
		}
		
		setIterations(index);
		System.out.println("Odczytano plik!");
		scanner.close();
		setData(data);
		
    	for (int i = 0; i < iterations; i++) 
    	{
			Event a = new Event(timestamp[i], loglevel[i], details[i]);
    		System.out.println((queue.acceptEvent(a))?"Dodano Event(" + timestamp[i] + ", " + loglevel[i] + ", " + details[i] + ")":"Nie udalo sie dodac Eventu(" + timestamp[i] + ", " + loglevel[i] + ", " + details[i] + ")");
		}
    }
}
