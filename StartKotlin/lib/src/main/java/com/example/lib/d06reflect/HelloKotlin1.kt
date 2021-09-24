package com.example.lib.d06reflect


// 获取Java class和Kotlin KClass的方法

fun main() {
    // 获取Kotlin KClass
    val c = String::class
    println(c)
    // 获取Java class
    val c2 = String::class.java
    println(c2)

    println(Int::class)
    println(Int::class.java)

    println(Collection::class)
    println(Collection::class.java)
}

class HelloKotlin1 {
}