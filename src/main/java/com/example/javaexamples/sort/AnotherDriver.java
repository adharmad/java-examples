package com.example.javaexamples.sort;

import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class AnotherDriver {
    @Setter private String output;

    public static void main(String[] args) {
        AnotherDriver driver = new AnotherDriver();
        driver.doIt();
    }

    public void doIt() {
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

        SortFileOrganization sortFileOrganization = new SortFileOrganizationInMemory<Person>();

        for (int i=0 ; i<personList.size() ; i++) {
            sortFileOrganization.release(personList.get(i));
        }

        sortFileOrganization.sort(SortOrder.ASCENDING, List.of("firstName", "age"));

        final AtomicBoolean sortComplete = new AtomicBoolean(false);

        while (!sortComplete.get()) {
            sortFileOrganization.returnSort(this::setOutput,
                    () -> {
                        sortComplete.set(true);
                        System.out.println("We are done");
                    },
                    () -> {
                        System.out.println(output);
                    });
        }
    }
}
