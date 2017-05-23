package fileprocessing.filters;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @authors: Aviad Nissel, Noy Sternlicht
 */
abstract public class Filter {
    private boolean not = false;
    public Filter(boolean not){
        this.not = not;
    }
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
