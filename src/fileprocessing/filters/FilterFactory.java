/**
 * Created by Noy Sternlicht on 21-May-17.
 */

package fileprocessing.filters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class creates Filter objects according to the FILTER subsection in command file.
 */
public class FilterFactory {
    private static final int NAME_INDEX = 0;
    private static final String DEFAULT_NAME = "all";

    /**
     * Splits The FILTER subsection by the character "#".
     * @param filterString: The FILTER subsection.
     * @return: A list contains the name, argument, and possibly the "NOT" suffix of the filter.
     */
    public static List<String> split(String filterString){ // TODO: to test. //TODO: To make private.
        String[] helper = filterString.split("//#");
        List<String> data = new ArrayList<>();
        data.addAll(Arrays.asList(helper)); // TODO: Verify we can use it.
        return data;
    }

    /**
     * Finds out if a filter has "NOT" suffix.
     * @param data: A list contains the name, argument, and possibly the "NOT" suffix of the filter.
     * @return: True if the not flag should be turned on, false otherwise.
     */
    private static boolean ifNot(List<String> data){
        return data.get(data.size() - 1).equals("NOT");
    }

    /**
     * Creates a Filter object according the FILTER subsection in command file.
     * @param filterString: The FILTER subsection.
     * @return: The desired filtered object.
     */
    public static Filter createFilter(String filterString){ // TODO: to test
        List<String> data = split(filterString);
        Filter filter = new AllFilter(true); // Default filter.

        if (data.size() > 0){ // If filterString is not empty.
            String name = data.get(NAME_INDEX);
            if(!name.equals(DEFAULT_NAME)){
                String filterArgument1 = data.get(1);
                boolean not = ifNot(data);
                switch (name){
                    case "greater_than": filter = new GreaterThanFilter(filterArgument1, not); // TODO: Magic?
                        break;
                    case "between":
                        String filterArgument2 = data.get(1); // Todo: there might be a bad input (only 1 argument) **(Not mentioned in PDF)
                        filter = new BetweenFilter(filterArgument1, filterArgument2, not);
                        break;
                }
            }
        }
        return filter;
    }
}





