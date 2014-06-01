package logmanager;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Configuration //zczytuje konfigurację aplikacji z pliku i odtwarza ją w formie obiektu Configuration.
{
	private static String inputAdapter;
	private static String outputAdapter;
	private static String locInput, locOutput;
	private static long maxDBSize;
	private static long batchSize;
	
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
		    
		    if ((tmpLine = bufferReader.readLine()) != null)  batchSize=Integer.parseInt(tmpLine); 
		    else { bufferReader.close(); throw new RuntimeException("Plik konfiguracyjny zawiera niepelne dane"); }
		    
		    if ((tmpLine = bufferReader.readLine()) != null)  locInput=tmpLine; 
		    else { bufferReader.close(); throw new RuntimeException("Plik konfiguracyjny zawiera niepelne dane"); }	
		    
		    if ((tmpLine = bufferReader.readLine()) != null)  locOutput=tmpLine; 
		    else { bufferReader.close(); throw new RuntimeException("Plik konfiguracyjny zawiera niepelne dane"); }			    
		    
		    bufferReader.close();
		} 
		catch (IOException ex) { System.out.println("ERR: nie można odczyt pliku konfiguracyjnego("+ex+")"); }
	}
	
	
	public String getInputAdapter() { return inputAdapter; }
	public String getOutputAdapter() { return outputAdapter; }
	public long getMaxDBSize() { return maxDBSize; }
	public long getBatchSize() { return batchSize; }
	public String getLocInput() { return locInput; }
	public String getLocOutput() { return locOutput; }
}
