/**
 * Created by Noy Sternlicht on 22-May-17.
 */

package filesprocessing.filters;
import java.io.File;

/**
 * This class creates a filter that checks if the file name contains a given string.
 */
public class ContainsFilter extends Filter {
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

    /* --- Methods --- */

    /**
     * Tests if a file matches the filter.
     * @param file: The file to be tested.
     * @returns: True if the file matches the filter, false otherwise.
     */
    @Override
    public boolean isMatch(File file) {
        return file.getName().contains(value);
    }
}
