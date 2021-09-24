package com.example.lib.d06reflect

import kotlin.reflect.full.createInstance

/**
 *  顶层变量的反射 vs 普通变量的反射调用
 */
class MyTestClass13 {
    // 普通变量
    var name: String = "Flutter"
}

// 顶层变量
var test = "test"
fun main() {
    // 顶层变量的反射调用
    val topProp = ::test
    topProp.set("changed value")
    println(topProp.get())
    println(test)

    // 普通变量的反射调用
    println("==========通过实例反射调用属性========")
    // 通过实例反射调用属性
    val myTestClass13Instance = MyTestClass13::class.createInstance()
    val name = myTestClass13Instance::name
    name.set("ruby")
    println(name.get())

    println("==========通过KClass反射调用属性========")
    // 通过KClass反射调用属性
    val name2 = MyTestClass13::name
    name2.set(myTestClass13Instance, "Kotlin")
    println(name2.get(myTestClass13Instance))
}

class HelloKotlin25 {
}