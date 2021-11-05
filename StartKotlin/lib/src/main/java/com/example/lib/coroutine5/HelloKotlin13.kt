package com.example.lib.coroutine5

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

/**
 * Flow Context（Flow 上下文）
 * Flow的收集总是发生在调用协程的上下文中，这个特性叫做上下文保留(Context Preservation)
 * 注意加上-Dkotlinx.coroutines.debug
 */
private fun log(logMessage: String) = println("[${Thread.currentThread().name}] $logMessage")

private fun myMethod(): Flow<Int> = flow {
    log("start")
    for (i in 1..4) {
        emit(i)
    }
}

fun main() = runBlocking {
    // 例如下面的Flow的收集操作发生在runBlocking的上下文 即主线程中
    myMethod().collect {
        log("Collected: $it")
    }
}

/*
输出

[main @coroutine#1] start
[main @coroutine#1] Collected: 1
[main @coroutine#1] Collected: 2
[main @coroutine#1] Collected: 3
[main @coroutine#1] Collected: 4
 */

class HelloKotlin13 {
}