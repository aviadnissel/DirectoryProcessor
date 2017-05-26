package filesprocessing.exceptions.errors;

/**
 * Created by Noy on 22-May-17.
 */
public class BadSubSectionNameError extends FileProcessingError {
    public BadSubSectionNameError(String message, int line) {
        super(message, line);
    }
}
