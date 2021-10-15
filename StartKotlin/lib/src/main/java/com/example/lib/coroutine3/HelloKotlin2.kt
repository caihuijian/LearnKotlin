package com.example.lib.coroutine3

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * 使用async与await实现并发
 * 从概念上讲，async就像是launch一样。它会开启一个单独的协程，这个协程也是轻量级线程，可以与其他协程并发工作。区别在于，launch
 * 会返回一个Job 但是Job不会持有任何结果值，而async会返回一个Deferred，Deferred是一个轻量级非阻塞的future，代表一个promise
 * 可以在之后提供一个结果值
 *
 * 可以通过在一个deferred上调用await方法来获取最终计算的结果值，Deferred是Job的子类，因此可以在需要时取消Job
 */
fun main() = runBlocking {
    val elapsedTime = measureTimeMillis {
        println("start calculate...")
        val deferred1 = async { intValue1() } // 并行调用挂起方法
        val deferred2 = async { intValue2() } // 并行调用挂起方法
        val value1 = deferred1.await() // 调用await方法来获取最终计算的结果值
        val value2 = deferred2.await()
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

class HelloKotlin2 {
}