package org.lotr.domain.filters;

import org.lotr.data.filters.core.FilterContent;
import org.lotr.data.filters.core.FilterData;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * A utility class for processing filter data and generating query parameters for API requests.
 */
public class FilterProcessor {


    /**
     * Generates query parameters based on the provided filter data.
     *
     * @param filterData The filter data containing filter criteria.
     * @return A map of query parameters where keys represent query parameter names and values represent
     * query parameter values.
     */
    public static Map<String, String> getQueryParameters(FilterData filterData) {
        Map<String, String> queryParameters = new HashMap<>();

        for (FilterContent filterContent : filterData.getFilterContent()) {
            String queryKey = "";
            String queryValue = "";

            switch (filterContent.filterOption()) {

                case MATCH, INCLUDE, REGEX -> {
                    queryKey = filterContent.filterField().getFieldName() + "=";
                    queryValue = filterContent.filterValue();
                }
                case NEGATE_MATCH, EXCLUDE -> {
                    queryKey = String.format("%s%s", filterContent.filterField().getFieldName(), "!=");
                    queryValue = filterContent.filterValue();
                }
                case EXISTS -> {
                    queryKey = filterContent.filterField().getFieldName();
                    queryValue = "";
                }
                case DOES_NOT_EXIST -> {
                    queryKey = String.format("%s%s", "!", filterContent.filterField().getFieldName());
                    queryValue = "";
                }
                case LESS_THAN -> {
                    queryKey = String.format("%s%s", filterContent.filterField().getFieldName(), "<");
                    queryValue = filterContent.filterValue();
                }
                case GREATER_THAN -> {
                    queryKey = String.format("%s%s", filterContent.filterField().getFieldName(), ">");
                    queryValue = filterContent.filterValue();
                }
                case GREATER_THAN_OR_EQUAL_TO -> {
                    queryKey = String.format("%s%s", filterContent.filterField().getFieldName(), ">=");
                    queryValue = filterContent.filterValue();
                }
            }

            if (!queryKey.isBlank()) {
                queryParameters.putIfAbsent(queryKey, queryValue);
            }
        }

        int limit = filterData.getLimit();
        if (limit >= 1)
            queryParameters.putIfAbsent("limit", String.valueOf(limit));

        return queryParameters;
    }

    private static String processInternalQueryParameters(Map<String, String> queryMap) {
        return "?" + queryMap.entrySet().stream()
                .map(entry -> {
                    String key = URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8);
                    String value = URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8);
                    return key + value;
                })
                .collect(Collectors.joining("&"));
    }

    public static String processQueryParameters(FilterData filterData) {
        return processInternalQueryParameters(getQueryParameters(filterData));
    }
}
