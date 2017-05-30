package filesprocessing.orders;

import java.io.File;
import java.util.Comparator;

/**
 * Order by file size.
 *
 * @author Aviad Nissel, Noy Sternlicht
 */
public class SizeOrder extends Order {


    /* --- Constructors --- */

    public SizeOrder(boolean reversed) {
        super(reversed);
    }


    /* --- Order Impl. --- */

    @Override
    protected Comparator<File> getComparator() {
        return Comparator.comparing(File::length).thenComparing(File::getAbsolutePath);
    }
}
