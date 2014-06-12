package testandexceptions;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import in.SocketInputAdapter.LOG;

import org.junit.Test;

public class SocketInputAdapterTest {
    
    /**
     * Klasa LOG przechowuje obiekt logu.
     */
    public class LOG {
    /**  Zmienna do przechowywania kolumny timestamp (czas zdarzenia). */
     private Timestamp timestamp;
    /**  Zmienna do przechowywania kolumny loglevel (typ zdarzenia).  */
     private String loglevel;
    /**  Zmienna do przechowywania kolumny details (opis zdarzenia).  */
     private String details;

}

	@Test
	public final void testLogLevel() {
		LOG lg = new LOG();
		String[] parts = getLOG().split(" ");
		lg.loglevel = getLOG().substring(parts[0].length() + 1,
	               parts[0].length() + 1 + parts[1].length());
		assertEquals("Wartosci sa rowne", lg.loglevel, getLogLevel());
	}

	
	
	@Test
	public final void testDetails() {
		LOG lg = new LOG();
		String[] parts = getLOG().split(" ");
		 lg.details = getLOG().substring(parts[0].length()
		            + parts[1].length() + 1 + 1 + 1
		            , getLOG().length());
		assertEquals("Wartosci sa rowne", lg.details, getDetails());
	}
	
	@Test
	public final void testTimestamp() {
		LOG lg = new LOG();
		 DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		    String[] parts =  getLOG().split(" ");
		     Date date = null;
		     try {
				date = (Date) df.parse((getLOG().substring(1,
				            0 + parts[0].length() - 1)));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		    lg.timestamp = new Timestamp(date.getTime());

		assertEquals("Wartosci sa rowne", lg.timestamp, getTimestamp());
	}
	
	
	public String getLogLevel() {
		return "INFO";
	}
	
	public String getLOG() {
		return "(2014-05-22T07:24:45.0813) INFO" 
				+ "  [is.iWeb.sentinel.logic.business." 
				+ "Configurator.log(Configurator.java:201)]" 
				+ " (Thread-379544) AO sn=CPE12 Value OK :" 
				+ "Device.ManagementServer.ConnectionRequestUsername " 
				+ " user==user";
	}
	public String getDetails() {
		return "[is.iWeb.sentinel.logic.business." 
				+ "Configurator.log(Configurator.java:201)]" 
				+ " (Thread-379544) AO sn=CPE12 Value OK :" 
				+ "Device.ManagementServer.ConnectionRequestUsername " 
				+ " user==user";
	}
	
	public Timestamp getTimestamp() {
		 DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		 Date date = null;
		 String s = "2014-05-22T07:24:45.0813";
	     try {
			date = (Date) df.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}

	    Timestamp t = new Timestamp(date.getTime());
		return t;
	}
	
}

