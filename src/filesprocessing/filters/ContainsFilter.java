package filesprocessing.filters;
import java.io.File;

/**
 * This class creates a filter that checks if the file name contains a given string.
 * @author Aviad Nissel, Noy Sternlicht
 */
public class ContainsFilter extends Filter {


    /* --- Data Members --- */

    private final String value;


    /* --- Constructors --- */

    /**
     * Creates a new contains filter.
     * @param value: Files with names than contain that value matches the filter.
     * @param not: If true the files that doesn't match the filter are returned. Otherwise the files that
     *             match it are returned.
     */
    public ContainsFilter(String value, boolean not) {
        super(not);
        this.value = value;
    }


    /* --- Filter Impl. --- */

    @Override
    public boolean isMatch(File file) {
        return file.getName().contains(value);
    }
}
