package filesprocessing.exceptions.errors;

import filesprocessing.exceptions.FileProcessingException;

/**
 * A general FileProcessing error exception.
 *
 * @author Aviad Nissel, Noy Sternlicht
 */
public class FileProcessingError extends FileProcessingException {


    /* --- Constructors --- */

    public FileProcessingError(String message, int line) {
        super(message, line);
    }
}
