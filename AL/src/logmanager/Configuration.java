
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
	private final static String delimiter=":";
	
	private String getValFromLine(String tmpLine)
	{
	    if (tmpLine != null) {
	    	if(tmpLine.contains(delimiter)) {
	    		tmpLine=tmpLine.substring(tmpLine.indexOf(delimiter)+1);
	    	}
	    	return tmpLine; 
	    } else throw new RuntimeException("Plik konfiguracyjny zawiera niepelne dane");
	}
	
	private Integer getValFromLineInInt(String tmpLine)
	{
	    if (tmpLine != null) {
	    	if(tmpLine.contains(delimiter)) {
	    		tmpLine=tmpLine.substring(tmpLine.indexOf(delimiter)+1);
	    	} 
	    	return Integer.parseInt(tmpLine); 
	    } else throw new RuntimeException("Plik konfiguracyjny zawiera niepelne dane");
	}
	
	/**
	 * Konstruktor objektu typu Configuration
	 */
	public Configuration() {
		try {
		
			//tworzy plik konfiguracyjny conf.dat
			FileReader confHandle = new FileReader("conf.dat");
			BufferedReader bufferReader = new BufferedReader(confHandle);
			
			inputAdapter=getValFromLine(bufferReader.readLine());
			outputAdapter=getValFromLine(bufferReader.readLine());
			maxDBSize=getValFromLineInInt(bufferReader.readLine());
			batchSize=getValFromLineInInt(bufferReader.readLine());
			locInput=getValFromLine(bufferReader.readLine());
			locOutput=getValFromLine(bufferReader.readLine());
			port=getValFromLine(bufferReader.readLine());
			DBName=getValFromLine(bufferReader.readLine());
			DBHost=getValFromLine(bufferReader.readLine());
			DBUserName=getValFromLine(bufferReader.readLine());
			DBPassword=getValFromLine(bufferReader.readLine());
			DBTableName=getValFromLine(bufferReader.readLine());
		    

		    
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