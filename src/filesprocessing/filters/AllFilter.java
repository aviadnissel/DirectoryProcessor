package filesprocessing.filters;

import java.io.File;

/**
 * This class represents the "all" filter - The filter which returns all the files in the directory.
 * @author Aviad Nissel, Noy Sternlicht
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


     /* --- Filter Impl. --- */

     @Override
    public boolean isMatch(File file){
        return true;
    }
}
