package com.example.javaexamples.sort;

import java.util.List;

public interface Sorter {
    public <T> List<T> sort(List<T> itemsToSort, SortOrder order, List<String> sortFields);
    public <T> void sortInPlace(List<T> itemsToSort, SortOrder order, List<String> sortFields);
}
