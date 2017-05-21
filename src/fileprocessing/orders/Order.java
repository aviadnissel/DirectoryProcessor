package fileprocessing.orders;

import java.io.File;
import java.util.List;

/**
 * Created by Noy on 21-May-17.
 */
abstract public class Order {
    private final boolean reversed;

    public Order(boolean reversed){
        this.reversed    = reversed;
    }
    abstract public List<File> order(List<File> toOrder);
}
