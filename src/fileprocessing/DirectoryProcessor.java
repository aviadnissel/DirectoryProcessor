/**
 * @Author: Noy Sternlicht
 */

package fileprocessing;
import fileprocessing.exceptions.errors.*;
import fileprocessing.exceptions.warnings.FileProcessingWarning;
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

    /**
     * Runs invalidUsage test and ioProblems test and handle the errors if there are.
     * @param args
     * @returns: If the commands file passed the tested, returns is. Throws the relevant error otherwise.
     */
    private static File runBasicTests(String[] args) throws FileProcessingError{
        return null;
    }

    private static List<String> splitToSections(File commandFile){

        return null;
    }

    private static List<Section> createSections(List<String> sectionsText) throws FileProcessingError,
            FileProcessingWarning{  //Here the program catches the errors from section factory & the warnings from F.factory, O.factory.
        return null;
    }

    private static void printOutput(List<Section> sections){

    }

    /* --- MAIN --- */

    public static void main(String[] args) throws FileProcessingError, FileProcessingWarning{

        File commandsFile = runBasicTests(args); // If the file passed the tests, it is saved as a variable.
        List<String> sectionsTextList = splitToSections(commandsFile);
        List<Section> sectionsList = createSections(sectionsTextList);

        printOutput(sectionsList);

    }
}

