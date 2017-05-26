package filesprocessing.exceptions.errors;

import filesprocessing.exceptions.FileProcessingException;

/**
 * Created by Noy on 22-May-17.
 */
public class FileProcessingError extends FileProcessingException {


    public FileProcessingError(String message, int line) {
        super(message, line);
    }
}
