/**
 * Created by Noy Sternlicht on 22-May-17.
 */

package fileprocessing.filters;
import java.io.File;

/**
 * This class represents a filter that matches files with names that ends with a given value.
 */
public class SuffixFilter extends Filter {

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

    /* --- Methods --- */

    /**
     * Tests if a file matches the filter.
     * @param file: The file to be tested.
     * @returns: True if the file matches the filter, false otherwise.
     */
    @Override
    public boolean isMatch(File file) {
        return file.getName().endsWith(suffix);
    }
}
