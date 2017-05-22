package fileprocessing.exceptions;

/**
 * Created by Noy on 22-May-17.
 */
public class FileProcessingException extends Exception{
    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    private int line;

    public FileProcessingException(String message, int line){
        super(message);
        this.line = line;
    }


}
