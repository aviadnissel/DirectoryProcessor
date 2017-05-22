package fileprocessing.filters;

import java.io.File;

/**
 * Created by Noy on 22-May-17.
 */
public class ContainsFilter extends Filter {
    private final String value;

    public ContainsFilter(String value, boolean not) {
        super(not);
        this.value = value;
    }

    @Override
    public boolean isMatch(File file) {
        return false;
    }
}
