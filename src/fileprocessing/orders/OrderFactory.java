package fileprocessing.orders;

import fileprocessing.exceptions.warnings.BadNameWarning;
import fileprocessing.exceptions.warnings.FileProcessingWarning;

/**
 * A factory for creating Order out of a given string.
 *
 * @author Aviad Nissel, Noy Sternlicht
 */
public class OrderFactory {


    /* --- Constants --- */

    private final static String EMPTY_ORDER = "";
    private final static String ABS_ORDER = "abs";
    private final static String TYPE_ORDER = "type";
    private final static String SIZE_ORDER = "size";


    /* --- Public Static Methods --- */

    /**
     * Creates an order from the given string.
     * @param orderString The order string. One line only, without the ORDER line.
     *                    May have #REVERSE at the end. May be an empty string.
     * @return The appropriate Order object.
     */
    public static Order createOrder(String orderString)  throws FileProcessingWarning {
        String[] split = orderString.split("\\#");
        String actualOrder = split[0];
        boolean reverse = false;
        if (split.length > 1) {
            // We assume that after # comes REVERSE
            reverse = true;
        }
        switch (actualOrder) {
            case EMPTY_ORDER:
                // Same as ABS_ORDER, so no break!
            case ABS_ORDER:
                return new AbsOrder(reverse);
            case TYPE_ORDER:
                return new TypeOrder(reverse);
            case SIZE_ORDER:
                return new SizeOrder(reverse);
        }
        throw new BadNameWarning("Unknown order " + orderString, 1);
    }
}
