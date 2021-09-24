package com.example.lib.d06reflect

import kotlin.reflect.full.companionObject
import kotlin.reflect.full.functions

/**
 * 通过KClass获取一个类的伴生对象 并反射调用伴生的方法
 */

class MyTestClass8 {
    companion object {
        fun method() {
            println("method")
        }
    }
}

fun main() {
    val myTestClass8Class = MyTestClass8::class
    val companionObj = myTestClass8Class.companionObject
    println(companionObj)
    val methodToInvoke = companionObj?.functions?.find { it.name == "method" }
    methodToInvoke?.call(MyTestClass8)// 反射调用

    MyTestClass8.method()//正常调用
}

class HelloKotlin20 {
}