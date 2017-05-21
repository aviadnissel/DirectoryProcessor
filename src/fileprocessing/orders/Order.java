package fileprocessing.orders;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * An abstract Order. All orders inherit from it.
 *
 * @author Aviad Nissel, Noy Stern-Licht
 */
abstract public class Order {


    /* --- Data Members --- */

    private boolean reversed;


    /* --- Constructors --- */

    public Order(boolean reversed){
        this.reversed = reversed;
    }


    /* --- Public Methods --- */

    /**
     * Orders the given file list.
     *
     * @param toOrder The file list to order.
     * @return The ordered file list.
     */
    public List<File> order(List<File> toOrder) {
        toOrder.sort(getComparator());
        if (reversed) {
            Collections.reverse(toOrder);
        }
        return toOrder;
    }


    /* --- Abstract Methods --- */

    /**
     * Creates the comparator to order the files with. Used in the order method.
     * @return A Comparator object.
     */
    protected abstract Comparator<File> getComparator();

}
