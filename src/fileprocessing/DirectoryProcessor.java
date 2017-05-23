/**
 * @Author: Aviad Nissel, Noy Sternlicht
 */

package fileprocessing;
import fileprocessing.exceptions.FileProcessingException;
import fileprocessing.exceptions.errors.*;
import fileprocessing.exceptions.warnings.FileProcessingWarning;
import fileprocessing.sections.Section;
import fileprocessing.sections.SectionFactory;

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
    private static final String INVALID_USAGE_ERROR_MESSAGE = "ERROR: Line arguments are invalid.";
    private static final String LINE_SEPARATOR = "/n";
    private static final String DEFAULT_SECTION_STRING = "";
    private static final int LINE_NOT_NEEDED = -1;
    private static final int SECTION_SIZE = 3;
    private static final String WARNINGS_MESSAGE = "Warning in line";
    private static final String BAD_FORMAT_ERROR_MESSAGE = "Error: The file format is not valid.";
    private static final String BAD_SUBSECTION_NAME_MESSAGE = "Error: Subsections names are not valid.";



    /*--- Private Static Methods ---*/

    /* --- Errors Testers --- */

    /**
     * Tests if the command line arguments are ok.
     * @param args: Command line arguments.
     * @throws InvalidUsageError
     */
    private static void testInvalidUsage(String[] args) throws InvalidUsageError {
        File sourceDir = new File(args[0]);
        File commandsFile = new File(args[1]);
        if((args.length != 2) || !sourceDir.exists() || !commandsFile.exists()){
            throw new InvalidUsageError(INVALID_USAGE_ERROR_MESSAGE, LINE_NOT_NEEDED);
        }
    }

    /**
     * This method verifies that there are no IO problems while accessing the commands file. If there aren't,
     * creates a list with the commands file lines.
     * @param args: Command line arguments.
     * @returns: A list that contains the file lines.
     * @throws IoProblemsError
     */
    private static List<String> testIoProblems(String[] args)throws IoProblemsError{
        File commandsFile = new File(args[1]);
        List<String> commandsFileLines = new ArrayList<>();

        // Tries to read the commands file:
        try(BufferedReader bufferedReader = new BufferedReader( new FileReader(commandsFile))){
            String line;
            while ((line = bufferedReader.readLine()) != null){
                commandsFileLines.add(line);
                }
            }
            catch(IOException exception){
            throw new IoProblemsError(IO_ERROR_MESSAGE, LINE_NOT_NEEDED);
            }
        return commandsFileLines;
        }


    /* --- Main Methods --- */

    /**
     * Runs invalidUsage test and ioProblems test and handle the errors if there are.
     * @param args: Command line arguments.
     * @returns: If the commands file passed the tested, returns is. Throws the relevant error otherwise.
     */
    private static List<String> runBasicTests(String[] args) throws FileProcessingError{

        try {
            testInvalidUsage(args);
        } catch (InvalidUsageError invalidUsageError){
            System.err.println(INVALID_USAGE_ERROR_MESSAGE);
            System.exit(1);
        }

        List<String> commandsFileLines = new ArrayList<>();

        try{
            commandsFileLines = testIoProblems(args);
        } catch (IoProblemsError ioProblemsError){
            System.err.println(IO_ERROR_MESSAGE);
            System.exit(1);
        }
        return commandsFileLines;
    }

    /**
     * Gets a list of commandsfile lines and unify each bundle of rows into a section text.
     * @param commandFileLines: A list of commandsfile lines.
     * @returns A list of sections strings.
     */
    private static List<String> createSectionsText(List<String> commandFileLines){  //TODO: The last "/n" won't ruin anything?

        List<String> sectionsStrings = new ArrayList<>();
        String section = DEFAULT_SECTION_STRING;
        int counter = 0;

        for (String commandFileLine : commandFileLines) {
            // If the reviewed line is still inside the section part, the method concatenate it to "section":
            if (counter <= SECTION_SIZE) {
                section += (commandFileLine + LINE_SEPARATOR);
                counter +=1;
            }

            // Else it creates a new section string:
            else{
                sectionsStrings.add(section);
                counter = 1;
                section = (commandFileLine + LINE_SEPARATOR);
            }
        }

        sectionsStrings.add(section); // Adds the last section.

        return sectionsStrings;
    }

    /**
     *
     * @param warning
     * @throws FileProcessingWarning
     */
    private static void handleWarnings(FileProcessingWarning warning) throws  FileProcessingWarning{ // TODO: To compute lines. //TODO: It should print it in a certain order!
        int line = warning.getLine();
        System.err.println(WARNINGS_MESSAGE + line);
    }

    /**
     *
     * @param sectionsText
     * @return
     * @throws FileProcessingException
     */
    private static List<Section> testFactoriesExceptions(List<String> sectionsText)
            throws FileProcessingException{

        List<Section> sectionsList = new ArrayList<>();
        try{
            for (String sectionText: sectionsText) {
                sectionsList.add(SectionFactory.createSection(sectionText));
            }

        } catch (BadFormatError badFormatError){
            System.err.println(BAD_FORMAT_ERROR_MESSAGE);
            System.exit(1);

        } catch (BadSubSectionNameError badSubSectionNameError){
            System.err.println(BAD_SUBSECTION_NAME_MESSAGE);
            System.exit(1);

        } catch (FileProcessingWarning warning){
            handleWarnings(warning);
        }

        return sectionsList;
    }

    /**
     *
     * @param sectionsText
     * @return
     * @throws FileProcessingException
     */
    private static List<Section> createSections(List<String> sectionsText) throws FileProcessingException{
        return testFactoriesExceptions(sectionsText);
    }

    private static void printOutput(List<Section> sections){ // TODO: (to delete): uses the Section class method getFiles in order to get the list of files to print.

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
        List<String> sectionsTextList = createSectionsText(commandsFileLines);
        List<Section> sectionsList = createSections(sectionsTextList);
//
//        printOutput(sectionsList);

    }
}
