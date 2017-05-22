package fileprocessing.filters;

import fileprocessing.exceptions.warnings.FileProcessingWarning;

import java.io.File;

/**
 * Created by Noy on 22-May-17.
 */
public class test {
    public static void main(String[] args) throws FileProcessingWarning {
//        File test = new File("noy/is/pretty.txt");
//        System.out.println(test.isHidden());
//        HiddenFilter filter = new HiddenFilter("NO", false);
//        System.out.println(filter.isMatch(test));

        System.out.println(FilterFactory.createFilter("between#10#2"));



    }
}
