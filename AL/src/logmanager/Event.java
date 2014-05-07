package logmanager;

import java.sql.Timestamp;


//przyklad utworzenia eventu
//Timestamp tmp = null;
//Event a=new Event(tmp, "yolo", LogLevel.INFO);



public class Event 
{	
	public enum LogLevel { INFO, WARNING, ERROR, SEVERE };
	private Timestamp timestamp;
	private LogLevel loglevel;
	private String details;
	
	public Timestamp getTimestamp() { return timestamp; }
	public String getDetails()      { return details; }
	public LogLevel getLoglevel()   { return loglevel; }

	public Event(Timestamp timestamp, String details, LogLevel loglevel)
	{
		this.timestamp=timestamp;
		this.details=details;
		this.loglevel=loglevel;
	}
}
