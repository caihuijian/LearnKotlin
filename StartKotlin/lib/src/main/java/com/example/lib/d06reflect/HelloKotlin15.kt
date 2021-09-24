package com.example.lib.d06reflect

import kotlin.reflect.full.memberFunctions

/**
 * 通过KClass获取一个类的普通方法
 */
class MyTestClass3 {
    fun printSth() {
        println("printSth")
    }

    fun getSth(): String {
        return "getSth"
    }
}

fun main() {
    val myTestClass3 = MyTestClass3::class
    println(myTestClass3.memberFunctions)
}

class HelloKotlin15 {
}
