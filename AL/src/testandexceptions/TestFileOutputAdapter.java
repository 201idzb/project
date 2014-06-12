
package testandexceptions;


/** klasa testujaca FileOutputAdapter.
 * @author Mateusz Ratajczyk
*/
public class TestFileOutputAdapter {
	
	// FileNotFoundException
	//
	// Blad ten wystapi gdy podczas tworzenia nowego pliku:
	// out = new BufferedWriter(new OutputStreamWriter(
	// new FileOutputStream(config.getLocOutput())));
	//
	// - nie bedzie miejsca na dysku
	// - nie bedzie polaczenia z dyskiem
	
	// Test sprawdzenia czy zdarzenia zapisaly sie
	// w formacie JSON zakonczony powodzeniem.
}