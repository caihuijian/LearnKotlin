package com.example.lib.coroutine3

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * 程序运行时间统计measureTimeMillis
 *
 * Kotlin 提供了一个便利的内置函数来统计运行时间(measureTimeMillis)
 */

fun main() = runBlocking {
    // Executes the given block and returns elapsed time in milliseconds.
    val elapsedTime = measureTimeMillis {
        println("start calculate...")
        val value1 = intValue1() // 串行调用挂起方法 时间为挂起函数执行时间累加值
        val value2 = intValue2()
        println("$value1 + $value2 = ${value1 + value2}")
    }

    println("total time: $elapsedTime")
}

private suspend fun intValue1(): Int {
    delay(2000)
    return 15
}

private suspend fun intValue2(): Int {
    delay(3000)
    return 20
}

class HelloKotlin1 {
}