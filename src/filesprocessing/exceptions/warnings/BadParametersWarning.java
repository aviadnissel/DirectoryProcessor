package filesprocessing.exceptions.warnings;

/**
 * A BadParametersWarning exception.
 *
 * @author Aviad Nissel, Noy Sternlicht
 */
public class BadParametersWarning extends FileProcessingWarning {


    /* --- Constructors --- */

    public BadParametersWarning(String message, int line) {
        super(message, line);
    }
}
