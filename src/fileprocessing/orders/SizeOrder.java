package fileprocessing.orders;

import java.io.File;
import java.util.Comparator;

/**
 * Order by file size.
 *
 * @author Aviad Nissel, Noy Stern-Licht
 */
public class SizeOrder extends Order {


    /* --- Constructors --- */

    public SizeOrder(boolean reversed) {
        super(reversed);
    }


    /* --- Order Impl. --- */

    @Override
    protected Comparator<File> getComparator() {
        return Comparator.comparing(File::length);
    }
}
