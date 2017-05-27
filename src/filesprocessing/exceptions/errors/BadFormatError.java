package filesprocessing.exceptions.errors;

/**
 * A BadFormatError exception.
 *
 * @author Aviad Nissel, Noy Sternlicht
 */
public class BadFormatError extends FileProcessingError {


    /* --- Constructors --- */

    public BadFormatError(String message, int line) {
        super(message, line);
    }
}
