package com.example.javaexamples.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemorySorter implements Sorter {

    @Override
    public <T> List<T> sort(List<T> itemsToSort, SortOrder order, List<String> sortFields) {
        List<T> sortedList = new ArrayList<>();
        List<SortFilter> filters = sortFields.stream().map(sortField -> new SortFilter(sortField, order))
                .collect(Collectors.toList());
        itemsToSort.sort(SortFilter.chainedItemComparators(filters));
        return itemsToSort;
    }

    @Override
    public <T> void sortInPlace(List<T> itemsToSort, SortOrder order, List<String> sortFields) {
        List<SortFilter> filters = sortFields.stream().map(sortField -> new SortFilter(sortField, order))
                .collect(Collectors.toList());
        itemsToSort.sort(SortFilter.chainedItemComparators(filters));
        return;
    }
}
