
package in;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import logmanager.Configuration;
import logmanager.QueueManager;
import logmanager.Event;

/**
* wejsciowy adapter pobierajacy dane z pliku.
* @author Mateusz Ratajczyk
*/

public class FileInputAdapter extends Thread implements InputAdapter {
	/**  Obiekt do klasy Configuration.  */
	private Configuration config;
	/**  Obiekt do klasy QueueManager.  */
	private QueueManager queue;

	/**  Zmienna do przechowywania kolumny timestamp (czas zdarzenia). */
	private Timestamp[] timestamp = null;
	/**  Zmienna do przechowywania kolumny loglevel (typ zdarzenia).  */
	private String[] loglevel = null;
	/**  Zmienna do przechowywania kolumny details (opis zdarzenia).  */
	private String[] details = null;
	/**  Zmienna do przechowywania kolejnych spacji w linii z loga.  */
	private String[] parts;
	/**  Zmienna do przechowywania linii z loga.  */
	private String data = "";
	/**  Czas zdarzenia wykorzystywany do rzutowania.  */
	private Date date;

	/**  Konstruktor wypisujacy utworzenie adaptera.  */
	public FileInputAdapter() {
		System.out.println("Utworzono Adapter Wejsciowy");
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
	* Metoda sluzaca do polaczenia z obiektem
	* kolejki za pomoca referencji z rdzenia aplikacji.
	* @param que referencja do kolejki
	*/
	public final void connectToQueueManager(final QueueManager que) {
		this.queue = que;
	}

	/**
	* funkcja wywolania.
	*/
	public final void exec() { start(); }

	/**
	* do obslugi funkcji testowej.
	* @param tmp test
	* @return null
	*/
	@Override
	public final String test(final String tmp) { return null; }

	/**
	* metoda sluzaca do tworzenia zdarzen.
	*/
	public final void createEvents() {
		/**  Zmienna do przechowywania czasu uspienia (w ms).  */
		final int timeToSleep = 1000;

		for (int i = 0; i < config.getBatchSize(); ++i) {
			if (timestamp[i] == null) { break; }

			Event a = new Event(timestamp[i], loglevel[i], details[i]);

			while (!queue.acceptEvent(a)) {
				System.out.println("Nie udalo sie dodac Eventu("
				+ timestamp[i]
				+ ", " + loglevel[i]
				+ ", " + details[i]);
				System.out.println("Ponawiam...");
				try { Thread.sleep(timeToSleep);
				}  catch (InterruptedException e) { e.printStackTrace(); }
			}
			System.out.println("Dodano Event("
			+ timestamp[i] + ", "
			+ loglevel[i] + ", "
			+ details[i]);
		}
	}

	/** 
	* metoda sluzaca do ustalania wartosci
	* timestamp, loglevel oraz details.
	* @param i index
	*/
	public final void setValues(final int i) {
		/**  Format czasu zdarzenia.  */
		DateFormat df = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss.SSS");

		try {
			date = (Date) df.parse((data.substring(1,
					0 + parts[0].length() - 1)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		timestamp[i] = new Timestamp(date.getTime());

		loglevel[i] = data.substring(parts[0].length() + 1,
		parts[0].length() + 1 + parts[1].length());

		details[i] = data.substring(parts[0].length()
		+ parts[1].length() + 1 + 1 + 1,
		data.length());
	}

	/**
	* metoda obslugi watku.
	*/
	public final void run() {
		/**  Zmienna do obslugi zczytywania danych z pliku.  */
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(config.getLocInput()));
			System.out.println("Odczytano sciezke do pliku!");
		} catch (FileNotFoundException e) {
			System.out.println("Blad odczytu pliku!");
		}
		//dopoki jest nastepna linia
		while (scanner.hasNextLine()) {
			timestamp = new Timestamp[(int) config.getBatchSize()];
			loglevel = new String[(int) config.getBatchSize()];
			details = new String[(int) config.getBatchSize()];

			for (int i = 0; i < config.getBatchSize(); ++i) {
				//jezeli nie ma nastepnej linii to konczy
				if (!scanner.hasNextLine()) { break; }
				data = scanner.nextLine();
				parts = data.split(" "); //wyszukiwanie spacji

				//jesli nie jest to pusta linia
				if (!(data.equals(""))) {
					//ustawianie wartosci Timestamp, Loglevel oraz details
					setValues(i);
				}
			}
			//tworzenie Zdarzen
			createEvents();
		}
		System.out.println("Odczytano plik!");
		scanner.close();
	}
}