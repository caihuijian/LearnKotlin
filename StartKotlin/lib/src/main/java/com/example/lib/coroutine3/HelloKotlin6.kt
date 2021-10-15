package com.example.lib.coroutine3

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.lang.IllegalArgumentException

/**
 * 协程嵌套异常取消问题
 * 当一个协程内部嵌套两个协程时
 * 子协程其中一个出现异常 会导致另外一个子协程以及父协程都被取消
 */

fun main() = runBlocking<Unit> {
    try {
        failureComputation()
    } finally {
        println("main end")
    }
}

private suspend fun failureComputation(): Int = coroutineScope {
    val value1 = async {
        try {
            delay(90000)
            50
        } finally {
            println("value1 end")
        }
    }
    val value2 = async<Int> {
        Thread.sleep(2000)
        println("value2 throws exception")
        throw IllegalArgumentException()
    }

    value1.await() + value2.await()
}

class HelloKotlin6 {
}