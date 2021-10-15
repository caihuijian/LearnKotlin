package com.example.lib.coroutine3

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * 使用async延时启动协程
 *
 * 我们可以通过将async方法的start方法参数设置为 CoroutineStart.LAZY来实现协程的延迟执行
 * 在这种情况下，协程会在如下情况执行：
 * 1.调用deferred的await方法
 * 2.调用job的start方法
 */
fun main() = runBlocking {
    val elapsedTime = measureTimeMillis {

        val deferred1 = async(start = CoroutineStart.LAZY) { intValue1() } // 并行调用挂起方法
        val deferred2 = async(start = CoroutineStart.LAZY) { intValue2() } // 并行调用挂起方法
        println("hello...")
        Thread.sleep(2000)
        // 如果注释调下面两个start调用 协程又会变成串行执行
        deferred1.start()
        deferred2.start()
        println("1...")
        val value1 = deferred1.await() // 调用await方法来获取最终计算的结果值 这里需要等待2s(从deferred1.start调用开始计时)
        println("2...")
        val value2 = deferred2.await() // 调用await方法来获取最终计算的结果值 这里需要等待3s(从deferred2.start调用开始计时)
        println("3...")
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

class HelloKotlin3 {
}