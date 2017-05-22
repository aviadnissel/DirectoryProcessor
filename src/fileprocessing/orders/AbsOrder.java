package fileprocessing.orders;

import java.io.File;
import java.util.Comparator;

/**
 * Order by absolute path.
 *
 * @author Aviad Nissel, Noy Sternlicht
 */
public class AbsOrder extends Order {


    /* --- Constructors --- */

    public AbsOrder(boolean reversed) {
        super(reversed);
    }


    /* --- Order Impl. --- */

    @Override
    protected Comparator<File> getComparator() {
        return Comparator.comparing(File::getAbsolutePath);
    }
}
