package fileprocessing.filters;

import java.io.File;

/**
 * Created by Noy on 22-May-17.
 */
public class SuffixFilter extends Filter {

    private final String suffix;

    public SuffixFilter(String suffix, boolean not) {
        super(not);
        this.suffix = suffix;
    }

    @Override
    public boolean isMatch(File file) {
        return false;
    }
}
