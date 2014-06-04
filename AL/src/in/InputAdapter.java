package in;

import logmanager.Configuration;
import logmanager.QueueManager;
/**
 * Interfejs dla adapterów odczytu
 * @author Kajetan Hryñczuk
 *
 */
public interface InputAdapter {
	/**
	 * Metoda s³u¿¹ca do po³¹czenia z objektem konfiguracji za pom¹c¹ referencji z rdzenia aplikacji
	 * @param config objekt zawieraj¹cy konfiguracje
	 */
	void setupConfig(Configuration config);
	/**
	 * Metoda s³u¿¹ca do po³¹czenia z objektem kolejki za pom¹c¹ referencji z rdzenia aplikacji
	 * @param queue referencja do kolejki
	 */
	void connectToQueueManager(QueueManager queue);
	/**
	 * 
	 * funkcja testowa 
	 * 
	 */
	String test(String tmp);
	void exec();
}