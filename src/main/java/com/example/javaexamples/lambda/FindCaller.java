package com.example.javaexamples.lambda;

import java.util.function.Predicate;

public class FindCaller {
    private FindObject[] findObjects = new FindObject[100];
    private int findIndex;
    private String findValue;

    public FindCaller() {
        for (int i=0 ; i<findObjects.length ; i++) {
            FindObject findObject = new FindObject();
            findObject.setFoo("foo");
            findObject.setBar("bar");
            findObjects[i] = findObject;
        }

        findIndex = 0;
        findValue = "hello";

        findObjects[90].setFoo("hello");
    }

    public static void main(String[] args) {
        FindCaller findCaller = new FindCaller();

        Predicate<FindObject> predicate = (o) -> o.getFoo().equals(findCaller.findValue);

        Runnable r = () -> {
            System.out.println("find value " + findCaller.findValue);
            System.out.println("found it!");
        };

        //FindTest.findIt(findCaller.findObjects, findCaller.findIndex, predicate, r, () -> {});
        FindTest.findIt(findCaller.findObjects, findCaller.findIndex, predicate, r, () -> {});
    }

}
