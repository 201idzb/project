
package logmanager;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



/**
 * Klasa �aduj�ca konfiguracje adaptera log�w do programu
 * zczytuje konfiguracje aplikacji z pliku 
 * Konfiguracja przechowywana jest w objekcie typu Configuration
 * @author Kajetan Hry�czuk
 */
public class Configuration {
	private static String inputAdapter;
	private static String outputAdapter;
	private static String locInput, locOutput;
	private static long maxDBSize;
	private static long batchSize;
	private static String port, DBName, DBHost, DBUserName, DBPassword, DBTableName;
	/**
	 * Konstruktor objektu typu Configuration
	 */
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
		    
			tmpLine = bufferReader.readLine();
			
		    if (tmpLine != null) { 
		    	port = tmpLine; 
		    } else { bufferReader.close(); 
		    throw new RuntimeException(
		    		"Plik konfiguracyjny zawiera niepelne dane"); 
		    }
		    
			tmpLine = bufferReader.readLine();
			
		    if (tmpLine != null) { 
		    	DBName = tmpLine; 
		    } else { bufferReader.close(); 
		    throw new RuntimeException(
		    		"Plik konfiguracyjny zawiera niepelne dane"); 
		    }
		    
			tmpLine = bufferReader.readLine();
			
		    if (tmpLine != null) { 
		    	DBHost = tmpLine; 
		    } else { bufferReader.close(); 
		    throw new RuntimeException(
		    		"Plik konfiguracyjny zawiera niepelne dane"); 
		    }
		    
			tmpLine = bufferReader.readLine();
			
		    if (tmpLine != null) { 
		    	DBUserName = tmpLine; 
		    } else { bufferReader.close(); 
		    throw new RuntimeException(
		    		"Plik konfiguracyjny zawiera niepelne dane"); 
		    }
		    
			tmpLine = bufferReader.readLine();
			
		    if (tmpLine != null) { 
		    	DBPassword = tmpLine; 
		    } else { bufferReader.close(); 
		    throw new RuntimeException(
		    		"Plik konfiguracyjny zawiera niepelne dane"); 
		    }
		    
			tmpLine = bufferReader.readLine();
			
		    if (tmpLine != null) { 
		    	DBTableName = tmpLine; 
		    } else { bufferReader.close(); 
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
	/**
	 *  Metoda zwracaj�ca nazwe wej�ciowego adaptera
	 * @return Zwraca nazwe wej�ciowego adaptera
	 */
	public String getInputAdapter() { return inputAdapter; }
	/**
	 * Metoda zwracaj�ca nazwe wyj�ciowego adaptera
	 * @return Zwraca nazwe wyj�ciowego adaptera
	 */
	public String getOutputAdapter() { return outputAdapter; }
	/**
	 * Metoda zwracaj�ca maksymalny dostpny rozmiar bazy log�w 
	 * @return Zwraca maksymalny dostepny rozmiar bazy log�w jak� mo�e buforowac aplikacja 
	 */
	public long getMaxDBSize() { return maxDBSize; }
	/**
	 * Metoda zwracaj�ca rozmiar jednej paczki danych przesy�anej przez adaptery
	 * @return zwraca rozmiar jednej paczki danych przesy�anej przez adaptery
	 */
	public long getBatchSize() { return batchSize; }
	/**
	 * Metoda zwracaj�ca plik wej�ciowy
	 * @return zwraca plik wej�ciowy 
	 */
	public String getLocInput() { return locInput; }
	/**
	 * Metoda Zwracaj�ca plik wyj�ciowy
	 * @return zwraca plik wyj�ciowy
	 */
	public String getLocOutput() { return locOutput; }
	public String getPort() {return port;}
	public String getDBName() {return DBName;}
	public String getDBHost() {return DBHost;}
	public String getDBUserName() {return DBUserName;}
	public String getDBPassword() {return DBPassword;}
	public String getDBTableName() {return DBTableName;}
}