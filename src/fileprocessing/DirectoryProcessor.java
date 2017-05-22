/**
 * Created by Noy Sternlicht on 20/05/2017.
 */

package fileprocessing;
import fileprocessing.exceptions.errors.*;
import fileprocessing.sections.Section;
import java.io.File;
import java.util.List;

public class DirectoryProcessor {

    /*--- Private Static Methods ---*/

    /* --- Errors Testers --- */

    private static boolean invalidUsage(String[] args) throws InvalidUsageError {
        return true;
    }

    private static File ioProblems(String[] args)throws IoProblemsError{
        return null;
    }


    /* --- Main Methods --- */

    private static List<String> splitToSections(File commandFile){
        return null;
    }

    private static List<Section> createSections(List<String> sectionsText){
        return null;
    }

    private static void printOutput(List<Section> sections){

    }

    /* --- MAIN --- */

    public static void main(String[] args) throws FileProcessingError{
    }
}

