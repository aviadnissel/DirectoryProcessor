package filesprocessing.filters;
import java.io.File;

/**
 * This class represents a filter that matches files with names that ends with a given value.
 * @author Aviad Nissel, Noy Sternlicht
 */
public class SuffixFilter extends Filter {


    /* --- Data Members --- */

    private final String suffix;


    /* --- Constructors --- */

    /**
     * Creates a new prefix filter.
     * @param suffix: The files that matches the filter has names that starts with this prefix.
     * @param not: If true the files that doesn't match the filter are returned. Otherwise the files that
     *             match it are returned.
     */
    public SuffixFilter(String suffix, boolean not) {
        super(not);
        this.suffix = suffix;
    }


    /* --- Filter Impl. --- */

    @Override
    public boolean isMatch(File file) {
        return file.getName().endsWith(suffix);
    }
}
