package com.example.javaexamples.sort;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class SortFileOrganizationInMemory<T> implements SortFileOrganization {
    private List<T> itemsToSort = new ArrayList<>();
    private Iterator<T> iterator = null;
    private Sorter sorter = new InMemorySorter();

    public SortFileOrganizationInMemory() {
    }

    public List<T> getItemsToSort() {
        return itemsToSort;
    }

    @Override
    public void sort(SortOrder order, List<String> sortFields) {
        sorter.sort(itemsToSort, order, sortFields);
    }

    @Override
    public void release(Object obj) {
        T t = (T)obj;
        itemsToSort.add(t);
    }

    @Override
    public void returnSort(Consumer<String> obj, Runnable atEnd, Runnable notAtEnd) {

        if (iterator == null) {
            iterator = itemsToSort.listIterator();
        }

        boolean isAtEnd = !iterator.hasNext();

        if (isAtEnd) {
            atEnd.run();
        } else {
            T nextElement = iterator.next();
            obj.accept(nextElement.toString());
//            String serObj = SerializerUtils.serialize(nextElement);
//            SerializerUtils.deserializeIntoObject(obj, serObj, obj.getClass());
            notAtEnd.run();
        }
    }
}