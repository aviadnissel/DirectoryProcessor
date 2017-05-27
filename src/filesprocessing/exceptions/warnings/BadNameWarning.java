package filesprocessing.exceptions.warnings;

/**
 * A BadNameWarning exception.
 *
 * @author Aviad Nissel, Noy Sternlicht
 */

public class BadNameWarning extends FileProcessingWarning {


    /* --- Constructors --- */

    public BadNameWarning(String message, int line) {
        super(message, line);
    }
}
