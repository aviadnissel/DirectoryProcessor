package fileprocessing.sections;

import fileprocessing.filters.Filter;
import fileprocessing.orders.Order;

import java.io.File;
import java.util.List;

/**
 * Created by Noy on 21-May-17.
 */
public class Section {
    private final Filter filter;
    private final Order order;

    public Section(Filter filter, Order order){
        this.filter = filter;
        this.order = order;
    }
    public List<File> getFiles(File sourceDir){
        return null;
    }
}
