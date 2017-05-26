package filesprocessing.filters;
import java.io.File;

/**
 * Created by Noy Sternlicht on 21-May-17.
 */


/**
 * This class represents a filter which returns files with size strictly bigger than a given
 * number (in K-bytes)
 */
public class GreaterThanFilter extends Filter {

    private static final int KBYTES_TO_BYTES = 1024;
    private int greaterThan;

    /* --- Constructors --- */

    /**
     * Creates a new greater_than filter object.
     * @param greaterThan: The filter returns files with size strictly bigger than this number (in K-bytes)
     * @param not: If false the files with size bigger than "greaterThan" match. Else, the rest match.
     */
    public GreaterThanFilter(String greaterThan, Boolean not){
        super(not);
        this.greaterThan = (Integer.parseInt(greaterThan)) * KBYTES_TO_BYTES;

    }

    /* --- Methods --- */

    @Override

    /**
     * Returns true if the tested file's size is bigger than "greaterThan", false otherwise.
     * @param file: The tested file object.
     * @returns: True if the tested file's size is bigger than "greaterThan", false otherwise.
     */
    public boolean isMatch(File file){
        return file.length() > greaterThan;
    }
}
