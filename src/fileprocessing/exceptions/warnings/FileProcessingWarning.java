package fileprocessing.exceptions.warnings;

import fileprocessing.exceptions.FileProcessingException;

/**
 * Created by Noy on 22-May-17.
 */
public class FileProcessingWarning extends FileProcessingException {

    public FileProcessingWarning(String message, int line) {
        super(message, line);
    }
}
