package com.example.lib.d06reflect

import kotlin.reflect.KMutableProperty1
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.memberProperties

/**
 * 通过KClass获取一个类的属性并get set它的值
 * 通过判断属性名称 并使用KMutableProperty1或KProperty1 调用
 * 可以对比HelloKotlin19的例子
 */

class MyTestClass12 {
    var name: String = "Flutter"
    val price: Double = 34.5
}

fun main() {
    val myTestClass12KClass = MyTestClass12::class
    val myTestClass12Instance = myTestClass12KClass.createInstance()
    // memberProperties是KProperty1类型 只有get方法
    val properties = myTestClass12KClass.memberProperties

    properties.forEach {
        when (it.name) {
            "name" -> {
                // KMutableProperty1 包含了set和get方法
                // 其中的泛型的第一个参数是接收者类型 也是当前属性所依附的对象类型
                // 其中的泛型的第二个参数是当前属性类型
                val kMutableProperty = it as KMutableProperty1<MyTestClass12, String>
                kMutableProperty.set(myTestClass12Instance, "Kotlin")
                println(it.get(myTestClass12Instance))

            }
            "price" -> {
                println(it.get(myTestClass12Instance))
            }
        }
    }

}

class HelloKotlin24 {
}