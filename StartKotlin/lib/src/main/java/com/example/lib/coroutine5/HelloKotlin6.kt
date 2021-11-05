package com.example.lib.coroutine5

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

/**
 * Flow的取消
 * Flow实际上没有提供取消的方法 Flow的取消依赖于协程，如果Flow依附的协程取消了，则Flow也会取消
 *
 * Flow的收集操作是可以取消的，前提是Flow在一个可以取消挂起函数（如delay）中被挂起了，除此之外，我们无法
 * 通过其他方式取消Flow
 */

private fun myMethod(): Flow<Int> = flow {
    for (i in 1..4) {
        delay(100)
        println("emit $i")
        emit(i)
    }
}

fun main() = runBlocking {
    // 协程超过280毫秒会被取消
    withTimeoutOrNull(280) {// 如果改成withTimeout会抛出TimeoutCancellationException
        myMethod().collect {
            println(it)
        }
    }
    println("finished")
}

class HelloKotlin6 {
}