package fileprocessing.orders;

import java.io.File;
import java.util.Comparator;

/**
 * Order by file type.
 *
 * @author Aviad Nissel, Noy Stern-Licht
 */
public class TypeOrder extends Order {


    /* --- Constructors --- */

    public TypeOrder(boolean reversed) {
        super(reversed);
    }


    /* --- Order Impl. --- */

    @Override
    protected Comparator<File> getComparator() {
        return Comparator.comparing(f -> {
            String[] split = f.getName().split("\\.");
            if (split.length == 1) {
                return "";
            }
            return split[split.length - 1];
        } );
    }
}
