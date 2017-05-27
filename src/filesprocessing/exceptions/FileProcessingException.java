package filesprocessing.exceptions;

/**
 * A basic FileProcessing exception.
 *
 * @author Aviad Nissel, Noy Sternlicht
 */
public class FileProcessingException extends Exception {


    /* --- Data Members --- */

    private int line;


    /* --- Constructors --- */

    /**
     * Constructor.
     * @param message The message.
     * @param line The line number the exception was raised in.
     */
    public FileProcessingException(String message, int line){
        super(message);
        this.line = line;
    }

    /* --- Getters / Setters --- */

    /**
     * Setter for line.
     * @param line The line number.
     */
    public void setLine(int line) {
        this.line = line;

    }

    /**
     * Getter for line.
     * @return line The line number.
     */
    public int getLine() {
        return line;
    }
}
