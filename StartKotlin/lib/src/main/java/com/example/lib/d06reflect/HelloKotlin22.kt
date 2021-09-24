package com.example.lib.d06reflect

import kotlin.reflect.full.createInstance
import kotlin.reflect.full.memberProperties

/**
 * 通过KClass反射调用有参构造方法
 */

class MyTestClass10(var name: String) {
    var price = 0.0

    constructor() : this("默认商品") {
        price = 0.0
    }

    constructor(name: String, price: Double) : this(name) {
        this.price = price
    }
}

fun main() {
    val myTestClass10KClass = MyTestClass10::class
    // 反射调用无参构造方法
    val myTestClass10Instance = myTestClass10KClass.createInstance()
    println(myTestClass10Instance.name)
    println(myTestClass10Instance.price)

    // 反射调用属性
    println(myTestClass10KClass.memberProperties.find { it.name == "name" }
        ?.call(myTestClass10Instance))
    println(myTestClass10KClass.memberProperties.find { it.name == "price" }
        ?.call(myTestClass10Instance))

    // 反射调用有参构造方法
    val cons = myTestClass10KClass.constructors
    cons.forEach {
        if (it.parameters.size == 2) {
            val instance2 = it.call("python", 34)
            println(myTestClass10KClass.memberProperties.find { it.name == "name" }
                ?.call(instance2))
            println(myTestClass10KClass.memberProperties.find { it.name == "price" }
                ?.call(instance2))
        }
    }
}

class HelloKotlin22 {
}