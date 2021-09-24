package com.example.lib.d06reflect

import kotlin.reflect.full.createInstance
import kotlin.reflect.full.functions

/**
 * 通过KClass获取一个类的普通方法并调用
 * 通过判断参数个数调用
 */
class MyTestClass11 {
    fun printSomething(name: String) {
        println("printSomething $name")
    }

    fun printNothing() {
        println("printNothing")
    }
}

fun main() {
    // 获取MyTestClass5的KClass
    val myTestKClass11 = MyTestClass11::class
    val myTestKClass5Instance = myTestKClass11.createInstance()

    // 根据参数个数查找普通方法
    val functions = myTestKClass11.functions
    for (function in functions) {
        // 调用1个参数的普通方法
        // 注意对比构造方法的反射调用 因为普通方法比构造方法来说 多一个参数(实例对象)
        // 因此一般一个参数的普通方法 在反射时有两个参数
        if (function.parameters.size == 2) {
            function.call(myTestKClass5Instance, "xxxx")
        }
        // 调用0个参数的普通方法
        if (function.parameters.size == 1) {
            function.call(myTestKClass5Instance)
        }
    }
}

class HelloKotlin23 {
}