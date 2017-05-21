package fileprocessing.filters;

import java.io.File;

/**
 * Created by Noy on 21-May-17.
 */
public class Between extends Filter{
    public Between(String smaller, String bigger, boolean not){
        super(not);

    }

    public boolean isMatch(File file){
        return true;
    }
}
