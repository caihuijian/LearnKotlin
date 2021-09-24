package com.example.lib.d06reflect

import kotlin.reflect.full.functions

/**
 * 通过KClass获取一个类的普通方法并调用
 * 通过判断方法名称调用
 */
class MyTestClass5 {
    fun printSomething(name: String) {
        println("printSomething $name")
    }

    fun printNothing() {
        println("printNothing")
    }
}

fun main() {
    // 获取MyTestClass5的KClass
    val myTestClass5 = MyTestClass5::class
    val myTestClass5Instance = MyTestClass5()

    // 通过MyTestClass5的KClass 根据方法名查找方法
    val functionToInvoke1 = myTestClass5.functions.find { it.name == "printSomething" }
    // 调用指定instance的相应方法
    functionToInvoke1?.call(myTestClass5Instance, "AAAA")
    val functionToInvoke2 = myTestClass5.functions.find { it.name == "printNothing" }
    functionToInvoke2?.call(myTestClass5Instance)
}

class HelloKotlin17 {
}