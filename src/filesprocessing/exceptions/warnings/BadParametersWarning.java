package filesprocessing.exceptions.warnings;

/**
 * Created by Noy on 22-May-17.
 */
public class BadParametersWarning extends FileProcessingWarning {
    public BadParametersWarning(String message, int line) {
        super(message, line);
    }
}
