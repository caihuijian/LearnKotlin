package com.example.lib.coroutine4

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 对比HelloKotlin6 思考下面会如何输出
 * 注意加上-Dkotlinx.coroutines.debug
 */

private fun log(logMessage: String) = println("[${Thread.currentThread().name}] $logMessage")


fun main() = runBlocking<Unit> {
    log("outer")
    val job1 = GlobalScope.launch {
        log("job1 hello")
        delay(1000)
        log("job1 world")
    }

    val job2 = launch {
        delay(100)
        log("job2 hello")
        delay(1000)
        log("job2 world")
    }
    delay(500)
    job1.cancel()
    job2.cancel()
    delay(1000)
    log("end")
}

/**
输出
[main @coroutine#1] outer
[DefaultDispatcher-worker-1 @coroutine#2] job1 hello
[main @coroutine#3] job2 hello
[main @coroutine#1] end
 */

class HelloKotlin6_1 {
}