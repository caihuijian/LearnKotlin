package com.example.lib.d06reflect

/**
 * 属性引用与扩展属性
 */
// 给String增加一个扩展属性firstChar
val String.firstChar: Char
    get() = this[0]

fun main() {
    // 正常调用扩展属性
    println("abc".firstChar)

    // 通过属性引用调用扩展属性
    val x = String::firstChar
    println(x.get("abc"))
}

class HelloKotlin7 {
}