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
    private static final String WARNINGS_MESSAGE = "Warning in line ";
    private static final String BAD_FORMAT_ERROR_MESSAGE = "Error: The file format is not valid.";
    private static final String BAD_SUBSECTION_NAME_ERROR_MESSAGE = "Error: Subsections names are not valid.";

    /* --- Other Constants --- */
    private static final String LINE_SEPARATOR = "\n";
    private static final int LINE_NOT_NEEDED = -1;
    private static final int NUMBER_OF_LINES_IN_SECTION = 4;
    private static final int COMMANDS_FILE_INDEX = 1;
    private static final int SOURCE_DIRECTORY_INDEX = 0;
    private static final int VALID_COMMANDS_LINE_LENGTH = 2;
    private static final String EMPTY_SECTION_STRING = "";
    private static final String FILTER_SUBSECTION_TITLE = "FILTER";
    private static final String ORDER_SUBSECTION_TITLE = "ORDER";




    /*--- Private Static Methods ---*/

    /* --- Errors Testers --- */

    /**
     * Tests if the command line arguments are ok.
     * @param args: Command line arguments.
     * @throws InvalidUsageError
     */
    private static void testInvalidUsage(String[] args) throws InvalidUsageError {
        if(args.length != VALID_COMMANDS_LINE_LENGTH){
            throw new InvalidUsageError(INVALID_USAGE_ERROR_MESSAGE, LINE_NOT_NEEDED);
        }

        File sourceDir = new File(args[SOURCE_DIRECTORY_INDEX]);
        File commandsFile = new File(args[COMMANDS_FILE_INDEX]);

        if(!sourceDir.exists() || !commandsFile.exists()){
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

    /* --- Main Methods --- */

    private static List<String> createSectionStrings(List<String> commandFileLines) throws BadFormatError{
        List<String> sectionsStrings = new ArrayList<>();

        try {
             sectionsStrings = sectionsStringsHelper(commandFileLines);
        } catch (BadFormatError badFormatError){
            System.err.println(BAD_FORMAT_ERROR_MESSAGE);
            System.exit(1);
        }

        return sectionsStrings;
    }

    /**
     * Gets a list of commandsfile lines and unify each bundle of rows into a section text.
     * @param commandFileLines: A list of commandsfile lines.
     * @returns A list of sections strings.
     */
    private static List<String> sectionsStringsHelper(List<String> commandFileLines) throws BadFormatError{ // TODO: I have changed the signature after UML submission.
        List<String> sectionsStrings = new ArrayList<>();
        String section = EMPTY_SECTION_STRING;

        // If the first line in the file is not "FILTER" an error should be thrown:
        if(!commandFileLines.get(0).equals(FILTER_SUBSECTION_TITLE)){
            throw new BadFormatError(BAD_FORMAT_ERROR_MESSAGE, LINE_NOT_NEEDED);
        }

        for (String line: commandFileLines) {

            // The method concatenated all the relevant lines, and it creates a new section string:
            if(section.contains(ORDER_SUBSECTION_TITLE) && line.equals(FILTER_SUBSECTION_TITLE)){
                sectionsStrings.add(section);
                section = line + LINE_SEPARATOR; // The line is surly a proper FILTER header.
            }else{
                section += line + LINE_SEPARATOR;
            }
        }

        /*
         It might happen that a section string doesn't contains an "ORDER" subsection. In that
         case the method recognize it as the last (and incomplete) section in the file and WON'T add it to
          "sectionsStrings". If this scenario happens, a BedFormatError should be thrown:
         */
        if (!section.contains(ORDER_SUBSECTION_TITLE)){
            throw new BadFormatError(BAD_FORMAT_ERROR_MESSAGE, LINE_NOT_NEEDED);
        }

        // Adds the last section:
        sectionsStrings.add(section);
    return sectionsStrings;
    }

    /**
     * Prints a warning and the invalid line number (first line is indexed as 1).
     * @param warning : The warning object that was thrown.
     * @throws FileProcessingWarning
     */
    private static void handleWarning(FileProcessingWarning warning, Section section){

        int line = warning.getLine() +  (NUMBER_OF_LINES_IN_SECTION * section.getIndex());
        System.err.println(WARNINGS_MESSAGE + " " + line);
    }

    /**
     * Creates a list of sections objects and handles the warnings and errors that might occur as result.
     * @param sectionsStrings : A list of strings containing the data needed for creating sections objects.
     * @returns: A list of section objects.
     * @throws FileProcessingException
     */
    private static List<Section> createSectionsList(List<String> sectionsStrings)
            throws FileProcessingException{

        List<Section> sectionsList = new ArrayList<>();

        // Tries to create valid sections objects & handles warnings and errors if failed:
        try{
            Section currentSection;
            int counter = 0;

            for (String sectionString: sectionsStrings) {
                currentSection = SectionFactory.createSection(sectionString);
                currentSection.setIndex(counter);
                sectionsList.add(currentSection);
                counter += 1;
            }

        } catch (BadFormatError badFormatError){
            System.err.println(BAD_FORMAT_ERROR_MESSAGE);
            System.exit(1);

        } catch (BadSubSectionNameError badSubSectionNameError){
            System.err.println(BAD_SUBSECTION_NAME_ERROR_MESSAGE);
            System.exit(1);
        }
        return sectionsList;
    }

    /**
     * Prints the names of the files that were filtered and ordered by a Section object.
     * @param sectionsList : A list of sections objects.
     * @param sourceDir : The directory that contains the files we wish to filter and order.
     */
    private static void printOutput(List<Section> sectionsList, File sourceDir){
        List<File> outputFiles;
        List<FileProcessingWarning> sectionWarnings;

        for (Section section: sectionsList) {
            outputFiles = section.getFiles(sourceDir);
            sectionWarnings = section.getWarnings();

            for (FileProcessingWarning warning: sectionWarnings) {
                handleWarning(warning, section);
            }
            for (File file: outputFiles) {
                System.out.println(file.getName());
            }
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
        List<String> sectionsStringsList = createSectionStrings(commandsFileLines);
        List<Section> sectionsList = createSectionsList(sectionsStringsList);
        File sourceDir = new File(args[SOURCE_DIRECTORY_INDEX]);

        printOutput(sectionsList, sourceDir);
    }
}