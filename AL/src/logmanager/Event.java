package logmanager;

import java.util.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Klasa umo�liwiajaca utworzenie objektu jaki u�ywany jest do przechowywania event�w
 * przyklad utworzenia eventu
 * Timestamp tmp = null;
 * Event a=new Event(tmp, "yolo", LogLevel.INFO);
 * @author Kajetan Hry�czuk
 *
 */
public class Event {	
	/**
	 * typy zdarze�
	 * @author Kajetan Hry�czuk
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
	 * Metoda zwracaj�ca date zaj�cia zdarzenia
	 * @return zwraca date zaj�cia zdarzenia
	 */
	public final Timestamp getTimestamp() { return timestamp; }
	/**
	 * Metoda zwracaj�ca informacje o zdarzeniu
	 * @return zwraca informacje o zdarzeniu
	 */
	public final String getDetails()      { return details; }
	/**
	 * Metoda zwracaj�ca typ zdarzenia
	 * @return zwraca typ zdarzenia
	 */
	public final LogLevel getLoglevel()   { return logLevel; }
	
	/**
	 * konstruktor do stworzenia Eventu
	 * @param timestamp data zaj�cia zdarzenia podana w formie stringa
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
	 * @param timestamp data zaj�cia zdarzenia podana w formie timestamp
	 * @param logLevel typ zdarzenia
	 * @param details informacje o zdarzeniu
	 */
	public Event(final Timestamp timestamp, final String logLevel, final String details) {
		this.timestamp = timestamp;
		this.details = details;
		this.logLevel = LogLevel.valueOf(logLevel);
	}
}
