package filesprocessing.sections;

import filesprocessing.exceptions.warnings.FileProcessingWarning;
import filesprocessing.filters.Filter;
import filesprocessing.orders.Order;

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
    private List<FileProcessingWarning> warnings;
    private int numOfLines;


    /* --- Constructors --- */

    public Section(Filter filter, Order order, List<FileProcessingWarning> warnings, int numOfLines) {
        this.filter = filter;
        this.order = order;
        this.warnings = warnings;
        this.numOfLines = numOfLines;
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
        //noinspection ConstantConditions
        for (File file: filesAndDirectories) {
            if (file.isFile()) {
                files.add(file);
            }
        }
        List<File> filteredFiles = filter.filterFiles(files);
        return order.order(filteredFiles);
    }

    public List<FileProcessingWarning> getWarnings() {
        return warnings;
    }

    public int getNumOfLines() {
        return numOfLines;
    }
}
