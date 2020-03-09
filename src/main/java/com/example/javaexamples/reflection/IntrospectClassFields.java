package com.example.javaexamples.reflection;

import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.*;

public class IntrospectClassFields {
    public static final Set<String> NATIVE_TYPES = new HashSet<>(Arrays.asList("int", "long", "double", "float", "char", "byte"));

    public static void main(String[] args) throws Exception {
        Class c = Class.forName("com.example.javaexamples.reflection.Zoo");

        //Map<String, String> fieldAccessorMap = getFieldsMap(c);
        //System.out.println(fieldAccessorMap);

        //Zoo zoo = new Zoo();

        //System.out.println(invokeMethod(zoo, "getEmbObj().getField3()"));

        Map<String, List<String>> objFieldMap = getObjectFieldMap(c);
        Map<String, Object> valMap = initializeMap();
        //System.out.println(objFieldMap);

        //System.out.println(valMap);
        Map<String, String> flatMap = getFlatMap(objFieldMap);
        //System.out.println(flatMap);

        Map<String, Object> newValMap = fixValMap(valMap, flatMap);
        //System.out.println(newValMap);
        Gson gson = new Gson();
        String json = gson.toJson(newValMap);

        System.out.println(json);

    }

    public static Map<String, Object> fixValMap(Map<String, Object> valMap, Map<String, String> flatMap) {
        Map<String, Object> newMap = new HashMap<>();

        for (Map.Entry<String, Object> val : valMap.entrySet()) {
            if (flatMap.containsKey(val.getKey()) && !(val.getValue() instanceof List)) {
                newMap.put(flatMap.get(val.getKey()), val.getValue());
            } else {

                if (val.getValue() instanceof List) {
                    List<Map<String, String>> innerMapLst = (List<Map<String, String>>) val.getValue();

                    if (innerMapLst.size() == 1) {
                        Map<String, String> innerMap = innerMapLst.get(0);
                        Map<String, String> newInnerMap = new HashMap<>();
                        for (Map.Entry<String, String> entry : innerMap.entrySet()) {
                            String key = val.getKey() + "." + entry.getKey();
                            String newKey = flatMap.get(key);

                            newInnerMap.put(newKey, entry.getValue());
                        }

                        newMap.put(flatMap.get(val.getKey()), newInnerMap);

                    } else if (innerMapLst.size() > 1) {
                        List<Map<String, String>> newLst = new ArrayList();

                        for (Map<String, String> lstElem : innerMapLst) {
                            Map<String, String> newInnerMap = new HashMap<>();
                            for (Map.Entry<String, String> entry : lstElem.entrySet()) {
                                String key = val.getKey() + "." + entry.getKey();
                                String newKey = flatMap.get(key);

                                newInnerMap.put(newKey, entry.getValue());
                            }
                            newLst.add(newInnerMap);
                        }

                        newMap.put(flatMap.get(val.getKey()), newLst);
                    }
                }
            }
        }

        return newMap;
    }

    public static Map<String, List<String>> getObjectFieldMap(Class cls) throws Exception {
        Map<String, List<String>> fieldTypeMap = new HashMap<>();

        for (Field f : cls.getDeclaredFields()) {
            String name = f.getName();
            String type = f.getType().getName();

            if (NATIVE_TYPES.contains(type) || type.startsWith("java")) {
                fieldTypeMap.put(name, null);
            } else {
                Class c = Class.forName(type);
                List<String> fList = new ArrayList<>();

                for (Field f1 : c.getDeclaredFields()) {
                    fList.add(f1.getName());
                    String fType = f1.getType().getName();

                    if (f1.getType().equals(List.class)) {
                        ParameterizedType ff1 = (ParameterizedType) f1.getGenericType();
                        Class<?> ff1Cls = (Class<?>) ff1.getActualTypeArguments()[0];

                        if (!NATIVE_TYPES.contains(ff1Cls.getName()) && !ff1Cls.getName().startsWith("java")) {
                            fieldTypeMap.put(f1.getName(), getFieldsList(ff1Cls));
                        }

                    }
                }

                fieldTypeMap.put(name, fList);
            }
        }

        return fieldTypeMap;
    }

    public static List<String> getFieldsList(Class cls) throws Exception {
        List<String> fList = new ArrayList<>();

        for (Field f : cls.getDeclaredFields()) {
            String name = f.getName();
            fList.add(name);
        }

        return fList;
    }

    public static Map<String, String> getFlatMap(Map<String, List<String>> m) {
        Map<String, String> flatm = new HashMap<>();
        String lstParentKey = "";
        String lstChildKey = "";

        for (Map.Entry<String, List<String>> entry : m.entrySet()) {
            if (entry.getValue() == null) {
                flatm.put(entry.getKey().toUpperCase(), entry.getKey());
            } else {
                flatm.put(entry.getKey().toUpperCase(), entry.getKey());

                if (entry.getValue().size() == 1) {
                    lstParentKey = entry.getKey();
                    lstChildKey = entry.getValue().get(0);
                }

                if (entry.getValue().size() > 1) {
                    if (entry.getKey().equals(lstChildKey)) {
                        for (String elem : entry.getValue()) {
                            flatm.put(lstParentKey.toUpperCase() + "." + elem.toUpperCase(), elem);
                        }
                    } else {
                        for (String elem : entry.getValue()) {
                            flatm.put(entry.getKey().toUpperCase() + "." + elem.toUpperCase(), elem);
                        }
                    }
                }

            }
        }

        return flatm;
    }

    public static final Map<String, String> getFieldsMap(Class cls) throws Exception {
        Map<String, String> fieldAccessorMap = new HashMap<>();

        for (Field f : cls.getDeclaredFields()) {
            String name = f.getName();
            String type = f.getType().getName();

            if (NATIVE_TYPES.contains(type) || type.startsWith("java")) {
                fieldAccessorMap.put(name, "get" + Character.toString(name.charAt(0)).toUpperCase() + name.substring(1) + "()");
            } else {
                Class c = Class.forName(type);
                String parentGetter = "get" + Character.toString(name.charAt(0)).toUpperCase() + name.substring(1) + "()";
                Map<String, String> innerFieldAccessorMap = getFieldsMap(c);
                for (Map.Entry<String, String> entry : innerFieldAccessorMap.entrySet()) {
                    innerFieldAccessorMap.put(entry.getKey(), parentGetter + "." + entry.getValue());
                }

                fieldAccessorMap.putAll(innerFieldAccessorMap);
            }
        }

        return fieldAccessorMap;
    }

    public static Object invokeMethod(Object obj, String methodName) throws Exception {
        List<String> methods = parseMethods(methodName);
        Object ret = null;
        for (String method : methods) {
            Method m = null;
            if (ret != null) {
                obj = ret;
            }
            m = obj.getClass().getDeclaredMethod(method);
            ret = m.invoke(obj);
        }
        return ret;
    }

    public static List<String> parseMethods(String methodName) {
        List<String> methods = new ArrayList<>();
        StringTokenizer stok = new StringTokenizer(methodName, "().");

        while (stok.hasMoreTokens()) {
            methods.add(stok.nextToken());
        }

        return methods;
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
        m.put("LSTTYPEOBJ", lst);

        return m;
    }
}
