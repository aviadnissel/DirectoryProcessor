package filesprocessing.filters;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * An abstract filter.
 * Filters the given list of files according to the (unimplemented) isMatch method.
 *
 * @author Aviad Nissel, Noy Sternlicht
 */
abstract public class Filter {


    /* --- Data Members --- */

    private boolean not = false;


    /* --- Constructors --- */

    public Filter(boolean not){
        this.not = not;
    }


    /* --- Public Methods --- */

    /**
     * Filters the given file list.
     * @param files The file list to filter.
     * @return A filtered file list.
     */
    public List<File> filterFiles(List<File> files){
        List<File> matched = new ArrayList<>();
        for (File file: files) {
            if(not ^ isMatch(file)){
                matched.add(file);
            }
        }
        return matched;
    }


    /* --- Abstract Methods --- */

    /**
     * Checks if a given file is a match (or should it be filtered out).
     * @param file The file.
     * @return True or false.
     */
    abstract protected boolean isMatch(File file);
}
