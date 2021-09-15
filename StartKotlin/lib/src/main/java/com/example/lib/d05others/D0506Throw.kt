package com.example.lib.d05others

import java.lang.IllegalArgumentException

/**
 * throw
 * throw在Kotlin中是一个表达式
 * throw表达式的类型是一种特殊的类型:Nothing
 * 在自己的代码中 可以使用Nothing来代表永远不会返回的函数
 */

fun main() {
    // 1 Elvis 表达式
    // Elvis是 ?: 它的含义是 如果该表达式的前半部分不为空 则该表达式的值为前半部分 否则为后半部分
    val str: String? = "aa" // null
    val str2 = str ?: throw IllegalArgumentException("不能传入空")
    println(str2)
    println("=========1 end ==========")

    // 2 throw表达式 返回Nothing类型
    val str3: String? = "bb" // null
    val str4 = str3 ?: myMethod("")
    println(str4)

    // 3 各种item赋值为null 类型推断
    // 赋值为null
    // 类型推断为Nothing?
    val myNull = null
    println(myNull is Nothing?)
    // list元素赋值为null
    // list类型推断为List<Nothing?>
    val s2 = listOf(null)
    println(s2 is List<Nothing?>)

}

fun myMethod(message: String): Nothing {
    throw IllegalArgumentException(message)
}

class D0506Throw {

}
