
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