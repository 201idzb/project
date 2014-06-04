package out;

import java.util.List;

import logmanager.Configuration;
import logmanager.Event;
/**
 * Interfejs dla adapterów zapisu
 * @author Kajetan Hryñczuk
 *
 */
public interface OutputAdapter {
	/**
	 * Metoda s³u¿¹ca do po³¹czenia z objektem konfiguracji za pom¹c¹ referencji z rdzenia aplikacji
	 * @param config objekt zawieraj¹cy konfiguracje
	 */
	void setupConfig(Configuration config);
	/**
	 * Metoda wywo³ywana przez rdzeñ aplikacji odpowiedzialna za przechwycenie eventów i zapisanie ich
	 * @param batch lista zawjeraj¹ca zdarzenia
	 * @return true jeœli zdarzenia zostan¹ zapisane, a else wrazie niepowodzenia
	 */
	boolean storeEvents(List<Event> batch);
	/**
	 * funkcja testowa
	 */
	String test(String tmp);
	void exec();
}