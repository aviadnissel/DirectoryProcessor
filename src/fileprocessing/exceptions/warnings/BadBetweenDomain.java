package fileprocessing.exceptions.warnings;

/**
 * Created by Noy on 22-May-17.
 */
public class BadBetweenDomain extends FileProcessingWarning {
    public BadBetweenDomain(String message, int line) {
        super(message, line);
    }
}
