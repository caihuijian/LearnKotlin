package com.example.lib.d06reflect

import kotlin.reflect.jvm.javaField
import kotlin.reflect.jvm.javaGetter
import kotlin.reflect.jvm.javaSetter

/**
 * 查找一个用作Kotlin属性的getter的幕后字段或Java方法
 */
class MyTestClass14 {
    var name: String = "java"
    val price: Double = 34.5
}

var myTest = "myTest"

fun main() {
    val topProp = ::myTest
    println(topProp.javaField)
    println(topProp.javaSetter)
    println(topProp.javaGetter)

    val n = MyTestClass14::name
    println(n.javaField)
    println(n.javaSetter)
    println(n.javaGetter)

    val p = MyTestClass14::price
    println(p.javaField)
    println(p.javaGetter)
}

class HelloKotlin26 {
}