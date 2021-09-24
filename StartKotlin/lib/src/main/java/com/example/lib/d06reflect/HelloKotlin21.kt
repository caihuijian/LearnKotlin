package com.example.lib.d06reflect

import kotlin.reflect.full.createInstance
import kotlin.reflect.full.functions

/**
 * 通过KClass反射创建实例
 * 反射调用无参构造方法
 */


class MyTestClass9(val value: Int = 0) {
    fun printSomething(name: String) {
        println("printSomething $name")
    }

    fun printNothing() {
        println("printNothing $value")
    }
}

fun main() {
    val myTestClass9KClass = MyTestClass9::class
    // 反射创建实例 调用的是无参构造方法
    val myTestClass9Instance = myTestClass9KClass.createInstance()

    // 反射查找方法并调用指定对象的该方法
    myTestClass9KClass.functions.find { it.name == "printSomething" }
        ?.call(myTestClass9Instance, "AAA")
    myTestClass9KClass.functions.find { it.name == "printNothing" }?.call(myTestClass9Instance)
}

class HelloKotlin21 {
}