package com.example.lib.d06reflect

import kotlin.reflect.full.memberProperties

/**
 * 通过KClass获取一个类的属性并调用其get方法
 * 通过判断属性名称调用
 */
class MyTestClass6() {
    var name: String = "hello"
}

fun main() {
    // 获取MyTestClass6的KClass
    val myTestClass6Class = MyTestClass6::class
    val myTestClass6Instance = MyTestClass6()

    val propertyToInvoke = myTestClass6Class.memberProperties.find { it.name == "name" }
    println(propertyToInvoke?.call(myTestClass6Instance))// 反射调用属性1
    println(propertyToInvoke?.get(myTestClass6Instance))// 反射调用属性2
    println(propertyToInvoke?.getter?.call(myTestClass6Instance))// 反射调用属性3
}

class HelloKotlin18 {
}