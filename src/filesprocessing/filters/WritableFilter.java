package filesprocessing.filters;

import java.io.File;

/**
 * This class represents a filter which returns files that have writhing permission, or files
 * that doesn't have writing permissions according to a given word (YES for writable, NO for not writable).
 *
 * @author Aviad Nissel, Noy Sternlicht
 */
public class WritableFilter extends Filter {


    /* --- Data Members --- */

    private final String yesOrNo;

     /* --- Constructors --- */

    /**
     * Creates a new hidden filter.
     * @param yesOrNo: If YES: writable files are returned, if NO not writable files are returned.
     * @param not:  If true the files that doesn't match the filter are returned. Otherwise the files that
     *             match it are returned.
     */
    public WritableFilter(String yesOrNo, boolean not) {
        super(not);
        this.yesOrNo = yesOrNo;
    }


    /* --- Filter Impl. --- */

    @Override
    public boolean isMatch(File file) {
        return file.canWrite() == yesOrNo.equals("YES");
    }
}
