package com.example.javaexamples.json;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Map2Json {
    public static void main(String[] args) {
        Map<String, Object> valMap = initializeMap();
        Gson gson = new Gson();
        String json = gson.toJson(valMap);

        System.out.println(json);
    }

    public static Map<String, Object> initializeMap() {
        Map<String, Object> m = new HashMap<>();
        m.put("BOO", "booval");
        m.put("FOO", "fooval");
        m.put("TOO", "tooval");
        m.put("NUMBIRDS", "100");
        m.put("NUMANIMALS", "123");
        m.put("NUMBACTERIA", "4.4");

        Map<String, Object> m1 = new HashMap<>();
        m1.put("FIELD1", "field1val");
        m1.put("FIELD2", "field2val");
        m1.put("FIELD3", "field3val");
        List<Map<String, Object>> lst1 = new ArrayList<>();
        lst1.add(m1);
        m.put("EMBOBJ", lst1);

        List<Map<String, Object>> lst = new ArrayList<>();
        for (int i=0 ; i<3 ; i++) {
            Map<String, Object> m2 = new HashMap<>();
            m2.put("AFIELD", "afieldval");
            m2.put("BFIELD", "bfieldval");
            m2.put("CFIELD", "cfieldval");

            lst.add(m2);
        }
        m.put("LSTTYPOBJ", lst);

        return m;
    }
}
