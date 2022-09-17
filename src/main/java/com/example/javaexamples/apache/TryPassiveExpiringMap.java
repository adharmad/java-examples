package com.example.javaexamples.apache;

import org.apache.commons.collections4.map.PassiveExpiringMap;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TryPassiveExpiringMap {
    public static void main(String[] args) {
        final Map<String, Integer> m = new HashMap<>();
        m.put("one", Integer.valueOf(1));
        m.put("two", Integer.valueOf(2));
        m.put("three", Integer.valueOf(3));

        PassiveExpiringMap.ConstantTimeToLiveExpirationPolicy<String, Integer>
                expirationPolicy = new PassiveExpiringMap.ConstantTimeToLiveExpirationPolicy<>(
                5, TimeUnit.SECONDS);

        PassiveExpiringMap<String, Integer> expiringMap = new PassiveExpiringMap<>(expirationPolicy, m);

        int initialCapacity = expiringMap.size();
        System.out.println("initialCapacity = " + initialCapacity);

        System.out.println("Sleeping...");
        try { Thread.sleep(10000L); } catch (InterruptedException e) { }

        int updatedCapacity = expiringMap.size();
        System.out.println("updatedCapacity = " + updatedCapacity);
        Integer one = expiringMap.get("one");
        System.out.println(one);
        System.out.println("updatedCapacity = " + updatedCapacity);
    }
}
