package com.example.lib.d07scope.function

/**
 * run 函数详解
 *
 * 1 let函数返回的是lambda表达式 即根据lambda表达式最后一行不同而不同
 * 2 let内部可以使用this代表调用者
 *
 * 3 使用lambda表达式链式调用
 */

fun main() {
    val str = "Huijian Cai"
    val res1: Float = str.run {
        println(this)// let内部可以使用this代表调用者
        true
        3.14f // 返回值是最后一行
    }
    println(res1)

    // 使用具名函数进行链式调用
    str.run(::isLong)//返回Boolean
        .run(::printText)// 根据Boolean返回不同text
        .run(::mapText)// 对text做处理
        .run(::println)// 输出text
    println("===========")

    // 使用匿名函数进行链式调用
    str.run { length > 5 }
        .run { if (this) "字符串合格" else "字符串太短" }
        .run { "[$this]" }
        .run { println(this) }
}

fun isLong(string: String) = string.length > 5

fun printText(isLong: Boolean) = if (isLong) "字符串合格" else "字符串太短"

fun mapText(getShow: String) = "[$getShow]"

class HelloKotlin4 {
}