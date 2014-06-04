
package logmanager;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



/**
 * Klasa ³aduj¹ca konfiguracje adaptera logów do programu
 * zczytuje konfiguracje aplikacji z pliku 
 * Konfiguracja przechowywana jest w objekcie typu Configuration
 * @author Kajetan Hryñczuk
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
	 *  Metoda zwracaj¹ca nazwe wejœciowego adaptera
	 * @return Zwraca nazwe wejœciowego adaptera
	 */
	public String getInputAdapter() { return inputAdapter; }
	/**
	 * Metoda zwracaj¹ca nazwe wyjœciowego adaptera
	 * @return Zwraca nazwe wyjœciowego adaptera
	 */
	public String getOutputAdapter() { return outputAdapter; }
	/**
	 * Metoda zwracaj¹ca maksymalny dostpny rozmiar bazy logów 
	 * @return Zwraca maksymalny dostepny rozmiar bazy logów jak¹ mo¿e buforowac aplikacja 
	 */
	public long getMaxDBSize() { return maxDBSize; }
	/**
	 * Metoda zwracaj¹ca rozmiar jednej paczki danych przesy³anej przez adaptery
	 * @return zwraca rozmiar jednej paczki danych przesy³anej przez adaptery
	 */
	public long getBatchSize() { return batchSize; }
	/**
	 * Metoda zwracaj¹ca plik wejœciowy
	 * @return zwraca plik wejœciowy 
	 */
	public String getLocInput() { return locInput; }
	/**
	 * Metoda Zwracaj¹ca plik wyjœciowy
	 * @return zwraca plik wyjœciowy
	 */
	public String getLocOutput() { return locOutput; }
	public String getPort() {return port;}
	public String getDBName() {return DBName;}
	public String getDBHost() {return DBHost;}
	public String getDBUserName() {return DBUserName;}
	public String getDBPassword() {return DBPassword;}
	public String getDBTableName() {return DBTableName;}
}