package filesprocessing.exceptions.errors;

/**
 * Created by Noy on 22-May-17.
 */
public class InvalidUsageError extends FileProcessingError {
    public InvalidUsageError(String message, int line) {
        super(message, line);
    }
}
