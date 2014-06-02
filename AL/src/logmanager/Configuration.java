package logmanager;
import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;


//zczytuje konfiguracje aplikacji z pliku 
//i odtwarza ja w formie obiektu Configuration
public class Configuration {
	private static String inputAdapter;
	private static String outputAdapter;
	private static String locInput, locOutput;
	private static long maxDBSize;
	private static long batchSize;
	
	public Configuration() {
		try {
			String tmpLine;
			
			//tworzy plik konfiguracyjny conf.dat
			FileReader confHandle = new FileReader("conf.dat");
			BufferedReader bufferReader = new BufferedReader(confHandle);
			
			tmpLine = bufferReader.readLine();
			
		    if (tmpLine != null) { 
		    	inputAdapter = tmpLine; 
		    } else { bufferReader.close(); 
		    throw new RuntimeException(
		    		"Plik konfiguracyjny zawiera niepelne dane"); 
		    }
		    
		    tmpLine = bufferReader.readLine();
		    
		    if (tmpLine != null)  { 
		    	outputAdapter = tmpLine; 
		    } else { 
		    	bufferReader.close(); 
		    	throw new RuntimeException(
		    			"Plik konfiguracyjny zawiera niepelne dane"); 
		    }
		    
		    tmpLine = bufferReader.readLine();
		    
		    if (tmpLine != null)  { 
		    	maxDBSize = Integer.parseInt(tmpLine); 
		    } else { 
		    	bufferReader.close(); 
		    	throw new RuntimeException(
		    			"Plik konfiguracyjny zawiera niepelne dane"); 
		    }
		    
		    tmpLine = bufferReader.readLine();
		    
		    if (tmpLine != null)  { 
		    	batchSize = Integer.parseInt(tmpLine); 
		    } else { 
		    	bufferReader.close(); 
		    	throw new RuntimeException(
		    			"Plik konfiguracyjny zawiera niepelne dane"); 
		    }
		    
		    tmpLine = bufferReader.readLine();
		    
		    if (tmpLine != null)  { 
		    	locInput = tmpLine; 
		    } else { 
		    	bufferReader.close(); 
		    	throw new RuntimeException(
		    			"Plik konfiguracyjny zawiera niepelne dane"); 
		    }	
		    
		    tmpLine = bufferReader.readLine();
		    
		    if (tmpLine != null)  { 
		    	locOutput = tmpLine; 
		    } else { 
		    	bufferReader.close(); 
		    	throw new RuntimeException(
		    			"Plik konfiguracyjny zawiera niepelne dane"); 
		    }			    
		    
		    bufferReader.close();
		} catch (IOException ex) { 
			System.out.println(
					"ERR: nie mozna odczyt pliku konfiguracyjnego(" + ex + ")");
		}
	}
	
	//metody zwracajace zmienne z pliku konfiguracyjnego
	public String getInputAdapter() { return inputAdapter; }
	public String getOutputAdapter() { return outputAdapter; }
	public long getMaxDBSize() { return maxDBSize; }
	public long getBatchSize() { return batchSize; }
	public String getLocInput() { return locInput; }
	public String getLocOutput() { return locOutput; }
}