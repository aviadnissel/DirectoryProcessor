package filesprocessing.exceptions.errors;

/**
 * A IoProblemsError exception.
 *
 * @author Aviad Nissel, Noy Sternlicht
 */
public class IoProblemsError extends FileProcessingError {


    /* --- Constructors --- */

    public IoProblemsError(String message, int line) {
        super(message, line);
    }
}
