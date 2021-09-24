package com.example.lib.d06reflect

import kotlin.reflect.KClass
import kotlin.reflect.KProperty0
import kotlin.reflect.KProperty1
import kotlin.reflect.jvm.javaGetter
import kotlin.reflect.jvm.javaSetter

// 获取KClass Class等对象的对比示例

class T(var x: Int)

fun main() {
    // Kotlin语境下 获取Java get set的表示
    println(T::x.javaGetter)
    println(T::x.javaSetter)
    // javaClass与javaClass.kotlin的toString可能相同 如下面2个
    // 获取Java类
    println(T(10).javaClass)
    // 获取Java类对应的Kotlin类
    println(T(10).javaClass.kotlin)
    println("------1-----")

    // javaClass与javaClass.kotlin的toString也可能不相同 如下面2个就是不同的
//     val values5:Class<String.Companion> = String.javaClass
//     val values55:KClass<String.Companion> = String.javaClass.kotlin
//    println(values5)
//    println(values55)

    // .javaClass: Returns the runtime Java class of this object.
    val values1: Class<String.Companion> = String.javaClass
    val values11 = "AAA".javaClass
    println(values1)
    println(values11)
    println("------2-----")
    // .kotlin: Returns a KClass instance corresponding to the given Java Class instance.
    val values2: KClass<String.Companion> = String.javaClass.kotlin
    val values22 = "AAA".javaClass.kotlin
    println(values2)
    println(values22)
    println("------3-----")

    // ::class: 返回Kotlin class(KClass)
    // ::代表引用 如果前面跟了类 则是类引用
    // 如果后面跟了属性 则是属性引用
    // 如果后面跟了方法 则是方法引用
    // 实例的引用和类的引用几乎一致
    val values3: KClass<String> = String::class
    val values33: KClass<out String> = "AAA"::class
    println(values3)
    println(values33)
    println("------4-----")
    // .java: Returns a Java Class instance corresponding to the given KClass instance.
    val values4: Class<String> = String::class.java
    val values44 = "AAA"::class.java
    println(values4)
    println(values44)
    println("------5-----")

    // 对比示例 示例来自 https://blog.csdn.net/a568478312/article/details/80718028
    val person = Person()
    val a: KClass<Person> = Person::class
    val b: KClass<out Person> = person::class
    val c: Class<Person> = Person::class.java
    val d: Class<out Person> = person::class.java

    val e: KProperty1<Person, Class<Person>> = Person::javaClass
    val f: KProperty0<Class<Person>> = person::javaClass

    val g: Class<Person.Companion> = Person.javaClass
    val h: Class<Person> = person.javaClass
    val i: KClass<Person.Companion> = Person.javaClass.kotlin
    val j: KClass<Person> = person.javaClass.kotlin
    println(a)
    println(b)
    println(c)
    println(d)
    println(e)
    println(f)
    println(g)
    println(h)
    println(i)
    println(j)
    println("===========================")

    val string = "String"
    val a1: KClass<String> = String::class
    val b1: KClass<out String> = string::class
    val c1: Class<String> = String::class.java
    val d1: Class<out String> = string::class.java

    val e1: KProperty1<String, Class<String>> = String::javaClass
    val f1: KProperty0<Class<String>> = string::javaClass

    val g1: Class<String.Companion> = String.javaClass
    val h1: Class<String> = string.javaClass
    val i1: KClass<String.Companion> = String.javaClass.kotlin
    val j1: KClass<String> = string.javaClass.kotlin
    println(a1)
    println(b1)
    println(c1)
    println(d1)
    println(e1)
    println(f1)
    println(g1)
    println(h1)
    println(i1)
    println(j1)
    val output = """
        class com.example.lib.d06reflect.Person
        class com.example.lib.d06reflect.Person
        class com.example.lib.d06reflect.Person
        class com.example.lib.d06reflect.Person
        val T.javaClass: java.lang.Class<T>
        val T.javaClass: java.lang.Class<T>
        class com.example.lib.d06reflect.Person        ${'$'}Companion
        class com.example.lib.d06reflect.Person
        class com.example.lib.d06reflect.Person        ${'$'}Companion
        class com.example.lib.d06reflect.Person
        ===========================
        class kotlin.String
        class kotlin.String
        class java.lang.String
        class java.lang.String
        val T.javaClass: java.lang.Class<T>
        val T.javaClass: java.lang.Class<T>
        class kotlin.jvm.internal.StringCompanionObject
        class java.lang.String
        class kotlin.String        ${'$'}Companion
        class kotlin.String

        Process finished with exit code 0

    """.trimIndent()
}

class Person {
    var name: String = ""
    var age: Int = 0

    companion object {

    }
}

class HelloKotlin8 {
}