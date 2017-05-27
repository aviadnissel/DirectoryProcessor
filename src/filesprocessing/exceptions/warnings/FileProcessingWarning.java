package filesprocessing.exceptions.warnings;

import filesprocessing.exceptions.FileProcessingException;

/**
 * A general FileProcessing warning exception.
 *
 * @author Aviad Nissel, Noy Sternlicht
 */

public class FileProcessingWarning extends FileProcessingException {


    /* --- Constructors --- */

    public FileProcessingWarning(String message, int line) {
        super(message, line);
    }
}
