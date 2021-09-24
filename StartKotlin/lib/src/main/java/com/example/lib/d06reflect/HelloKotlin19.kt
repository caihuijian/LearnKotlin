package com.example.lib.d06reflect

import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.memberProperties

/**
 * 通过KClass获取一个类的属性并get set它的值
 * 通过判断属性名称调用
 */
class MyTestClass7 {
    var name: String = "hello"
    var authorName: String = "Tom"
}

fun main() {
    // 获取MyTestClass6的KClass
    val myTestClass7Class = MyTestClass7::class
    val myTestClass7Instance = MyTestClass7()

    val propertyToInvoke = myTestClass7Class.memberProperties.find { it.name == "name" }
    println(propertyToInvoke?.call(myTestClass7Instance))// 反射调用属性1
    println(propertyToInvoke?.get(myTestClass7Instance))// 反射调用属性2
    println(propertyToInvoke?.getter?.call(myTestClass7Instance))// 反射调用属性3
    println(myTestClass7Instance.name)

    if (propertyToInvoke is KMutableProperty<*>) {// 反射获取属性的set方法并调用
        propertyToInvoke.setter.call(myTestClass7Instance, "hi")
    }
    println(propertyToInvoke?.call(myTestClass7Instance))// 反射调用属性1
    println(propertyToInvoke?.get(myTestClass7Instance))// 反射调用属性2
    println(propertyToInvoke?.getter?.call(myTestClass7Instance))// 反射调用属性3
    println(myTestClass7Instance.name)
}

class HelloKotlin19 {
}