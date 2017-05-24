package fileprocessing.sections;

import fileprocessing.filters.Filter;
import fileprocessing.orders.Order;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * A section. Contains a Filter and an Order.
 *
 * @author Aviad Nissel, Noy Sternlicht
 */
public class Section {


    /* --- Data Members --- */

    private Filter filter;
    private Order order;


    /* --- Constructors --- */

    public Section(Filter filter, Order order){
        this.filter = filter;
        this.order = order;
    }


    /* --- Public Methods --- */

    /**
     * Filters the given source directory with Filter and returns a list of file ordered by Order.
     * @param sourceDir The source dir to look in.
     * @return An ordered list of filtered files.
     */
    public List<File> getFiles(File sourceDir) {
        File[] filesAndDirectories = sourceDir.listFiles();
        List<File> files = new ArrayList<>();
        for (File file: filesAndDirectories) {
            if (file.isFile()) {
                files.add(file);
            }
        }
        List<File> filteredFiles = filter.filterFiles(files);
        List<File> orderedFiles = order.order(filteredFiles);
        return orderedFiles;
    }
}
