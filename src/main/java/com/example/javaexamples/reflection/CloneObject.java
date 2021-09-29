package com.example.javaexamples.reflection;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import java.util.List;

public class CloneObject {
    public static void main(String[] args) throws Exception {
        Company company = new Company();
        System.out.println(company);
        System.out.println("=======");
        Company1 company1 = cloneObject(company, Company1.class);

        System.out.println(company1);
        System.out.println("=======");
    }

    public static <T> T cloneObject(Object sourceObj, Class<T> targetType) throws Exception {
        Object retObj = null;

        if (sourceObj == null) {
            return (T) retObj;
        }

        Class sourceType = sourceObj.getClass();
        retObj = (T) createBeanOfType(targetType);

        Field[] fields = targetType.getDeclaredFields();

        for (Field field : fields) {
            String fieldName = field.getName();
            String fieldType = field.getType().getName();

            if (sourceType.getDeclaredField(fieldName) != null
                    && (java.lang.reflect.Modifier.isStatic(field.getModifiers())
                    || java.lang.reflect.Modifier.isFinal(field.getModifiers())
                    || java.lang.reflect.Modifier.isTransient(field.getModifiers()))) {
                continue;
            }
            if (fieldType.startsWith("java")
                    || fieldType.startsWith("[Ljava")
                    || fieldType.equals("int")
                    || fieldType.equals("long")
                    || fieldType.equals("float")
                    || fieldType.equals("double")
                    || fieldType.equals("[B")
                    || fieldType.equals("[C")) {
                try {
                    FieldUtils.writeDeclaredField(
                            retObj, fieldName, FieldUtils.readDeclaredField(sourceObj, fieldName, true), true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (fieldType.equals("java.util.List")) {
                String getterName = getGetterName(fieldName);

                try {
                    Object targetLstObj = MethodUtils.invokeMethod(retObj, getterName, null);
                    List targetLst = (List) targetLstObj;

                    Object sourceLstObj = MethodUtils.invokeMethod(sourceObj, getterName, null);
                    List sourceLst = (List) sourceLstObj;

                    Type targetListType =
                            ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
                    Class targetListTypeClass = Class.forName(targetListType.getTypeName());

                    for (Object sourceLstElem : sourceLst) {
                        Object targetListElem = cloneObject(sourceLstElem, targetListTypeClass);
                        MethodUtils.invokeMethod(targetLst, "add", new Object[]{targetListElem});
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                try {
                    String getterName = getGetterName(fieldName);
                    Object sourceEmbObj = MethodUtils.invokeMethod(sourceObj, getterName, null);

                    Object targetEmbObj = cloneObject(sourceEmbObj, field.getType());
                    FieldUtils.writeDeclaredField(retObj, fieldName, targetEmbObj, true);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        return (T) retObj;
    }

    public static Object createBeanOfType(Class c) throws Exception {
        Object o = c.newInstance();
        return o;
    }

    public static String getGetterName(String attrName) {
        return "get" + attrName.substring(0, 1).toUpperCase() + attrName.substring(1);
    }

}
