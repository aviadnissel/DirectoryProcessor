/**
 * Created by Noy Sternlicht on 21-May-17.
 */

package filesprocessing.filters;
import java.io.File;

/**
 * This class represents a filter which returns files with size between two given numbers (inclusive).
 */
public class BetweenFilter extends Filter{

    private static final int KBYTES_TO_BYTES = 1024;
    private int lowerBound, upperBound;

    /* --- Constructors --- */

    /**
     * Creates a new between filter.
     * @param lowerBound: The files that match the filter has size upperBound or equal to "LowerBound"
     *                    (in K-bytes).
     * @param upperBound: The files that match the filter has size smaller or equal to "upperBound"
     *                    (in K-bytes).
     * @param not: If false, files with size between "lowerBound" and "upperBound" are returned. Else,
     *             the files that
     *             doesn't match the filter are returned.
     */
    public BetweenFilter(String lowerBound, String upperBound, boolean not){
        super(not);
        this.lowerBound = Integer.parseInt(lowerBound) * KBYTES_TO_BYTES;
        this.upperBound = Integer.parseInt(upperBound) * KBYTES_TO_BYTES;
    }

    /* --- Methods --- */

    @Override

    /**
     * Returns true if the file size is between lowerBound and upperBound.
     * @param file: The file to be tested.
     * @returns: True if the file size is between lowerBound and upperBound, false otherwise.
     */
    public boolean isMatch(File file){
        return ((file.length() >= lowerBound) && (file.length() <= upperBound));
    }
}
