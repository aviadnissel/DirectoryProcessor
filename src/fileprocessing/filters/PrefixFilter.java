package fileprocessing.filters;

import java.io.File;

/**
 * Created by Noy on 22-May-17.
 */
public class PrefixFilter extends Filter {

    private final String prefix;

    public PrefixFilter(String prefix, boolean not) {
        super(not);
        this.prefix = prefix;
    }

    @Override
    public boolean isMatch(File file) {
        return false;
    }
}
