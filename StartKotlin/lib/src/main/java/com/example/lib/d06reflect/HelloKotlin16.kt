package com.example.lib.d06reflect

/**
 * 通过KClass获取一个类的构造方法
 */
class MyTestClass4(value: Int) {
    constructor(value: Int, color: String) : this(value) {
        println("secondary constructor with string")
    }

    constructor(value: Int, full: Boolean) : this(value) {
        println("secondary constructor with boolean")
    }
}

fun main() {
    val myTestClass4 = MyTestClass4::class
    val constructors = myTestClass4.constructors
    println(constructors)
}

class HelloKotlin16 {
}