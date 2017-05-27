package filesprocessing.filters;

import java.io.File;

/**
 * This class represents a filter which returns files that have execute permission, or files
 * that doesn't have execute permissions according to a given word (YES for executable, NO for not executable).
 * @author Aviad Nissel, Noy Sternlicht
 */
public class ExecutableFilter extends Filter {


    /* --- Data Members --- */

    private final String yesOrNo;


    /* --- Constructors --- */

    /**
     * Creates a new hidden filter.
     * @param yesOrNo: If YES: executable files are returned, if NO not executable files are returned.
     * @param not:  If true the files that doesn't match the filter are returned. Otherwise the files that
     *             match it are returned.
     */
    public ExecutableFilter(String yesOrNo, boolean not) {
        super(not);
        this.yesOrNo = yesOrNo;
    }


    /* --- Filter Impl. --- */

    @Override
    public boolean isMatch(File file) {
        return file.canExecute() == yesOrNo.equals("YES");
    }
}
