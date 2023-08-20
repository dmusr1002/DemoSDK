package org.lotr.data.filters.core;


import java.util.ArrayList;
import java.util.List;

/**
 * A class for managing filter data and a limit for filtering operations.
 *
 * This class provides functionality to manage a collection of filter content objects
 * and allows you to set a limit on filtering operations. You can add filter content items
 * and set a limit to control the number of items processed during filtering.
 */
public class FilterData {
    List<FilterContent> filterData = new ArrayList<>();
    int limit = -1;


    /**
     * Add a filter content item to the collection.
     *
     * @param filterContent The filter content item to add.
     */
    public void addFilter(FilterContent filterContent) {
        this.filterData.add(filterContent);
    }

    /**
     * Set a limit on the number of items to be processed per request.
     * Not a mandatory argument. If not provided, no limit will be set on the API calls.
     * @param limit The limit value. It must be greater than 1.
     * @throws Exception If the provided limit value is less than or equal to 1.
     */
    public void setLimit(int limit) throws Exception {
        if (limit <= 1)
            throw new Exception("Provide a limit value greater than 0");

        this.limit = limit;
    }

    /**
     * Get the current limit set for the API requests.
     *
     * @return The limit value.
     */
    public int getLimit() {
        return this.limit;
    }

    /**
     * Get the collection of filter content items.
     *
     * @return A list of filter content items.
     */
    public List<FilterContent> getFilterContent() {
        return filterData;
    }
}
