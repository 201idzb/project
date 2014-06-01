package logmanager;

import java.util.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

//przyklad utworzenia eventu
//Timestamp tmp = null;
//Event a=new Event(tmp, "yolo", LogLevel.INFO);

public class Event 
{	
	public enum LogLevel { INFO, WARNING, ERROR, SEVERE };
	private Timestamp timestamp;
	private LogLevel logLevel;
	private String details;
	private DateFormat df = new SimpleDateFormat ("yyyy-MM-dd'T'HH:mm:ss.SSS");
	private Date date;
	
	public Timestamp getTimestamp() { return timestamp; }
	public String getDetails()      { return details; }
	public LogLevel getLoglevel()   { return logLevel; }
	
	public Event(String timestamp, String logLevel, String details)
	{
		try {
			date = (Date)df.parse(timestamp);
		} catch (ParseException e) {e.printStackTrace();}
		
		this.timestamp = new Timestamp(date.getTime());
		this.details = details;
		this.logLevel = LogLevel.valueOf(logLevel);
	}
}
