package filesprocessing.filters;
import java.io.File;


/**
 * This class represents a filter tha returns only files with name equals to a given value (excluding path).
 * @author Aviad Nissel, Noy Sternlicht
 */
public class FileFilter extends Filter {


    /* --- Data Members --- */

    private String value;


    /* --- Constructor --- */

    /**
     * Creates a new "file" filter.
     * @param value: Files with names equals to value match the filter.
     * @param not: If true the files that doesn't match the filter are returned. Otherwise the files that
     *             match it are returned.
     */
    public FileFilter(String value, boolean not) {
        super(not);
        this.value = value;
    }


    /* --- Filter Impl. --- */

    @Override
    public boolean isMatch(File file) {
        return value.equals(file.getName());
    }
}
