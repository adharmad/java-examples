package com.example.javaexamples.apache;

import org.apache.commons.collections4.map.PassiveExpiringMap;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TryPassiveExpiringMap2 {
    public static void main(String[] args) {
        PassiveExpiringMap.ConstantTimeToLiveExpirationPolicy<String, String>
                expirationPolicy = new PassiveExpiringMap.ConstantTimeToLiveExpirationPolicy<>(
                5, TimeUnit.SECONDS);

        PassiveExpiringMap<String, String> expiringMap = new PassiveExpiringMap<>(expirationPolicy);
        expiringMap.put("accesstoken1", RandomStringUtils.randomAlphabetic(10));
        expiringMap.put("accesstoken2", RandomStringUtils.randomAlphabetic(10));
        expiringMap.put("accesstoken3", RandomStringUtils.randomAlphabetic(10));

        System.out.println(expiringMap);
        int initialCapacity = expiringMap.size();
        System.out.println("initialCapacity = " + initialCapacity);

        System.out.println("Sleeping...");
        try {
            //Thread.sleep(10000L);
            Thread.sleep(3000L);
        } catch (InterruptedException e) { }

        int updatedCapacity = expiringMap.size();
        System.out.println("updatedCapacity = " + updatedCapacity);

        System.out.println(expiringMap);

    }
}
