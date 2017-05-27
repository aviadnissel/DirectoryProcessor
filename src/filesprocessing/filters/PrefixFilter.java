package filesprocessing.filters;
import java.io.File;

/**
 * This class represents a filter that matches files with names that starts with a given value.
 * @author Aviad Nissel, Noy Sternlicht
 */
public class PrefixFilter extends Filter {


    /* --- Data Members --- */

    private final String prefix;


    /* --- Constructors --- */

    /**
     * Creates a new prefix filter.
     * @param prefix: The files that matches the filter has names that starts with this prefix.
     * @param not: If true the files that doesn't match the filter are returned. Otherwise the files that
     *             match it are returned.
     */
    public PrefixFilter(String prefix, boolean not) {
        super(not);
        this.prefix = prefix;
    }


    /* --- Filter Impl. --- */

    @Override
    public boolean isMatch(File file) {
        return file.getName().startsWith(prefix);
    }
}
