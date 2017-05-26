package filesprocessing.exceptions.errors;

/**
 * Created by Noy on 22-May-17.
 */
public class BadFormatError extends FileProcessingError {
    public BadFormatError(String message, int line) {
        super(message, line);
    }
}
