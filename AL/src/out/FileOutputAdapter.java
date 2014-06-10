package out;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import logmanager.Configuration;
import logmanager.Event;
import logmanager.Event.LogLevel;

/**
* wyjsciowy adapter zapisu do pliku.
* @author Mateusz Ratajczyk
*
*/
public class FileOutputAdapter extends Thread implements OutputAdapter {
	/**  Obiekt do klasy Configuration.  */
	private Configuration config;
	/**  Zmienna do obslugi zapisywania danych do pliku.  */
	private Writer out;
	/**  Zmienna do przechowywania linii z loga.  */
	private String jsonRepresentation = "";

	/**  Obiekt do przechowywania szablonu zapisu do JSON.  */
	private SomeObject someObject;

	/**  Konstruktor wypisujacy utworzenie adaptera.  */
	public FileOutputAdapter() {
		System.out.println("Utworzono Adapter Wyjsciowy");
	}

	/** prywatna klasa, ktora posiada osobny konstruktor
	* inicjalizujacy zmienne Timestamp, Loglevel, Details.
	*/
	private class SomeObject {
		/**  Zmienna do przechowywania Timestamp`a.  */
		@SuppressWarnings("unused")
		private final Timestamp timestamp;
		/**  Zmienna do przechowywania Loglevel`a.  */
		@SuppressWarnings("unused")
		private final LogLevel loglevel;
		/**  Zmienna do przechowywania Details`a.  */
		@SuppressWarnings("unused")
		private final String details;

		/** konstruktor do tworzenia szablonu
		*  wykorzystywanego do zapisu do formatu JSON.
		*  @param a Timestamp
		*  @param b logLevel
		*  @param c String
		*/
		public SomeObject(final Timestamp a, 
		final LogLevel b, final String c) {
			this.timestamp = a;
			this.loglevel = b;
			this.details = c;
		}
	}

	/**
	* Metoda sluzaca do polaczenia z obiektem
	* konfiguracji za pomoca referencji z rdzenia aplikacji.
	* @param cnfg obiekt zawierajacy konfiguracje
	*/
	public final void setupConfig(final Configuration cnfg) {
		this.config = cnfg;
	}

	/**
	* do obslugi funkcji testowej.
	* @param tmp test
	* @return null
	*/
	public final String test(final String tmp) { return tmp; }

	/**
	* funkcja wywolania.
	*/
	public final void exec() { start(); }

	/**
	* metoda do przechwytywania Eventow.
	* @param batch paczka zdarzen
	* @return true
	*/
	public final boolean storeEvents(final List<Event> batch) {
		//System.out.println("FileOutputAdapter - Przechwycono zdarzenia.");
		Gson gson = new GsonBuilder().setFieldNamingPolicy(
		FieldNamingPolicy.UPPER_CAMEL_CASE).create();
		String tmpString = "";

		for (@SuppressWarnings("rawtypes") Iterator iterator = batch.iterator();
		iterator.hasNext();) {
			Event event = (Event) iterator.next();          

			someObject = new SomeObject(
			event.getTimestamp(),
			event.getLoglevel(),
			event.getDetails());

			tmpString = gson.toJson(someObject);

			jsonRepresentation = jsonRepresentation
			+ tmpString
			+ System.getProperty("line.separator");

			try {
				out = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(config.getLocOutput())));
				out.write(jsonRepresentation);
				out.flush();
				out.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//System.out.println("Zapisano do pliku!");
		}
		return true; //jesli ok
	}

	/**
	* metoda obslugi watku.
	*/
	public final void run() {
		final int timeToSleep = 500;
		//utrzymywanie watku przy zyciu
		while (true) {
			try {
				Thread.sleep(timeToSleep);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
	}
}