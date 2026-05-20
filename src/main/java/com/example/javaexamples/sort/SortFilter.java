package com.example.javaexamples.sort;

import org.apache.commons.lang3.reflect.FieldUtils;

import java.util.Comparator;
import java.util.List;

public class SortFilter {
    private String fieldName;
    private SortOrder sortOrder;

    public SortFilter(String fieldName, SortOrder sortOrder) {
        this.fieldName = fieldName;
        this.sortOrder = sortOrder;
    }

    public Comparator itemComparator() {
        return (item1, item2) -> {
            String val1 = (String) getFieldValue(item1, fieldName);
            String val2 = (String) getFieldValue(item2, fieldName);
            return (sortOrder == SortOrder.ASCENDING ? val1.compareTo(val2) : val2.compareTo(val1));
        };
    }

    public static  <T> Comparator<T> chainedItemComparators(List<SortFilter> filters) {
        return filters.stream()
                .map(SortFilter::itemComparator)
                .reduce((item1, item2) -> 0, (f1, f2) -> f1.thenComparing(f2));
    }

    public static Object getFieldValue(Object obj, String fieldName) {
        try {
            return FieldUtils.readDeclaredField(obj, fieldName, true);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
