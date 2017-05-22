/**
 * Created by Noy Sternlicht on 21-May-17.
 */

package fileprocessing.filters;
import fileprocessing.exceptions.warnings.FileProcessingWarning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class creates Filter objects according to the FILTER subsection in command file.
 */
public class FilterFactory {

    /* --- Int Constants --- */

    private static final int NAME_INDEX = 0;
    private static final int ARGUMENT1_INDEX = 1;
    private static final int ARGUMENT2_INDEX = 2;
    private static final int DOMAIN_NON_NEGATIVE_TEST = 0;
    private static final int DOMAIN_YES_NO_TEST = 2;
    private static final int DOMAIN_IS_DOUBLE_TEST = 3;
    private static final int DOMAIN_BETWEEN_TEST = 4;


    /* --- Filters Names & String Constants --- */

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
    private static final String LEGAL_NO = "NO";
    private static final String LEGAL_YES = "YES";


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

    /**
     * Tests if the input is double.
     * @param argument1: A given string.
     * @param argument2: Null unless the filter is between (Than it is a string too).
     * @returns: True if the inputs are doubles, false otherwise.
     */
    private static boolean testIfDouble(String argument1, String argument2){
        try{
            Double.parseDouble(argument1);
            if (argument2 != null){
                Double.parseDouble(argument2);
            }
        }catch(NumberFormatException numberException){
            return false;
        }
        return true;
    }

    /**
     * Tests if the inputs are non negatives.
     * @param argument1: A given string.
     * @param argument2: Null unless the filter is between. Otherwise a string too.
     * @returns: True if the inputs are non negatives, false otherwise.
     */
    private static boolean isNonNegative(String argument1, String argument2){
        if (argument2 != null){
            return Double.parseDouble(argument1) >= 0 && Double.parseDouble(argument2) >= 0;
        }
        return Double.parseDouble(argument1) >= 0;
    }


    /**
     * Tests if the filter's name & arguments are legal.
     * @param name: The filter's name.
     * @param argument1: The filter's firs argument.
     * @param argument2: The filter's second argument (null otherwise the tested filter is between).
     * @param testKind: The kind of the test we wish to preform.
     * @returns: True if the input is valid, false otherwise.
     */
    private static boolean testInput(String name, String argument1, String argument2, int testKind){

        switch (testKind){
            case DOMAIN_NON_NEGATIVE_TEST:
               return isNonNegative(argument1, argument2);
            case DOMAIN_YES_NO_TEST:
                return argument1.equals(LEGAL_NO) || argument1.equals(LEGAL_YES);
            case DOMAIN_IS_DOUBLE_TEST:
                return testIfDouble(argument1, argument2);
            case DOMAIN_BETWEEN_TEST:
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
            case SMALLER_THAN_FILTER: filter = new SmallerThanFilter(filterArgument1, not);
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


//TODO: Delete if not needed:

//    /**
//     * Tests if a filter name is legal.
//     * @param name: The name to be tested.
//     * @returns: True if the name is legal, false otherwise.
//     */
//    private static boolean isNameLegal(String name){
//        String[] legalNames = {EMPTY, ALL_FILTER, BETWEEN_FILTER, CONTAINS_FILTER, EXECUTABLE_FILTER
//                ,       FILE_FILTER, GREATER_THAN_FILTER, HIDDEN_FILTER, PREFIX_FILTER, SMALLER_THAN_FILTER,
//                SUFFIX_FILTER, WRITABLE_FILTER};
//        return Arrays.asList(legalNames).contains(name);
//    }



//            case BAD_NAME_TEST:
//                return isNameLegal(name);



//    private static final int BAD_NAME_TEST = 1;

