package com.example.lib.d05others;

// 配套在D0508KotlinCallJava2
public class MyArray {
    public void myArrayMethod(int[] args) {
        for (int a : args) {
            System.out.println(a);
        }
    }

    public void myArrayMethod2(String... strings) {
        for (String a : strings) {
            System.out.println(a);
        }
    }
}
