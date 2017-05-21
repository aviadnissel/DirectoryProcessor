package fileprocessing.filters;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Noy on 21-May-17.
 */
abstract public class Filter {
    public Filter(boolean not){
        this.not = not;
    }
    private boolean not = false;
    public List<File> filterFiles(List<File> files){
        List<File> matched = new ArrayList<>();
        for (File file: files) {
            if(not ^ isMatch(file)){
                matched.add(file);
            }
        }
        return matched;
    }

    abstract public boolean isMatch(File file);
}
