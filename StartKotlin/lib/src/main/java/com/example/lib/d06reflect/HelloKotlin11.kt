package com.example.lib.d06reflect

import kotlin.reflect.KClass

/**
 * Java 反射vs Kotlin反射
 *
 * Java反射和Kotlin反射各种class的对应关系
 * Java   Kotlin
 * Class  KClass
 * Method KFunction
 * Field  KProperty
 * 当然 并不是完全等价 例如与KProperty类似的还有KMutableProperty KProperty0 KMutableProperty0等等
 */

fun main() {
    val kotlinLang = "kotlin"
    val kClass: KClass<out String> = kotlinLang::class // 无论一个类有多少实例 他们的KClass是唯一的 都相等
    println(kClass)

    val kClassDataType: KClass<String> = String::class
    println(kClassDataType)

    val kClass1: KClass<out String> = "kotlin"::class
    val kClass2: KClass<out String> = "java"::class
    val kClass3: KClass<out String> = "ruby"::class
    println(kClass1)
    println(kClass2)
    println(kClass3)
    println(kClass1 == kClass2 && kClass2 == kClass3 && kClass1 == String::class)

    // Java Class 对象
    val javaClass: Class<*> = Class.forName("java.util.Date")
    // Kotlin KClass 对象
    val kClass4: KClass<out Any> = Class.forName("java.util.Date").kotlin
    println(javaClass)
    println(kClass4)
    // 输出一样但是类型不一样会返回false
    println(kClass4 == javaClass)
}

class HelloKotlin11 {
}