package fileprocessing.filters;

import java.io.File;

/**
 * Created by Noy on 22-May-17.
 */
public class ExecutableFilter extends Filter {

    private final String yesOrNo;

    public ExecutableFilter(String yesOrNo, boolean not) {
        super(not);
        this.yesOrNo = yesOrNo;
    }

    @Override
    public boolean isMatch(File file) {
        return false;
    }
}
