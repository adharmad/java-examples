package com.example.javaexamples.reflection;

import com.example.javaexamples.annotation.B;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class CopyObjectReflectively {
    public static void main(String[] args) {
        AObj aObj= new AObj("hello", "world", 10L, 3.1D, new String[]{"test1", "test2", "test3"});
        System.out.println(aObj.toString());

        BObj bObj = new BObj();
        System.out.println(bObj.toString());

        copyObject(aObj, bObj);
        System.out.println(bObj.toString());

    }

    public static void copyObject(Object sourceObject, Object targetObject) {
        if (sourceObject == null || targetObject == null) {
            return;
        }

        Class sourceClass = sourceObject.getClass();
        Class targetClass = targetObject.getClass();

        try {
            Field[] sourceFields = sourceClass.getDeclaredFields();
            Field[] targetFields = targetClass.getDeclaredFields();

            Set<String> targetFieldsSet =
                    Arrays.stream(targetFields).map(Field::getName).collect(Collectors.toSet());

            for (Field field : sourceFields) {
                String fieldName = field.getName();

                if (targetFieldsSet.contains(fieldName)) {
                    Object fieldValue = FieldUtils.readDeclaredField(sourceObject, fieldName, true);
                    FieldUtils.writeField(targetObject, fieldName, fieldValue, true);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return;
    }
}
