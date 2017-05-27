package filesprocessing.filters;
import java.io.File;

/**
 * This class represents a filter which returns files with size strictly bigger
 * than a given number (in K-bytes)
 * @author Aviad Nissel, Noy Sternlicht
 */
public class GreaterThanFilter extends Filter {


    /* --- Constants --- */

    private static final int KBYTES_TO_BYTES = 1024;


    /* --- Data Members --- */

    private double greaterThan;


    /* --- Constructors --- */

    /**
     * Creates a new greater_than filter object.
     * @param greaterThan: The filter returns files with size strictly bigger than this number (in K-bytes)
     * @param not: If false the files with size bigger than "greaterThan" match. Else, the rest match.
     */
    public GreaterThanFilter(String greaterThan, Boolean not){
        super(not);
        this.greaterThan = (Double.parseDouble(greaterThan)) * KBYTES_TO_BYTES;

    }


    /* --- Filter Impl. --- */

    @Override
    public boolean isMatch(File file){
        return file.length() > greaterThan;
    }
}
