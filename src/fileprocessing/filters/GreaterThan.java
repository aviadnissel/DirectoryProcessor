package fileprocessing.filters;

import java.io.File;

/**
 * Created by Noy on 21-May-17.
 */
public class GreaterThan extends Filter {
    private int greaterThan;

    public GreaterThan(String greaterThan, Boolean not){
        super(not);
    }
    public boolean isMatch(File file){
        return true;
    }
}
