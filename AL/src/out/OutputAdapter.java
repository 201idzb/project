package out;

import java.util.List;

import logmanager.Configuration;
import logmanager.Event;
/**
 * Interfejs dla adapter�w zapisu
 * @author Kajetan Hry�czuk
 *
 */
public interface OutputAdapter {
	/**
	 * Metoda s�u��ca do po��czenia z objektem konfiguracji za pom�c� referencji z rdzenia aplikacji
	 * @param config objekt zawieraj�cy konfiguracje
	 */
	void setupConfig(Configuration config);
	/**
	 * Metoda wywo�ywana przez rdze� aplikacji odpowiedzialna za przechwycenie event�w i zapisanie ich
	 * @param batch lista zawjeraj�ca zdarzenia
	 * @return true je�li zdarzenia zostan� zapisane, a else wrazie niepowodzenia
	 */
	boolean storeEvents(List<Event> batch);
	/**
	 * funkcja testowa
	 */
	String test(String tmp);
	void exec();
}