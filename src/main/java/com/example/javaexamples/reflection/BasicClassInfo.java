package com.example.javaexamples.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.security.PermissionCollection;
import java.security.Principal;
import java.security.ProtectionDomain;
import java.util.Arrays;

public class BasicClassInfo {
    public static void main(String[] args) {
        Class stringClass = String.class;

        // Basic class info
        System.out.println("Name: " + stringClass.getName());
        System.out.println("Canonical name: " + stringClass.getCanonicalName());
        System.out.println("Simple name: " + stringClass.getSimpleName());
        System.out.println("Type name: " + stringClass.getTypeName());

        System.out.println("Package: " + stringClass.getPackage());
        ProtectionDomain protectionDomain = stringClass.getProtectionDomain();
        System.out.println("Protection Domain: ");
        System.out.println("\tCode source: " + protectionDomain.getCodeSource());
        System.out.println("\tPrincipals: ");
        Principal[] principals = protectionDomain.getPrincipals();
        for (Principal principal : principals) {
            System.out.println("\t\t" + principal.toString());
        }

        System.out.println("\tPermission Collection: " + protectionDomain.getPermissions().toString());

        System.out.println("Super class: " + stringClass.getSuperclass());
        System.out.println("Generic Super class: " + stringClass.getGenericSuperclass());
        System.out.println("Interfaces: " + Arrays.toString(stringClass.getInterfaces()));
        System.out.println("Generic Interfaces: " + Arrays.toString(stringClass.getGenericInterfaces()));

        // Get class annotations
        System.out.println("Annotations: " + Arrays.toString(stringClass.getAnnotations()));
        System.out.println("Annotated Interfaces: " );
        for (AnnotatedType at : stringClass.getAnnotatedInterfaces()) {
            System.out.println("Annotations for type: " + at.getType() );
            Annotation[] annotations = at.getAnnotations();
            for (Annotation annotation : annotations) {
                System.out.println("\t" + annotation.annotationType().getName());
            }
        }


    }
}
