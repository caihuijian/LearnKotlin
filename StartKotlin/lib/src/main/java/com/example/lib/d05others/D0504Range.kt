package com.example.lib.d05others
// range

fun main() {
    // 1 输出4
    val i = 4
    if (i in 1..5) {
        println(i)
    }
    println("---------------")
    // 输出1-4
    for (a in 1..4) {
        println(a)
    }
    println("---------------")
    // 什么输出都没有
    for (a in 4..1) {
        println(a)
    }
    println("---------------")
    // 降序输出
    for (a in 4 downTo 1) {
        println(a)
    }
    println("---------------")

    for (a in 1..6 step 2) {
        println(a)
    }
    println("---------------")
    // 输出1-4 [1,4)
    for (a in 1 until 4) {
        println(a)
    }
    println("---------------")
}

class D0504Range {
}