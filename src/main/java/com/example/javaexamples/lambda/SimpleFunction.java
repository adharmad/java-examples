package com.example.javaexamples.lambda;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class SimpleFunction {
    public static class Person {
        String first;
        String middle;
        String last;

        private Person() {
        }

        private Person(Person.Builder builder) {
            this.first = builder.first;
            this.last = builder.last;
            this.middle = builder.middle;
        }

        public String getFirst() {
            return first;
        }

        public String getLast() {
            return last;
        }

        public String getMiddle() {
            return middle;
        }

        public String printCustom(Function<Person, String> f) {
            return f.apply(this);
        }

        public static class Builder {

            private String first;
            private String middle;
            private String last;

            public Person.Builder first(String first) {
                this.first = first;
                return this;
            }

            public Person.Builder middle(String middle) {
                this.middle = middle;
                return this;
            }

            public Person.Builder last(String last) {
                this.last = last;
                return this;
            }

            public Person build() {
                return new Person(this);
            }
        }

        public static List<Person> getPersonList() {
            List<Person> personList = new LinkedList<>();
            personList.add(
                    new Person.Builder()
                            .first("Alan")
                            .middle("X")
                            .last("Turing")
                            .build()
            );

            personList.add(
                    new Person.Builder()
                            .first("Claude")
                            .middle("Y")
                            .last("Shannon")
                            .build()
            );

            personList.add(
                    new Person.Builder()
                            .first("John")
                            .middle("Z")
                            .last("VonNewmann")
                            .build()
            );

            return personList;
        }
    }

    public static void main(String[] args) {
        List<Person> personList = Person.getPersonList();

        Function<Person, String> firstMiddleLast = p -> {
            return p.getFirst() + " " + p.getMiddle() + " " + p.getLast();
        };

        Function<Person, String> lastFirst = p -> {
            return p.getLast() + ", " + p.getFirst();
        };

        System.out.println("Printing persons with format 'First Middle Last'");
        for (Person p : personList) {
            System.out.println(p.printCustom(firstMiddleLast));
        }

        System.out.println("Printing persons with format 'Last, First'");
        for (Person p : personList) {
            System.out.println(p.printCustom(lastFirst));
        }

    }
}
