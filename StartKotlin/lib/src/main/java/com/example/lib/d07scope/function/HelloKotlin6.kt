package com.example.lib.d07scope.function

import java.io.File
import java.util.*

/**
 * also 函数详解
 *
 * 1 also 函数返回的是调用者本身
 * 2 also 内部可以使用it代表调用者
 *
 */
fun main() {
    val str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

    val r1: String = str.also {// r1 始终是str本身 不会管最后一行
        true
        3434.4f
        454
        'C'
        1
    }
    println(r1)
    println("=============")

    str.also {
        println(it)// 在内部 可以使用it代表调用者本身
    }

    // 由于also始终返回调用者本身 可以进行链式调用
    str.also {
        println("原始数据1：$str")
        println("原始数据2：$it")
    }.also {
        println("转换为小写${it.lowercase(Locale.getDefault())}")
    }.also {
        println("end")
    }
    println(str)// 不管如何操作 原始数据不变
    println("==============")

    // 链式调用的优势

    // 普通调用
    val file = File("D:\\a.txt")
    file.setExecutable(true)
    file.setReadable(true)
    println(file.readLines())
    println()
    // also链式调用
    val fileNew: File = file.also {
        file.setExecutable(true)// 可以使用file或者it代表外部变量
        it.setReadable(true)
    }.also {
        println(file.readLines())// 也可以拆成多次also调用
    }
    println(fileNew === file)
}

class HelloKotlin6 {
}