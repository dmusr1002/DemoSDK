package org.lotr.data.filters.core;


import org.lotr.data.filters.FilterField;

/**
 * A record representing a content filter with specific filter criteria.
 * This record stores information about a content filter, including the filter option,
 * filter field, and filter value. It is designed to simplify the management of content
 * filtering criteria in your application.
 *
 * @param filterOption The filter option specifying how the filter should be applied.
 * @param filterField  The filter field indicating which aspect of the content to filter.
 * @param filterValue  The value to use as the filter criterion.
 */
public record FilterContent(FilterOption filterOption, FilterField filterField, String filterValue) {
}