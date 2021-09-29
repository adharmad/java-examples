package com.example.javaexamples.reflection;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author Amol Dharmadhikari (adharmad@ionate.io)
 */
public class ObjectFactory {
    public static Object createBeanOfType(Class c) {
        Object o = null;
        try {
            o = c.newInstance();

            Field[] fields = c.getDeclaredFields();
            for (Field field : fields) {
                String fieldType = field.getType().getName();
                String fieldName = field.getName();
                if (!fieldType.startsWith("java") && !fieldType.equals("int")
                        && !fieldType.equals("long") && !fieldType.equals("double")) {
                    Object childObj = createBeanOfType(field.getType());
                    FieldUtils.writeField(o, fieldName, childObj, true);
                }

                if (fieldType.equals("java.util.List")) {
                    String getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
                    Object lstObj = MethodUtils.invokeMethod(o, getterName, null);

                    Type listType = ((ParameterizedType)field.getGenericType()).getActualTypeArguments()[0];
                    Class listTypeClass = Class.forName(listType.getTypeName());
                    Object listElem = createBeanOfType(listTypeClass);
                    MethodUtils.invokeMethod(lstObj, "add", new Object[]{ listElem });
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return o;
    }

    public static void main(String[] args) {
        Object o = createBeanOfType(Company.class);
        System.out.println(o);
    }
}
