package com.example.javaexamples.lambda;

import java.util.*;
import java.util.function.Predicate;

public class TestSearchCriteria {
    public static class Person {
        private String name;
        private Integer age;

        private Person() {
        }

        private Person(Person.Builder builder) {
            this.name = builder.name;
            this.age = builder.age;
        }

        public String getName() {
            return name;
        }

        public Integer getAge() {
            return age;
        }

        public static class Builder {

            private String name;
            private Integer age;

            public Person.Builder name(String name) {
                this.name = name;
                return this;
            }

            public Person.Builder age(Integer age) {
                this.age = age;
                return this;
            }

            public Person build() {
                return new Person(this);
            }
        }

        public static List<Person> getPersonList() {
            List<Person> personList = new LinkedList<>();
            personList.add(new Person.Builder().name("Alan Turing").age(39).build());
            personList.add(new Person.Builder().name("Claude Shannon").age(60).build());
            personList.add(new Person.Builder().name("John Von Newmann").age(65).build());
            personList.add(new Person.Builder().name("Don Knuth").age(100).build());
            personList.add(new Person.Builder().name("Grace Hopper").age(15).build());
            personList.add(new Person.Builder().name("Dennis Ritchie").age(35).build());

            return personList;
        }
    }

    public static void main(String[] args) {

        List<Person> personList = Person.getPersonList();

        Map<String, Predicate<Person>> searchMap = new HashMap<>();
        Predicate<Person> allDrivers = p -> p.getAge() >= 16;
        Predicate<Person> allDraftees = p -> p.getAge() >= 18 && p.getAge() <= 40;
        Predicate<Person> allPilots = p -> p.getAge() >= 30 && p.getAge() <= 60;
        Predicate<Person> allRetirees = p -> p.getAge() >= 65;
        searchMap.put("allDrivers", allDrivers);
        searchMap.put("allDraftees", allDraftees);
        searchMap.put("allPilots", allPilots);
        searchMap.put("allRetirees", allRetirees);

        // Get sum of ages of all pilots
        long totalAgeOfPilots = personList
            .stream()
            .filter(searchMap.get("allPilots"))
            .mapToInt(p -> p.getAge())
            .sum();
        System.out.println("Total of all pilots = " + totalAgeOfPilots);

        // Get average of ages of all retirees
        OptionalDouble avgAgeOfAllRetirees = personList
                .stream()
                .filter(searchMap.get("allRetirees"))
                .mapToDouble(p -> p.getAge())
                .average();
        System.out.println("Average of all retirees = " + avgAgeOfAllRetirees);
    }

}
