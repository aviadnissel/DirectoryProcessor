package filesprocessing.exceptions.errors;

/**
 * A InvalidUsageError exception.
 *
 * @author Aviad Nissel, Noy Sternlicht
 */
public class InvalidUsageError extends FileProcessingError {


    /* --- Constructors --- */

    public InvalidUsageError(String message, int line) {
        super(message, line);
    }
}
