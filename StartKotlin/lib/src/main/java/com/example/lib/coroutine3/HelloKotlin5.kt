package com.example.lib.coroutine3

import kotlinx.coroutines.*
import java.lang.IllegalArgumentException
import kotlin.system.measureTimeMillis

/**
 * 正确的异步风格的代码
 *
 * 推荐使用一个挂起函数调用需要并行执行的另外的挂起方法
 * 但是还是不明白HelloKotlin4中的代码有什么缺陷
 * 使用async进行结构化并发程序开发
 */
fun main() = runBlocking {
    val elapsedTime = measureTimeMillis {
        println("the answer is ${intSum()}")
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

private suspend fun intSum(): Int = coroutineScope {// coroutineScope会创建一个新的协程作用域
    val deferred1 = async { intValue1() }
    val deferred2 = async { intValue2() }
    deferred1.await() + deferred2.await()
}

class HelloKotlin5 {
}