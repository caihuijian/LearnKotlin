package com.example.lib.d06reflect

/**
 * 反射与扩展属性
 */
val String.firstChar: Char
    get() = this[0]

fun main() {
    println("abc".firstChar)

    val x = String::firstChar
    println(x.get("abc"))
}

class HelloKotlin7 {
}