package fileprocessing.filters;

import java.io.File;

/**
 * Created by Noy on 21-May-17.
 */

/**
 * This class represents the "all" filter - The filter which returns all the files in the directory.
 */
public class All extends Filter {
    /**
     * Creates all filter.
     * @param not: If false all files are matched, otherwise none of them is a match.
     */
    public All(boolean not){
        super(not);
    }

    /**
     * Returns true for each File object.
     * @param file: The file object to be tested.
     * @return true.
     */
    public boolean isMatch(File file){
        return true;
    }
}
