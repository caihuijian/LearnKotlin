package com.example.lib.coroutine2

import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

/**
 * 协程的超时 设置withTimeout
 * 我们在使用协程时，如果取消了协程，那么很大一部分原因在于协程的执行时间超过了指定时间：我们可以通过手工引用与协程对应的Job的
 * 方式来启动一个单独的协程用于取消这个协程，不过Kotlin提供了一个内建的函数帮我们更方便的实现这一点(withTimeout)
 */

fun main() = runBlocking {
    try {
        withTimeout(1900) {
            repeat(5) {// 修改为4 就不会抛出TimeoutCancellationException
                    i ->
                println("hello $i")
                delay(400)
            }
        }
    } catch (ex: TimeoutCancellationException) {

    }
    withTimeout(1900) {
        repeat(5) {// 修改为4 就不会抛出TimeoutCancellationException
                i ->
            println("hello $i")
            delay(400)
        }
    }
}


class HelloKotlin6 {

}