package com.example.lib.d05others

import java.lang.NumberFormatException
import javax.xml.bind.DatatypeConverter.parseInt

// 异常
// Kotlin 中的异常是一个表达式
// Kotlin 中所有的异常都是运行时异常 没有checked exception
fun main() {
    val s = "a"
    // 如下所示
    // res的值可能是try中的最后一句表达式的值 或者 catch中的最后一句表达式的值
    val res: Int? = try {
        parseInt(s)
    } catch (ex: NumberFormatException) {
        null
    } finally {
        println("finally invoked")
    }
    println(res)
}

class D0505Exception {
}
