package logmanager;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Configuration //zczytuje konfiguracjê aplikacji z pliku i odtwarza j¹ w formie obiektu Configuration.
{
	private static String inputAdapter;
	private static String outputAdapter;
	private static long maxDBSize;
	
	public Configuration() 
	{
		try 
		{	
			String tmpLine;
			
			FileReader confHandle = new FileReader("conf.dat");
			BufferedReader bufferReader = new BufferedReader(confHandle);
			
		    if ((tmpLine = bufferReader.readLine()) != null)  inputAdapter=tmpLine; 
		    else { bufferReader.close(); throw new RuntimeException("Plik konfiguracyjny zawiera niepelne dane"); }
		    
		    if ((tmpLine = bufferReader.readLine()) != null)  outputAdapter=tmpLine; 
		    else { bufferReader.close(); throw new RuntimeException("Plik konfiguracyjny zawiera niepelne dane"); }
		    
		    if ((tmpLine = bufferReader.readLine()) != null)  maxDBSize=Integer.parseInt(tmpLine); 
		    else { bufferReader.close(); throw new RuntimeException("Plik konfiguracyjny zawiera niepelne dane"); }
		    
		    bufferReader.close();
		} 
		catch (IOException ex) { System.out.println("ERR: nie mo¿na odczyt pliku konfiguracyjnego("+ex+")"); }
	}
	
	
	public String getInputAdapter() { return inputAdapter; }
	public String getOutputAdapter() { return outputAdapter; }
	public long getMaxDBSize() { return maxDBSize; }
}
