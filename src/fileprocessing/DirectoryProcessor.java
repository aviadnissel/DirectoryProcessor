/**
 * @Author: Noy Sternlicht
 */

package fileprocessing;
import fileprocessing.exceptions.FileProcessingException;
import fileprocessing.exceptions.errors.*;
import fileprocessing.sections.Section;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is the main class of a program named DirectoryProcessor that filters and orders files according
 * to various properties.
 */
public class DirectoryProcessor {

    /* --- Constants --- */
    private static final String IO_ERROR_MESSAGE = "ERROR: An error occurred while accessing commandfile";


    /*--- Private Static Methods ---*/

    /* --- Errors Testers --- */

    private static boolean testInvalidUsage(String[] args) throws InvalidUsageError {
        // rise when:
        // args.length< 1
        // args[0], args[1] are not strings.
        // maybe to test with "file.exist()" if such files exists.
        return true;
    }

    /**
     * This method verifies that there are no IO problems while accessing the commands file. If there aren't,
     * creates a list with the commands file lines.
     * @param args: Command line arguments.
     * @returns: A list that contains the file lines.
     * @throws IoProblemsError
     */
    private static List<String> testIoProblems(String[] args)throws IOException{ // TODO: I think we should unify those two exceptions somehow (maybe we should cancel the costume exception?)
        File commandsFile = new File(args[1]);
        List<String> commandsFileLines = new ArrayList<>();

        // Tries to read from the commands file:
        try(BufferedReader bufferedReader = new BufferedReader( new FileReader(commandsFile))){
            String line;
            while ((line = bufferedReader.readLine()) != null){
                commandsFileLines.add(line);
            }
            }catch(IOException exception){
            System.err.println(IO_ERROR_MESSAGE);
            System.exit(1);
            }
        return commandsFileLines;
    }


    /* --- Main Methods --- */

    /**
     * Runs invalidUsage test and ioProblems test and handle the errors if there are.
     * @param args
     * @returns: If the commands file passed the tested, returns is. Throws the relevant error otherwise.
     */
    private static List<String> runBasicTests(String[] args) throws FileProcessingError{
        return null;
    }

    /**
     * Gets a list of commandsfile lines and unify each bundle of rows into a section text.
     * @param commandFileLines
     * @return
     */
    private static List<String> unifyToSections(List<String> commandFileLines){

        return null;
    }

    private static List<Section> createSections(List<String> sectionsText) throws FileProcessingException{  //Here the program catches the errors from section factory & the warnings from F.factory, O.factory.
        return null;
    }

    private static void printOutput(List<Section> sections){

    }

    /* --- MAIN --- */

    /**
     * The main function runs the DirectoryProcessing program.
     * @param args: Command line should be: java filesProcessing.DirectoryProcessor sourcedir commandfile
     *              sourcedir - A path. The files that the program filters and orders are in that directory.
     *              commandsfile - A path. The commands in commandsfile used to filter and order the files in
     *              sourcedir.
     * @throws FileProcessingException
     */
    public static void main(String[] args) throws FileProcessingException{

        List<String> commandsFileLines = runBasicTests(args);
        List<String> sectionsTextList = unifyToSections(commandsFileLines);
        List<Section> sectionsList = createSections(sectionsTextList);

        printOutput(sectionsList);

    }
}

