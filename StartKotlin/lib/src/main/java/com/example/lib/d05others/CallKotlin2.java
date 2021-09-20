package com.example.lib.d05others;

public class CallKotlin2 {
    public static void main(String[] args) {
        // JvmName修改了生成的字节码文件名
        // 同时JvmMultifileClass可以合并不同字节码文件
        // 虽然Kotlin有这个功能 但是非必要不推荐使用
        HelloWorld1.myPrint();
        HelloWorld1.myPrint2();

        // JvmField使用使用在普通对象上
        // Java 调用普通对象
        MyPerson person = new MyPerson();
        System.out.println(person.getName());
        // age1变成了类变量
        System.out.println(person.age1);
        System.out.println(person.getAge2());

        // Java 调用伴生对象
        System.out.println(MyPeople.Companion.getAge());
        System.out.println(MyPeople.Companion.getName());
        // @JvmField加在伴生对象的属性上
        System.out.println(MyPeople.age3);

        MyPeople.Companion.test1();
        // @JvmStatic加在伴生对象的方法上
        MyPeople.test1();
        MyPeople.Companion.test2();
    }
}
