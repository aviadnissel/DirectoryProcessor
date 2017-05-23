/**
 * Created by Noy Sternlicht on 21-May-17.
 */

package fileprocessing.filters;
import fileprocessing.exceptions.warnings.BadBetweenDomain;
import fileprocessing.exceptions.warnings.BadNameWarning;
import fileprocessing.exceptions.warnings.BadParametersWarning;
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
    private static final int DOMAIN_YES_NO_TEST = 0;
    private static final int DOMAIN_IS_LEGAL_DOUBLE_TEST = 1;
    private static final int DOMAIN_BETWEEN_TEST = 2;


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
    private static final String LEGAL_NO = "NO";
    private static final String LEGAL_YES = "YES";
    private static final String BAD_DOMAIN_YES_NO_MSG = "Argument mast be ether 'YES' or 'NO'.";
    private static final String BAD_DOMAIN_DOUBLE_MSG = "The filter gets only non-negative doubles.";
    private static final String BAD_DOMAIN_BETWEEN_MSG = "The second argument mast be bigger than the first.";
    private static final String BAD_FILTER_NAME_MSG = "The second argument mast be bigger than the first.";



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
            case DOMAIN_YES_NO_TEST:
                return argument1.equals(LEGAL_NO) || argument1.equals(LEGAL_YES);
            case DOMAIN_IS_LEGAL_DOUBLE_TEST:
                return testIfDouble(argument1, argument2) && isNonNegative(argument1, argument2); // TODO: test precedence
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
     * @throws:  FileProcessingWarning
     */
    public static Filter createFilter(String filterString) throws FileProcessingWarning{

        List<String> data = split(filterString);
        String name = data.get(NAME_INDEX);
        String argument1;
        boolean not = ifNot(data);

        switch (name){
            case GREATER_THAN_FILTER:
                argument1 = data.get(ARGUMENT1_INDEX);
                if(testInput(name, argument1, null, DOMAIN_IS_LEGAL_DOUBLE_TEST)){
                    return new GreaterThanFilter(argument1, not);
                }
                throw new BadParametersWarning(BAD_DOMAIN_DOUBLE_MSG, 1);

            case SMALLER_THAN_FILTER:
                argument1 = data.get(ARGUMENT1_INDEX);
                if(testInput(name,argument1, null, DOMAIN_IS_LEGAL_DOUBLE_TEST)){
                    return new SmallerThanFilter(argument1, not);
                }
                throw new BadParametersWarning(BAD_DOMAIN_DOUBLE_MSG, 1);

            case BETWEEN_FILTER:
                argument1 = data.get(ARGUMENT1_INDEX);
                String argument2 = data.get(ARGUMENT2_INDEX);

                if(testInput(name, argument1, argument2, DOMAIN_IS_LEGAL_DOUBLE_TEST)){
                    if(testInput(name, argument1, argument2, DOMAIN_BETWEEN_TEST)){
                        return new BetweenFilter(argument1, argument2, not);
                    }
                    throw new BadBetweenDomain(BAD_DOMAIN_BETWEEN_MSG, 1);
                }
                throw new BadParametersWarning(BAD_DOMAIN_DOUBLE_MSG, 1);

            case ALL_FILTER:
                return new AllFilter(not);

            case CONTAINS_FILTER:
                argument1 = data.get(ARGUMENT1_INDEX);
                return new ContainsFilter(argument1, not);

            case EXECUTABLE_FILTER:
                argument1 = data.get(ARGUMENT1_INDEX);
                if(testInput(name, argument1, null, DOMAIN_YES_NO_TEST)){
                    return new ExecutableFilter(argument1, not);
                }
                throw new BadParametersWarning(BAD_DOMAIN_YES_NO_MSG, 1);

            case FILE_FILTER:
                argument1 = data.get(ARGUMENT1_INDEX);
                return new FileFilter(argument1, not);

            case HIDDEN_FILTER:
                argument1 = data.get(ARGUMENT1_INDEX);
                if(testInput(name, argument1, null, DOMAIN_YES_NO_TEST)){
                    return new HiddenFilter(argument1, not);
                }
                throw new BadParametersWarning(BAD_DOMAIN_YES_NO_MSG, 1);

            case PREFIX_FILTER:
                argument1 = data.get(ARGUMENT1_INDEX);
                return new PrefixFilter(argument1, not);

            case SUFFIX_FILTER:
                argument1 = data.get(ARGUMENT1_INDEX);
                return new SuffixFilter(argument1, not);

            case  WRITABLE_FILTER:
                argument1 = data.get(ARGUMENT1_INDEX);
                if(testInput(name, argument1, null, DOMAIN_YES_NO_TEST)){
                    return new WritableFilter(argument1, not);
                }
                throw new BadParametersWarning(BAD_DOMAIN_YES_NO_MSG, 1);

            default: throw new BadNameWarning(BAD_FILTER_NAME_MSG, 1); // Todo: create filter CANNOT throw the exception and return all filter. main should call it again with the arguments needed for all filter.
        }
    }
}
