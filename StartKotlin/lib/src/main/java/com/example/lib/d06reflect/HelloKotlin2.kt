package com.example.lib.d06reflect

// 通过实例(而不是类名) 获取自定义类的Kotlin以及Java 对象
fun main() {
    val son: Parent = Son()
    val daughter: Parent = Daughter()

    // 注意 因为这里是自定义的class 他们的Java class和Kotlin class输出一样
    println(son::class)
    println(son::class.java)
    println(daughter::class)
    println(daughter::class.java)
}

open class Parent
class Son : Parent()
class Daughter : Parent()

class HelloKotlin2 {
}