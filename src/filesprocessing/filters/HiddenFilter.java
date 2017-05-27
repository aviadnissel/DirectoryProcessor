package filesprocessing.filters;
import java.io.File;

/**
 * This class represents a filter which returns files that are hidden, or files that are not hidden according
 * to a given word (YES for hidden, NO for not hidden).
 * @author Aviad Nissel, Noy Sternlicht
 */
public class HiddenFilter extends Filter {


    /* --- Data Members --- */

    private String yesOrNo;


    /* --- Constructors --- */

    public HiddenFilter(String yesOrNo, boolean not){
        super(not);
        this.yesOrNo = yesOrNo;
    }

    /* --- Filter Impl. --- */

    @Override
    public boolean isMatch(File file){
        return (!(file.isHidden()) ^ (yesOrNo.equals("YES")));
    }
}
