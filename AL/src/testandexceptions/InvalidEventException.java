package testandexceptions;

/**
 * klasa wyjatku dla eventu.
 * @author Kajetan Hrynczuk
 *
 */
public class InvalidEventException extends Exception {
     /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;


/**
 * konstuktor wyjateku.
 * @param message tresc wyjatku
 */
    public InvalidEventException(final String message) {
         super(message);
      }

}
