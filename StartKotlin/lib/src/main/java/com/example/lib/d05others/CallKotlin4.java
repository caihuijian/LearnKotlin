package com.example.lib.d05others;

import java.io.FileNotFoundException;

public class CallKotlin4 {
    public static void main(String[] args) {
        D0509JavaCallKotlin5 kotlin5 = new D0509JavaCallKotlin5();
        // kotlin5.method();// 运行时报错

        // 因为加了@Throws注解 Java调用时知道该方法可能抛出异常
        try {
            kotlin5.method2();
        } catch (FileNotFoundException e) {
            System.out.println("catch FileNotFoundException " + e.getMessage());
        }

        kotlin5.method3("hello");
        // kotlin5.method3(null); //运行直接报错
    }
}
