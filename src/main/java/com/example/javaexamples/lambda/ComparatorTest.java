package com.example.javaexamples.lambda;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Example copied from:
 * http://www.oracle.com/webfolder/technetwork/tutorials/obe/java/Lambda-QuickStart/index.html
 */
public class ComparatorTest {

    public static class Person {
        String name;
        Integer age;
        String phone;

        public Person(String name, Integer age, String phone) {
            this.name = name;
            this.age = age;
            this.phone = phone;
        }

        public String toString() {
            return name + ":" + age + ":" + phone;
        }

        public String getName() {
            return name;
        }

        public Integer getAge() {
            return age;
        }

        public String getPhone() {
            return phone;
        }

    }

    public static void main(String[] args) {
        List<Person> personList = new LinkedList<>();
        personList.add(new Person("zack", 100, "111-222-3333"));
        personList.add(new Person("yellow", 150, "444-999-7777"));
        personList.add(new Person("anonymous", 50, "555-000-4444"));

        System.out.println("Unsorted list: " + personList);

        // Sort with Inner Class by name
        Collections.sort(personList, new Comparator<Person>(){
            public int compare(Person p1, Person p2){
                return p1.getName().compareTo(p2.getName());
            }
        });

        System.out.println("Sorted by Name: " + personList);

        // Sort with Inner Class by name
        Collections.sort(personList, new Comparator<Person>(){
            public int compare(Person p1, Person p2){
                return p1.getName().compareTo(p2.getName());
            }
        });

        System.out.println("Sorted by Name: " + personList);

        // Sort using lambdas by age
        Collections.sort(personList, (Person p1, Person p2) -> p1.getAge().compareTo(p2.getAge()));

        System.out.println("Sorted by Age: " + personList);

    }

}
