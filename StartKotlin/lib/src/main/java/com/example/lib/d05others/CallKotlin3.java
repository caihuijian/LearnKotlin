package com.example.lib.d05others;

import java.util.ArrayList;
import java.util.List;

public class CallKotlin3 {
    public static void main(String[] args) {
        // 1 JvmName注解 解决方法命名冲突
        List<String> list = D0509JavaCallKotlin4Kt.myFilter(new ArrayList<String>());
        System.out.println(list);

        List<Integer> list2 = D0509JavaCallKotlin4Kt.myFilter2(new ArrayList<Integer>());
        System.out.println(list2);

        // 2 JvmName注解 解决方法命名冲突2
        MyClass myClass = new MyClass();
        System.out.println(myClass.getA());
        System.out.println(myClass.getAValue());

        // 3 Java调用Kotlin带有默认参数的构造方法
        MyClass5 myClass5 = new MyClass5(1, "string");
        // Java看起来也可以使用默认参数了
        MyClass5 myClass51 = new MyClass5(1);

        // 4 Java调用Kotlin带有默认参数的普通方法
        myClass5.myMethod(1, "string");
        myClass5.myMethod(1, "string", 2);
    }
}
