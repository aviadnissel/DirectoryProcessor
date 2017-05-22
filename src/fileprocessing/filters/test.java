package fileprocessing.filters;

import java.io.File;

/**
 * Created by Noy on 22-May-17.
 */
public class test {
    public static void main(String[] args) {
        File test = new File("noy/is/pretty.txt");
        System.out.println(test.isHidden());
        HiddenFilter filter = new HiddenFilter("NO", false);
        System.out.println(filter.isMatch(test));
    }
}
