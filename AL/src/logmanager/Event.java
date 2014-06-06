package logmanager;

import java.util.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Klasa umo�liwiajaca utworzenie objektu jaki u�ywany
 * jest do przechowywania event�w.
 * przyklad utworzenia eventu
 * Timestamp tmp = null;
 * Event a=new Event(tmp, "yolo", LogLevel.INFO);
 * @author Kajetan Hry�czuk
 *
 */
public class Event {
	/**
	 * typy zdarze�.
	 * @author Kajetan Hry�czuk
	 *
	 */
	public enum LogLevel { INFO, WARNING, ERROR, SEVERE };
	/**  Czas wykonania zdarzenia.  */
	private Timestamp timestamp;
	/**  Typ zdarzenia.  */
	private LogLevel logLevel;
	/**  Opis zdarzenia.  */
	private String details;
	/**  Format czasu zdarzenia.  */
	private DateFormat df = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss.SSS");
	/**  Czas zdarzenia wykorzystywany do rzutowania.  */
	private Date date;

	//metody zwracajace Timestamp, details i LogLevel
	/**
	 * Metoda zwracaj�ca date zaj�cia zdarzenia.
	 * @return zwraca date zaj�cia zdarzenia
	 */
	public final Timestamp getTimestamp() { return timestamp; }
	/**
	 * Metoda zwracaj�ca informacje o zdarzeniu.
	 * @return zwraca informacje o zdarzeniu
	 */
	public final String getDetails()      { return details; }
	/**
	 * Metoda zwracaj�ca typ zdarzenia.
	 * @return zwraca typ zdarzenia
	 */
	public final LogLevel getLoglevel()   { return logLevel; }

	/**
	 * konstruktor do stworzenia Eventu.
	 * @param timestampTmp data zaj�cia zdarzenia podana w formie stringa
	 * @param logLevelTmp typ zdarzenia
	 * @param detailsTmp informacje o zdarzeniu
	 * @deprecated metoda przestarzlaa nalezy uzyc jej
	 * przeciazonego odpowiednika !
	 */
	@Deprecated
	public Event(final String timestampTmp, final String logLevelTmp,
			final String detailsTmp) {
		try {
			date = (Date) df.parse(timestampTmp);
		} catch (ParseException e) { e.printStackTrace(); }
		this.timestamp = new Timestamp(date.getTime());
		this.details = detailsTmp;
		this.logLevel = LogLevel.valueOf(logLevelTmp);
	}

	/**
	 * konstruktor do stworzenia Eventu.
	 * @param timestampTmp data zaj�cia zdarzenia podana w formie timestamp
	 * @param logLevelTmp typ zdarzenia
	 * @param detailsTmp informacje o zdarzeniu
	 */
	public Event(final Timestamp timestampTmp, final String logLevelTmp,
			final String detailsTmp) {
		this.timestamp = timestampTmp;
		this.details = detailsTmp;
		try {
			this.logLevel = LogLevel.valueOf(logLevelTmp);
		} catch (IllegalArgumentException e) {
			//e.printStackTrace();
			System.out.println("Nadano wartosc WARNING dla "
					+ "LogLevel w zdarzeniu z "
					+ "blednym LogLevel!");
			this.logLevel = LogLevel.WARNING;
		}
	}
}
