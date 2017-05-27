package filesprocessing.exceptions.warnings;

/**
 * A BadBetweenDomain exception.
 *
 * @author Aviad Nissel, Noy Sternlicht
 */
public class BadBetweenDomain extends FileProcessingWarning {


    /* --- Constructors --- */

    public BadBetweenDomain(String message, int line) {
        super(message, line);
    }
}
