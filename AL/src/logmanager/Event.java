package logmanager;

import java.util.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Klasa umo¿liwiajaca utworzenie objektu jaki u¿ywany jest do przechowywania eventów
 * przyklad utworzenia eventu
 * Timestamp tmp = null;
 * Event a=new Event(tmp, "yolo", LogLevel.INFO);
 * @author Kajetan Hryñczuk
 *
 */
public class Event {	
	/**
	 * typy zdarzeñ
	 * @author Kajetan Hryñczuk
	 *
	 */
	public enum LogLevel { INFO, WARNING, ERROR, SEVERE };
	private Timestamp timestamp;
	private LogLevel logLevel;
	private String details;
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
	private Date date;
	
	//metody zwracajace Timestamp, details i LogLevel
	/**
	 * Metoda zwracaj¹ca date zajœcia zdarzenia
	 * @return zwraca date zajœcia zdarzenia
	 */
	public final Timestamp getTimestamp() { return timestamp; }
	/**
	 * Metoda zwracaj¹ca informacje o zdarzeniu
	 * @return zwraca informacje o zdarzeniu
	 */
	public final String getDetails()      { return details; }
	/**
	 * Metoda zwracaj¹ca typ zdarzenia
	 * @return zwraca typ zdarzenia
	 */
	public final LogLevel getLoglevel()   { return logLevel; }
	
	/**
	 * konstruktor do stworzenia Eventu
	 * @param timestamp data zajœcia zdarzenia podana w formie stringa
	 * @param logLevel typ zdarzenia
	 * @param details informacje o zdarzeniu
	 */
	public Event(final String timestamp, final String logLevel, final String details) {
		try {
			date = (Date) df.parse(timestamp);
		} catch (ParseException e) { e.printStackTrace(); }
		
		this.timestamp = new Timestamp(date.getTime());
		this.details = details;
		this.logLevel = LogLevel.valueOf(logLevel);
	}
	
	/**
	 * konstruktor do stworzenia Eventu
	 * @param timestamp data zajœcia zdarzenia podana w formie timestamp
	 * @param logLevel typ zdarzenia
	 * @param details informacje o zdarzeniu
	 */
	public Event(final Timestamp timestamp, final String logLevel, final String details) {
		this.timestamp = timestamp;
		this.details = details;
		this.logLevel = LogLevel.valueOf(logLevel);
	}
}
