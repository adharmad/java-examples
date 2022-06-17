package com.example.javaexamples.stream;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TryListToMap {
    public static void main(String[] args) {
        List<Address> lst = List.of(
                new Address("one", "name1"),
                new Address("two", "name2"),
                new Address("three", "name3"),
                new Address("four", "name4"),
                new Address("five", "name5")
        );

        Map<String, Address> map = new LinkedHashMap<>();
        map.putAll(lst.stream().collect(Collectors.toMap(
                Address::getId, Function.identity(), (u, v) -> {
                    throw new IllegalStateException(String.format("Duplicate key %s", u));
                }, LinkedHashMap::new
        )));

        System.out.println(map);
    }

    static class Address {
        private String id;
        private String name;
        public Address(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Address{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
