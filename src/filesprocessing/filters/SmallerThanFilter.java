package filesprocessing.filters;

import java.io.File;

/**
 * This class represents a filter which returns files with size strictly smaller than a given
 * number (in K-bytes)
 * @author Aviad Nissel, Noy Sternlicht
 */
public class SmallerThanFilter extends Filter {


    /* --- Constants --- */

    private static final int KBYTES_TO_BYTES = 1024;


    /* --- Data Members --- */

    private double smallerThan;


    /* --- Constructors --- */

    /**
     * Creates a new smaller_than filter object.
     * @param smallerThan: The filter returns files with size strictly smaller than this number (in K-bytes)
     * @param not: If false the files with size smaller than "smallerThan" match. Else, the rest match.
     */
    public SmallerThanFilter(String smallerThan, Boolean not){
        super(not);
        this.smallerThan = (Double.parseDouble(smallerThan)) * KBYTES_TO_BYTES;
    }


    /* --- Filter Impl. --- */

    @Override
    public boolean isMatch(File file){
        return file.length() < smallerThan;
    }
}
