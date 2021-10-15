package com.example.lib.coroutine3

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.lang.IllegalArgumentException
import kotlin.system.measureTimeMillis

/**
 * 异步风格的代码
 *
 * 不推荐？？原因不理解 TODO
 */
fun main() {
    val elapsedTime = measureTimeMillis {
        val deferred1 = intValue1Async() // 调用“普通”方法
        val deferred2 = intValue2Async() // 调用“普通”方法
        runBlocking {
            println("the answer is :${deferred1.await()} + ${deferred2.await()} = ${deferred1.await() + deferred2.await()}")
        }
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

// GlobalScope.async返回一个Deferred
// 普通方法内部调用异步挂起方法 实际使用该方法看起来就像操作普通方法操作异步方法一样
fun intValue1Async() = GlobalScope.async {
    intValue1()
}

// 看起来是普通方法 实际是异步方法
// 1 因为这个方法是普通方法 所以方法调用时可以当做普通方法调用
// 2 如果要获取值 需要使用协程的异步方式执行来获取值
fun intValue2Async() = GlobalScope.async {
    intValue2()
}

class HelloKotlin4 {
}