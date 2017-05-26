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
    private int index;


    /* --- Constructors --- */

    public Section(Filter filter, Order order){
        this.filter = filter;
        this.order = order;
        this.warnings = new ArrayList<>();
        this.index = 0;
    }

    public Section(Filter filter, Order order, List<FileProcessingWarning> warnings) {
        this.filter = filter;
        this.order = order;
        this.warnings = warnings;
        this.index = 0;
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

    public List<FileProcessingWarning> getWarnings() {
        return warnings;
    }

    public void addWarning(FileProcessingWarning warning) {
        warnings.add(warning);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
