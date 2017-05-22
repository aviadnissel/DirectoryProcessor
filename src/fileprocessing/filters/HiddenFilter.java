
/**
 * Created by Noy Sternlich on 22-May-17.
 */

package fileprocessing.filters;
import java.io.File;

/**
 * This class represents a filter which returns files that are hidden, or files that are not hidden according
 * to a given word (YES for hidden, NO for not hidden).
 */
public class HiddenFilter extends Filter {
    private String yesOrNo;


    /* --- Constructors --- */

    /**
     * Creates a new hidden filter.
     * @param yesOrNo: If YES: hidden files are returned, if NO unhidden files are returned.
     * @param not:  If false, the filter returns hidden/ unhidden files according to the yesOrNo parameter.
     *              Else: returns the files that don't match the filter.
     */
    public HiddenFilter(String yesOrNo, boolean not){
        super(not);
        this.yesOrNo = yesOrNo; // TODO: might be a bad input.
    }

    /* --- Methods --- */

    /**
     * Tests if a file match the filter.
     * @param file: The file to be tested.
     * @return: True if the file passed the filter, false otherwise.
     */
    public boolean isMatch(File file){
        return (!(file.isHidden()) ^ (yesOrNo.equals("YES"))); // TODO : test this little shit
    }
    // TODO: remove later, for tests:
    // file hidden & yes == true // true
    // file hidden & no == false // false
    // file not hidden & yes == false // false
    // file not hidden & no == true // true
}
