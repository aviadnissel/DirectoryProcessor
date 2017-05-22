/**
 * Created by Noy Sternlicht on 21-May-17.
 */

package fileprocessing.filters;
import fileprocessing.DirectoryProcessor;
import fileprocessing.exceptions.warnings.FileProcessingWarning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class creates Filter objects according to the FILTER subsection in command file.
 */
public class FilterFactory {

    /* --- Constants --- */

    private static final int NAME_INDEX = 0;
    private static final int ARGUMENT1_INDEX = 1;
    private static final int ARGUMENT2_INDEX = 2;
    private static final String BETWEEN_FILTER = "between";
    private static final String GREATER_THAN_FILTER = "greater_than";
    private static final String ALL_FILTER= "all";
    private static final String CONTAINS_FILTER = "contains";
    private static final String EXECUTABLE_FILTER = "executable";
    private static final String HIDDEN_FILTER = "hidden";
    private static final String FILE_FILTER = "file";
    private static final String PREFIX_FILTER = "prefix";
    private static final String SMALLER_THAN_FILTER = "smaller_than";
    private static final String SUFFIX_FILTER = "suffix";
    private static final String WRITABLE_FILTER = "writable";
    private static final String SEPARATOR = "#";
    private static final String EMPTY = "";


    /* --- Private Static Methods --- */

    /**
     * Splits The FILTER subsection by the character "#".
     * @param filterString: The FILTER subsection.
     * @return: A list contains the name, argument, and possibly the "NOT" suffix of the filter.
     */
    private static List<String> split(String filterString){
        String[] helper = filterString.split(SEPARATOR);
        List<String> data = new ArrayList<>();
        data.addAll(Arrays.asList(helper));
        return data;
    }

    private static boolean testInput(String name, String argument1, String argument2, String testKind){

        switch (testKind){
            case "domain negative doubles":
                if (argument2 != null){
                    return Double.parseDouble(argument1) >= 0 && Double.parseDouble(argument2) >= 0;
                }
                return Double.parseDouble(argument1) >= 0;

            case "bad filter name":
            String[] legalNames = {EMPTY, ALL_FILTER, BETWEEN_FILTER, CONTAINS_FILTER, EXECUTABLE_FILTER
            ,       FILE_FILTER, GREATER_THAN_FILTER, HIDDEN_FILTER, PREFIX_FILTER, SMALLER_THAN_FILTER,
                    SUFFIX_FILTER, WRITABLE_FILTER};
            return Arrays.asList(legalNames).contains(name);

            case "domain YES/NO":
                return argument1.equals("NO") || argument1.equals("YES");

            case "domain int":
                try{
                    Double.parseDouble(argument1);
                    if (argument2 != null){
                        Double.parseDouble(argument2);
                    }
                }catch(NumberFormatException numberException){
                    return false;
                }
                return true;

            case "domain legal between":
                return Double.parseDouble(argument1) <= Integer.parseInt(argument2);
        }
        return true;
    }

    /**
     * Finds out if a filter has "NOT" suffix.
     * @param data: A list contains the name, argument, and possibly the "NOT" suffix of the filter.
     * @return: True if the not flag should be turned on, false otherwise.
     */
    private static boolean ifNot(List<String> data){
        return data.get(data.size() - 1).equals("NOT");
    }


    /* --- Public Static Methods --- */

    /**
     * Creates a Filter object according the FILTER subsection in command file.
     * @param filterString: The FILTER subsection.
     * @return: The desired filtered object.
     */
    public static Filter createFilter(String filterString) throws FileProcessingWarning{

        List<String> data = split(filterString);
        Filter filter = new AllFilter(true); // Default filter.

        String name = data.get(NAME_INDEX);
        String filterArgument1 = data.get(ARGUMENT1_INDEX);
        boolean not = ifNot(data);

        switch (name){
            case EMPTY : filter = new AllFilter(not); // default filter.
                break;
            case GREATER_THAN_FILTER: filter = new GreaterThanFilter(filterArgument1, not);
                break;
            case BETWEEN_FILTER:
                String filterArgument2 = data.get(ARGUMENT2_INDEX);
                filter = new BetweenFilter(filterArgument1, filterArgument2, not);
                break;
            case ALL_FILTER: filter = new AllFilter(not); // TODO: Is it ok that it is a duplicate?
                break;
            case CONTAINS_FILTER: filter = new ContainsFilter(filterArgument1, not);
                break;
            case EXECUTABLE_FILTER: filter = new ExecutableFilter(filterArgument1, not);
                break;
            case FILE_FILTER: filter = new FileFilter(filterArgument1, not);
                break;
            case HIDDEN_FILTER: filter = new HiddenFilter(filterArgument1, not);
                break;
            case PREFIX_FILTER: filter = new PrefixFilter(filterArgument1, not);
                break;
            case SUFFIX_FILTER: filter = new SuffixFilter(filterArgument1, not);
                break;
            case  WRITABLE_FILTER: filter = new WritableFilter(filterArgument1, not);
                break;

        }
        return filter;
    }
}





