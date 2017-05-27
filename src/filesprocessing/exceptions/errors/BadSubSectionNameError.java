package filesprocessing.exceptions.errors;

/**
 * A BadSubSectionNameError exception.
 *
 * @author Aviad Nissel, Noy Sternlicht
 */
public class BadSubSectionNameError extends FileProcessingError {


    /* --- Constructors --- */

    public BadSubSectionNameError(String message, int line) {
        super(message, line);
    }
}
