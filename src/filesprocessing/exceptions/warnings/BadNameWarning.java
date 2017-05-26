package filesprocessing.exceptions.warnings;

/**
 * Created by Noy on 22-May-17.
 */
public class BadNameWarning extends FileProcessingWarning {

    public BadNameWarning(String message, int line) {
        super(message, line);
    }
}
