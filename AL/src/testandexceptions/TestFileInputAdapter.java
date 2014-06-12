package testandexceptions;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;


/** klasa testujaca FileInputAdapter.
 * @author Mateusz Ratajczyk
*/
public class TestFileInputAdapter {
	/**  Zmienna do przechowywania kolumny timestamp (czas zdarzenia). */
	private Timestamp[] timestamp = null;
	/**  Zmienna do przechowywania kolumny loglevel (typ zdarzenia).  */
	private String[] loglevel = null;
	/**  Zmienna do przechowywania kolumny details (opis zdarzenia).  */
	private String[] details = null;
	/**  Zmienna do przechowywania kolejnych spacji w linii z loga.  */
	private String[] parts;
	
	/** metoda testujaca
	 * sprawdza czy poprawnie wycina kolumne Timestamp.
	 */
	@Test
	public final void testTimestamp() {
		timestamp = new Timestamp[1];
		parts = getLog().split(" "); //wyszukiwanie spacji
		for (int i = 0; i < 1; ++i) {
			/**  Format czasu zdarzenia.  */
			DateFormat df = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss.SSS");

			Date tmp = null;
			try {
				tmp = (Date) df.parse((getLog().substring(1,
						0 + parts[0].length() - 1)));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			timestamp[i] = new Timestamp(tmp.getTime());
		}
		assertEquals("Wartosci sa rowne ", timestamp[0], getTimestamp());
	}
	
	 /** metoda testujaca
	 * sprawdza czy poprawnie wycina kolumne loglevel.
	 */
	@Test
	public final void testLogLevel() {
		loglevel = new String[1];
		parts = getLog().split(" "); //wyszukiwanie spacji
		for (int i = 0; i < 1; ++i) {
			loglevel[i] = getLog().substring(parts[0].length() + 1,
					parts[0].length() + 1 + parts[1].length());
		}
		assertEquals("Wartosci sa rowne ", loglevel[0], getLogLevel());
	}
	
	 /** metoda testujaca
	 * sprawdza czy poprawnie wycina kolumne details.
	 */
	@Test
	public final void testDetails() {
		details = new String[1];
		parts = getLog().split(" "); //wyszukiwanie spacji
		for (int i = 0; i < 1; ++i) {
			details[i] = getLog().substring(parts[0].length()
					+ parts[1].length() + 1 + 1 + 1,
					getLog().length());
		}
		assertEquals("Wartosci sa rowne ", details[0], getDetails());
	}

	
	/** metoda zwracajaca 1 linijke z loga.
	 * @return linijka z loga
	 */
	public final String getLog() {
		return "(2014-05-22T07:24:45.0813) INFO" 
				+ "  [is.iWeb.sentinel.logic.business." 
				+ "Configurator.log(Configurator.java:201)]" 
				+ " (Thread-379544) AO sn=CPE12 Value OK :" 
				+ "Device.ManagementServer.ConnectionRequestUsername " 
				+ " user==user";
	}
	
	/** metoda zwracajaca sparsowany Timestamp.
	 * @return Timestamp string
	 */
	public final Timestamp getTimestamp() {
		String tmp = "2014-05-22T07:24:45.0813";
		/**  Format czasu zdarzenia.  */
		DateFormat df = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss.SSS");
		Date tmpdata = null;
		try {
			tmpdata = (Date) df.parse(tmp);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Timestamp time = new Timestamp(tmpdata.getTime());
		return time;
	}
	
	/** metoda zwracajaca "INFO".
	 * @return "INFO" string
	 */
	public final String getLogLevel() {
		return "INFO";
	}
	
	/** metoda zwracajaca kolumne details.
	 * @return details string
	 */
	public final String getDetails() {
		return "[is.iWeb.sentinel.logic.business." 
				+ "Configurator.log(Configurator.java:201)]" 
				+ " (Thread-379544) AO sn=CPE12 Value OK :" 
				+ "Device.ManagementServer.ConnectionRequestUsername " 
				+ " user==user";
	}
}
