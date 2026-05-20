package com.example.javaexamples.sort;

import java.util.ArrayList;
import java.util.List;

public class SortDriver {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        personList.addAll(
                List.of(
                        new Person("Richard", "Feynman", "55"),
                        new Person("Murray", "Gell-Mann", "54"),
                        new Person("Enrico", "Fermi", "60"),
                        new Person("Johnny", "vonNeumann", "22"),
                        new Person("Kurt", "Godel", "77"),
                        new Person("Srinivasa", "Ramanujan", "40"),
                        new Person("Srinivasa", "Vardhan", "11"),
                        new Person("Alexander", "Grothendieck", "65")
                ));

        Sorter sorter = new InMemorySorter();
        sorter.sort(personList, SortOrder.ASCENDING, List.of("firstName", "age"));

        for (Person p : personList) {
            System.out.println(p);
        }
    }
}
