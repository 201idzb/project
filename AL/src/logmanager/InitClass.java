package logmanager;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
/**
 * klasa realizuj¹ca zadanie rdzenia aplikacji
 * @author Kajetan Hryñczuk
 *
 */
class AppCore {	
	private static Configuration conf;
	private static Class<?> inputClass = null;
	private static Object inputInstance = null;
	private static Class<?> outputClass = null;
	private static Object outputInstance = null;
	private static QueueManager queue = null;
	/**
	 * Konstruktor objektu Appcore
	 */
	AppCore() {
		System.out.println("Rozpoczynam start rdzenia");
		
		conf = new Configuration();
		queue = new QueueManager(conf.getMaxDBSize());

        try {
            ClassLoader myClassLoader = ClassLoader.getSystemClassLoader();
            System.out.println("£adowanie klasy: " 
            	+ "in." + conf.getInputAdapter());
            inputClass = myClassLoader.loadClass("in." 
            	+ conf.getInputAdapter());
            inputInstance = inputClass.newInstance();
             
            Method setupConfigMethod = inputClass.getMethod("setupConfig", 
            		new Class[] { Configuration.class });
            setupConfigMethod.invoke(inputInstance, new Object[] { conf });
            
            Method connectToQueueManagergMethod = 
            		inputClass.getMethod("connectToQueueManager", 
            		new Class[] { QueueManager.class });
            connectToQueueManagergMethod.invoke(inputInstance, 
            		new Object[] { queue });
            
            Method runMethod = inputClass.getMethod("exec", new Class[] { });
            runMethod.invoke(inputInstance, new Object[] { }); 
            
        } catch (SecurityException e) { 
        	e.printStackTrace(); 
        } catch (IllegalArgumentException e) { 
        	e.printStackTrace(); 
        } catch (ClassNotFoundException e) { 
        	e.printStackTrace(); 
        } catch (InstantiationException e) { 
        	e.printStackTrace(); 
        } catch (IllegalAccessException e) { 
        	e.printStackTrace(); 
        } catch (NoSuchMethodException e) { 
        	e.printStackTrace(); 
        } catch (InvocationTargetException e) { 
        	e.printStackTrace(); 
        }
        
        try {
            ClassLoader myClassLoader = ClassLoader.getSystemClassLoader();
            System.out.println("£adowanie klasy: " 
            		+ "out." + conf.getOutputAdapter());
            outputClass = myClassLoader.loadClass("out." 
            		+ conf.getOutputAdapter());
            outputInstance = outputClass.newInstance();
             
            Method setupConfigMethod = outputClass.getMethod("setupConfig",
            		new Class[] { Configuration.class });
            setupConfigMethod.invoke(outputInstance, new Object[] { conf });
            
            Method runMethod = outputClass.getMethod("exec", new Class[] { });
            runMethod.invoke(outputInstance, new Object[] { });
            
            List<Event> batch = new ArrayList<Event>();
            
            //wysy³anie logow
            while (true)  {
            	try { 
            		Thread.sleep(500);
            	} catch (InterruptedException ex) {
            		Thread.currentThread().interrupt();
            	}
            	
            	if (queue.getActSize() > conf.getBatchSize()) {
            		//pobranie elementow do batch
            		for (int i = 0; i < conf.getBatchSize(); ++i) {
            			Event event = queue.sendEvents();
            			batch.add(event);
            		}
            		boolean isOK = false;
            		
            		//wysylanie az pakiet zostanie odebrany
	            	while (!isOK) {    		
	            		Method storeEventsMethod = 
	            				outputClass.getMethod("storeEvents", 
	            						new Class[] { List.class });
	            		isOK = (boolean) storeEventsMethod.invoke(
	            			outputInstance, new Object[] { batch });
			            
			            if (!isOK) {
			            	try { 
			            		Thread.sleep(500);
			            	} catch (InterruptedException ex) {
			            		Thread.currentThread().interrupt();
			            	}
			            }
	            	}
	            	
	            	//wyczyscie batcha poniewaz wysalo juz elementy
	            	//utworzenie nowej lsity szybsze niz clear
	            	batch = new ArrayList<Event>(); 
            	}
            } 
        } catch (SecurityException e) { 
        	e.printStackTrace(); 
        } catch (IllegalArgumentException e) { 
        	e.printStackTrace(); 
        } catch (ClassNotFoundException e) { 
        	e.printStackTrace(); 
        } catch (InstantiationException e) { 
        	e.printStackTrace(); 
        } catch (IllegalAccessException e) { 
        	e.printStackTrace(); 
        } catch (NoSuchMethodException e) { 
        	e.printStackTrace(); 
        } catch (InvocationTargetException e) { 
        	e.printStackTrace(); 
        }
	}
}
/**
 * Klasa inicjalizuj¹ca aplikacje
 * @author Kajetan Hryñczuk
 *
 */
public class InitClass {
	/**
	 * Metoda inicjalizuj¹ca aplikacje
	 * @param args argumenty wejœciowe aplikacji
	 */
    public static void main(final String[] args) {
    	System.out.println("Start aplikacji...");
    			
		@SuppressWarnings("unused")
		AppCore appCore = new AppCore();
    }
}