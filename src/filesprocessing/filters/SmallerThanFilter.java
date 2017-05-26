package filesprocessing.filters;

import java.io.File;

/**
 * Created by Noy on 21-May-17.
 */

/**
 * This class represents a filter which returns files with size strictly smaller than a given
 * number (in K-bytes)
 */
public class SmallerThanFilter extends Filter {

    private static final int KBYTES_TO_BYTES = 1024;
    private int smallerThan;


    /* --- Constructors --- */

    /**
     * Creates a new smaller_than filter object.
     * @param smallerThan: The filter returns files with size strictly smaller than this number (in K-bytes)
     * @param not: If false the files with size smaller than "smallerThan" match. Else, the rest match.
     */
    public SmallerThanFilter(String smallerThan, Boolean not){
        super(not);
        this.smallerThan = (Integer.parseInt(smallerThan)) * KBYTES_TO_BYTES;
    }

    /* --- Methods --- */


    @Override

    /**
     * Returns true if the tested file's size is smaller than "smallerThan", false otherwise.
     * @param file: The tested file object.
     * @returns: True if the tested file's size is smaller than "smallerThan", false otherwise.
     */
    public boolean isMatch(File file){
        return file.length() < smallerThan;
    }
}
