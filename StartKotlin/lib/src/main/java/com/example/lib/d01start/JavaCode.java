package com.example.lib.d01start;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class JavaCode {

    static class Parent{
         final int  x = 10;
    }
    static class Child extends Parent{
        public int  x = 100;
    }

    public static void main(String[] args) {

        Child child = new Child();
        System.out.println(child.x);
    }

}
