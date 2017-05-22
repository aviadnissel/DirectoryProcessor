package fileprocessing.filters;

import java.io.File;

/**
 * Created by Noy on 22-May-17.
 */
public class WritableFilter extends Filter {

    private final String yesOrNo;

    public WritableFilter(String yesORNo, boolean not) {
        super(not);
        this.yesOrNo = yesORNo;
    }

    @Override
    public boolean isMatch(File file) {
        return false;
    }
}
