// 1 元注解@file的使用
// 注解如果和D0509JavaCallKotlin2相同 且没有加 @file: JvmMultifileClass 则会报错
// e: D:\testDarren\LearnKotlin\StartKotlin\lib\src\main\java\com\example\lib\d05others\D0509JavaCallKotlin2.kt: (1, 1): Duplicate JVM class name 'com/example/lib/d05others/HelloWorld1' generated from: HelloWorld1, HelloWorld1
// 如果加上了 @file: JvmMultifileClass 则代表与其他叫HelloWorld1的文件进行合并
@file: JvmMultifileClass
@file: JvmName("HelloWorld1")

package com.example.lib.d05others

fun myPrint2() {
    println("hello world2")
}

class MyPerson {
    var name: String = "zhangsan"

    // 2 @JvmField的使用
    // @JvmField 会将普通的实例变量 变化成 类变量
    // 即age1会变成一个静态变量 且没有set get方法
    @JvmField
    var age1: Int = 10
    var age2: Int = 20
}

class MyPeople {
    companion object {
        var name = "zhangsan"
        var age = 20

        // 3 @JvmField 修饰伴生对象的filed
        // age3变成伴生对象的类变量 没有了set get方法
        @JvmField
        var age3 = 30

        // 4 @JvmStatic修饰伴生对象的方法
        // 在Kotlin中 可以将具名对象或者伴生对象中定义的函数注解为@JvmStatic 这样编译器会机会再相应对象的类中生成静态（类）方法 也会在在相应的类中生成实例方法
        // 即 在companion对象中生成test1方法 在MyPeople也生成test1方法 且MyPeople.test1方法内部调用的是companion.test1
        @JvmStatic
        fun test1() {
            println("test1")
        }

        fun test2() {
            println("test2")
        }
    }
}


class D0509JavaCallKotlin3 {
}