package com.example.javaexamples.reflection;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CreateObjectFromMap {
    public static void main(String[] args) {
        Map<String, Object> hm = new HashMap<>();
        hm.put("DB_ELEM1", "hello");
        hm.put("DB_NUM1", new BigDecimal(99999999));
        hm.put("DB_NUM2", 100);
        hm.put("DB_BOOL1", true);

        SimpleObj so = createObjectFromAnnotatedMap(hm, SimpleObj.class, ObjMeta.class, "columnName");
        System.out.print(so.toString());

    }

    public static <T> T createObjectFromMap(Map mapValue, Class<T> cls) {
        T retObj = null;

        try {
            retObj = cls.getDeclaredConstructor().newInstance();
            Field[] fields = cls.getDeclaredFields();

            for (Field field : fields) {
                String fieldName = field.getName();
                if (!mapValue.containsKey(fieldName)) {
                    continue;
                }

                Object fieldValue = mapValue.get(fieldName);
                FieldUtils.writeField(retObj, fieldName, fieldValue, true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return retObj;
    }

    public static <T> T createObjectFromAnnotatedMap(Map mapValue, Class<T> cls, Class annotationClass, String annotationMethodName) {
        T retObj = null;

        try {
            retObj = cls.getDeclaredConstructor().newInstance();
            Field[] fields = cls.getDeclaredFields();

            for (Field field : fields) {
                String fieldName = field.getName();
                Annotation annotation = field.getAnnotation(annotationClass);
                Object annotationValue = MethodUtils.invokeMethod(annotation, annotationMethodName);

                if (!mapValue.containsKey(annotationValue)) {
                    continue;
                }

                Object fieldValue = mapValue.get(annotationValue);
                Object convertedFieldValue = toTargetType(fieldValue, field.getType().getName());
                FieldUtils.writeField(retObj, fieldName, convertedFieldValue, true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return retObj;
    }

    public static Object toTargetType(Object obj, String targetType) {
        Object retObj = obj;
        if (obj == null) {
            return retObj;
        }

        if (obj.getClass().getName().equals(targetType)) {
            return retObj;
        }

        String conversionMethod = CONVERSION_TABLE.get(Pair.of(obj.getClass().getName(), targetType));
        try {
            retObj = MethodUtils.invokeExactStaticMethod(CreateObjectFromMap.class, conversionMethod, obj);
        } catch (Exception ex) {
            ex.printStackTrace();;
        }

        return retObj;
    }

    public static Long bigDecimalToLong(BigDecimal bigDecimal) {
        return bigDecimal.longValue();
    }


    public static final Map<Pair<String, String>, String> CONVERSION_TABLE = new HashMap();
    static {
        CONVERSION_TABLE.put(Pair.of("java.math.BigDecimal", "long"), "bigDecimalToLong");
    }

}
