package com.example.lib.d07scope.function

import java.io.File
import java.util.*

/**
 * apply函数详解
 *
 * 1 apply函数返回调用者本身
 * 2 apply内部可以使用this代表调用者
 *
 * apply可以进行链式调用
 */

fun main() {
    val info = "Cai Huijian"
    // 不使用apply方法
    println("apply 函数里面打印的this是 $info")
    println("info 长度是${info.length}")
    println("info 最后一个字符是${info[info.length - 1]}")
    println("info 全部转为小写：${info.lowercase(Locale.getDefault())}")

    println("==============")

    // 使用apply方法
    val info2 = info.apply {
        println("apply 函数里面打印的this是 $this")
        println("info 长度是${/*this.*/length}") // this 可以省略
        println("info 最后一个字符是${this[/*this.*/length - 1]}")
        println("info 全部转为小写：${/*this.*/lowercase(Locale.getDefault())}")
    }
    println(info2)// apply方法返回调用者自身 这里是info
    println("==============")


    // 链式调用vs普通调用
    // 普通调用
    val file = File("D:\\a.txt")
    file.setExecutable(true)
    file.setReadable(true)
    println(file.readLines())

    // 由于apply返回调用者自身 因此可以进行链式调用
    println("==============")
    file.apply {
        setExecutable(true)
    }.apply {
        setReadable(true)
    }.apply {
        println(file.readLines())
    }

    // 也可以这么写
    file.apply {
        this.setExecutable(true)
        this.setReadable(true)
        println(this.readLines())
    }

    // 另外也可以这么写
    file.apply {
        file.setExecutable(true)
        file.setReadable(true)
        println(file.readLines())
    }
}

class HelloKotlin2 {
}