package fileprocessing.filters;

import java.io.File;

/**
 * Created by Noy on 21-May-17.
 */

/**
 * This class represents the "all" filter - The filter which returns all the files in the directory.
 */
public class AllFilter extends Filter {

     /* --- Constructors --- */

    /**
     * Creates all filter.
     * @param not: If false all files are matched, otherwise none of them is a match.
     */
    public AllFilter(boolean not){
        super(not);
    }

     /* --- Methods --- */
    /**
     * Returns true for each File object.
     * @param file: The file object to be tested.
     * @return true.
     */
    public boolean isMatch(File file){
        return true;
    }
}
