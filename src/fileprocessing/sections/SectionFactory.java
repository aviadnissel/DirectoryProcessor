package fileprocessing.sections;

import fileprocessing.exceptions.FileProcessingException;
import fileprocessing.exceptions.errors.BadFormatError;
import fileprocessing.exceptions.errors.BadSubSectionNameError;
import fileprocessing.exceptions.errors.FileProcessingError;
import fileprocessing.exceptions.warnings.FileProcessingWarning;
import fileprocessing.filters.Filter;
import fileprocessing.filters.FilterFactory;
import fileprocessing.orders.Order;
import fileprocessing.orders.OrderFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A factory for Sections.
 *
 * @author Aviad Nissel, Noy Sternlicht
 */
public class SectionFactory {

    private final static String FILTER = "FILTER";

    private final static String ORDER = "ORDER";

    public static Section createSection(String sectionString) throws FileProcessingException {
        String[] linesArray = sectionString.split("\n");
        List<String> lines = new ArrayList<>(Arrays.asList(linesArray));

        int curLine = 1;
        String filterString = "";
        String orderString = "";

        if (!lines.get(0).equals(FILTER)) {
            throw new BadSubSectionNameError("FILTER not found", curLine);
        }

        lines.remove(0);
        curLine += 1;
        int filterLine = curLine;

        if (!lines.get(0).equals(ORDER)) {
            filterString = lines.get(0);
            lines.remove(0);
            curLine  += 1;
        }

        if (!lines.get(0).equals(ORDER)) {
            throw new BadSubSectionNameError("ORDER not found", curLine);
        }

        lines.remove(0);
        curLine += 1;
        int orderLine = curLine;

        if (lines.size() != 0) {
            orderString = lines.get(0);
        }

        Filter filter;
        Order order;
        List<FileProcessingWarning> warnings = new ArrayList<>();
        try {
             filter = FilterFactory.createFilter(filterString);
        } catch (FileProcessingWarning e) {
            e.setLine(e.getLine() + filterLine);
            warnings.add(e);
            filter = FilterFactory.createDefaultFilter();
        }
        try {
            order = OrderFactory.createOrder(orderString);
        } catch (FileProcessingWarning e) {
            e.setLine(e.getLine() + orderLine);
            warnings.add(e);
            order = OrderFactory.createDefaultOrder();
        }
        return new Section(filter, order, warnings);
    }
}
