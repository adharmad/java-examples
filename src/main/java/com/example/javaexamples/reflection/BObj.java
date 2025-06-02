package com.example.javaexamples.reflection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BObj {
    private String foo;
    private String bar;
    private long num1;
    private double dbl1;
    private String[] arr = new String[3];
}
