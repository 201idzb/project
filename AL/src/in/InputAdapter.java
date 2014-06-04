package in;

import logmanager.Configuration;
import logmanager.QueueManager;
/**
 * Interfejs dla adapter�w odczytu
 * @author Kajetan Hry�czuk
 *
 */
public interface InputAdapter {
	/**
	 * Metoda s�u��ca do po��czenia z objektem konfiguracji za pom�c� referencji z rdzenia aplikacji
	 * @param config objekt zawieraj�cy konfiguracje
	 */
	void setupConfig(Configuration config);
	/**
	 * Metoda s�u��ca do po��czenia z objektem kolejki za pom�c� referencji z rdzenia aplikacji
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