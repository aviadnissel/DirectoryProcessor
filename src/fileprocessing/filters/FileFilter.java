package fileprocessing.filters;

import java.io.File;

/**
 * Created by Noy on 22-May-17.
 */
public class FileFilter extends Filter {
    private final String name;

    public FileFilter(String name, boolean not) {
        super(not);
        this.name = name;
    }

    @Override
    public boolean isMatch(File file) {
        return false;
    }
}
