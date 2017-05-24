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

    /*--- Errors And Warnings Messages --- */
    private static final String IO_ERROR_MESSAGE = "ERROR: An error occurred while accessing commandfile";
    private static final String INVALID_USAGE_ERROR_MESSAGE = "ERROR: Line arguments are invalid.";
    private static final String WARNINGS_MESSAGE = "Warning in line";
    private static final String BAD_FORMAT_ERROR_MESSAGE = "Error: The file format is not valid.";
    private static final String BAD_SUBSECTION_NAME_ERROR_MESSAGE = "Error: Subsections names are not valid.";

    /* --- Other Constants --- */
    private static final String LINE_SEPARATOR = "/n";
    private static final String DEFAULT_SECTION_STRING = "FILTER/nall/nORDER/nabs/n";
    private static final int LINE_NOT_NEEDED = -1;
    private static final int SECTION_SIZE = 3;
    private static final int NUMBER_OF_LINES_IN_SECTION = 4;
    private static final int COMMANDS_FILE_INDEX = 1;
    private static final int SOURCE_DIRECTORY_INDEX = 0;
    private static final int VALID_COMMANDS_LINE_LENGTH = 2;
    private static final String EMPTY_SECTION_STRING = "";




    /*--- Private Static Methods ---*/

    /* --- Errors Testers --- */

    /**
     * Tests if the command line arguments are ok.
     * @param args: Command line arguments.
     * @throws InvalidUsageError
     */
    private static void testInvalidUsage(String[] args) throws InvalidUsageError {
        File sourceDir = new File(args[SOURCE_DIRECTORY_INDEX]);
        File commandsFile = new File(args[COMMANDS_FILE_INDEX]);
        if((args.length != VALID_COMMANDS_LINE_LENGTH) || !sourceDir.exists() || !commandsFile.exists()){
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
        File commandsFile = new File(args[COMMANDS_FILE_INDEX]);
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
    private static List<String> createSectionsText(List<String> commandFileLines){

        List<String> sectionsStrings = new ArrayList<>();
        String section = EMPTY_SECTION_STRING;
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
     * Prints a warning and the invalid line number (first line is indexed as 1).
     * @param warning: The warning object that was thrown.
     * @param sectionIndex: The index of the section in the commands file (The first is indexed 0).
     * @throws FileProcessingWarning
     */
    private static void handleWarnings(FileProcessingWarning warning, int sectionIndex)
            throws  FileProcessingWarning{

        int line = warning.getLine() +  (NUMBER_OF_LINES_IN_SECTION * sectionIndex);
        System.err.println(WARNINGS_MESSAGE + line);
    }

    /**
     * Creates a section object and handles the warnings and errors that might occur as result.
     * @param sectionString: A string contains the data needed for creating a section object.
     * @param sectionIndex: The index of the section in the commands file (The first is indexed 0).
     * @returns: A section object.
     * @throws FileProcessingException
     */
    private static Section createSection(String sectionString, int sectionIndex)
            throws FileProcessingException{


        //Creates a default section. This is the section used in a case a warning was given:
        Section section = SectionFactory.createSection(DEFAULT_SECTION_STRING);

        // Tries to create a valid section object & handles warnings and errors if failed:
        try{
            section =  SectionFactory.createSection(sectionString);

        } catch (BadFormatError badFormatError){
            System.err.println(BAD_FORMAT_ERROR_MESSAGE);
            System.exit(1);

        } catch (BadSubSectionNameError badSubSectionNameError){
            System.err.println(BAD_SUBSECTION_NAME_ERROR_MESSAGE);
            System.exit(1);

        } catch (FileProcessingWarning warning){
            handleWarnings(warning, sectionIndex);
        }
        return section;
    }

    /**
     * Prints the names of the files that were filtered and ordered by a Section object.
     * @param currentSection: A section object.
     * @param sourceDir: The directory that contains the files we wish to filter and order.
     */
    private static void printOutput(Section currentSection, File sourceDir){
        List<File> outputFiles = currentSection.getFiles(sourceDir);
        for (File outPut: outputFiles) {
            System.out.println(outPut.getName());
        }
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
        List<String> sectionsStringsList = createSectionsText(commandsFileLines);
        File sourceDir = new File(args[SOURCE_DIRECTORY_INDEX]);
        Section currentSection;
        int sectionIndex = 0;

        for (String sectionString: sectionsStringsList) {   // TODO: to test it when Section is implemented!!!
            // Creates a section object and handle errors and warnings:
            currentSection = createSection(sectionString, sectionIndex);

            // Prints the ordered and filtered files:
            printOutput(currentSection, sourceDir);
            sectionIndex += 1;
        }
    }
}
