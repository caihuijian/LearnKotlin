package com.example.lib.coroutine5

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

/**
 * Flow 的中间运算符filter map
 * Sequence也可以调用filter map等方法
 * Flow与Sequence之间的区别：对于Flow来说 中间运算符代码内可以调用挂起函数(对比Kotlin2)
 */
private suspend fun myExecution(input: Int): String {
    println("myExecution: $input")
    delay(500)
    return "output: $input"
}

fun main() = runBlocking<Unit> {
    // 将1到10转换为flow 过滤其中大于5的数字 并将这些数字形成一个映射 最后将其打印出来
    (1..10).asFlow().filter { it > 5 }.map { it -> myExecution(it) }// 这里中间函数map调用了挂起函数
        .collect { println(it) }
}

class HelloKotlin8 {
}