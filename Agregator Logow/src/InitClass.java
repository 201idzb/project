
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;







class Configuration //zczytuje konfiguracjê aplikacji z pliku i odtwarza j¹ w formie obiektu Configuration.
{
	private static String inputAdapter;
	private static String outputAdapter;
	private static long MaxDBSize=5000;
	
	Configuration() 
	{
		try 
		{	
			String tmpLine;
			
			FileReader confHandle = new FileReader("AgregatorLogow.dat");
			BufferedReader bufferReader = new BufferedReader(confHandle);
			
		    if ((tmpLine = bufferReader.readLine()) != null)  inputAdapter=tmpLine; 
		    else { bufferReader.close(); throw new RuntimeException("Plik konfiguracyjny zawiera niepelne dane"); }
		    
		    if ((tmpLine = bufferReader.readLine()) != null)  outputAdapter=tmpLine; 
		    else { bufferReader.close(); throw new RuntimeException("Plik konfiguracyjny zawiera niepelne dane"); }
		    
		    if ((tmpLine = bufferReader.readLine()) != null)  MaxDBSize=Integer.parseInt(tmpLine); 
		    else { bufferReader.close(); throw new RuntimeException("Plik konfiguracyjny zawiera niepelne dane"); }
		    
		    bufferReader.close();
		} 
		catch (IOException ex) { System.out.println("ERR: nie mo¿na odczyt pliku konfiguracyjnego("+ex+")"); }
	}
	
	
	public String getInputAdapter() { return inputAdapter; }
}

interface QueueManager //na podstawie konfiguracji inicjalizuje wymagane adaptery oraz klase QueueManager zarz¹dzaj¹ca kolejkowaniem zdarzeñ
{
	void connectToQueue();
	
	//nadzoruje przetwarzanie kolejki (decyduje kiedy zdarzenia s¹ wysy³ane do adaptera wyjœciowego). 
}

class AppCore implements QueueManager
{	
	private static Configuration conf;
	private static Class<?> iAdapter = null;
	
	AppCore() 
	{
		System.out.println("Rozpoczynam start rdzenia");
		
		conf=new Configuration();
		
		
		System.out.println(conf.getInputAdapter());
		try  { iAdapter = Class.forName(conf.getInputAdapter()); } 
		catch (ClassNotFoundException e)  { System.out.println("ERR: nie mo¿na wczytaæ klasy adaptera odczytu"); }
		
        
	}
	
	@Override
	public void connectToQueue() 
	{
		// TODO Auto-generated method stub
		
	}
	


}




public class InitClass
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		System.out.println("Start aplikacji...");
		
		@SuppressWarnings("unused")
		AppCore appCore=new AppCore();
		
	}

}

