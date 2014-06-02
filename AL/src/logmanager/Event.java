package logmanager;

import java.util.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

//przyklad utworzenia eventu
//Timestamp tmp = null;
//Event a=new Event(tmp, "yolo", LogLevel.INFO);

public class Event {	
	public enum LogLevel { INFO, WARNING, ERROR, SEVERE };
	private Timestamp timestamp;
	private LogLevel logLevel;
	private String details;
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
	private Date date;
	
	//metody zwracajace Timestamp, details i LogLevel
	public final Timestamp getTimestamp() { return timestamp; }
	public final String getDetails()      { return details; }
	public final LogLevel getLoglevel()   { return logLevel; }
	
	//konstruktor do stworzenia Eventu
	public Event(final String timestamp, final String logLevel, final String details) {
		try {
			date = (Date) df.parse(timestamp);
		} catch (ParseException e) { e.printStackTrace(); }
		
		this.timestamp = new Timestamp(date.getTime());
		this.details = details;
		this.logLevel = LogLevel.valueOf(logLevel);
	}
}
