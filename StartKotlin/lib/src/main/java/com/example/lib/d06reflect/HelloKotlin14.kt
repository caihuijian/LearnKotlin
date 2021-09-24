package com.example.lib.d06reflect

import kotlin.reflect.full.memberProperties

/**
 * 通过KClass获取一个类的属性信息
 */

class MyTestClass2(var a: String, val flag: Boolean, var age: Int)

fun main() {
    val myTestClass2 = MyTestClass2::class
    println(myTestClass2.memberProperties)
}

class HelloKotlin14 {
}