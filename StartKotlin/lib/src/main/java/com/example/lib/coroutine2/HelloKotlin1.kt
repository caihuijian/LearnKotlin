package com.example.lib.coroutine2

import kotlinx.coroutines.*

/**
 * 协程的取消
 */

fun main() = runBlocking {
    // 得到启用的协程myJob
    val myJob = GlobalScope.launch {
        repeat(200) { i ->
            println("hello $i")
            delay(500)
        }
    }

    delay(1100)
    println("hello world")
    /**
     * Cancels this job with an optional cancellation cause.
     * A cause can be used to specify an error message or to provide other details on the cancellation reason for debugging purposes.
     * See Job documentation for full explanation of cancellation machinery.
     */
    myJob.cancel(CancellationException("test exception"))// 取消协程 取消协程是一个正常的操作 因此实际上CancellationException不会被打印
    /**
     * Suspends the coroutine until this job is complete.
     * 取消协程不是立马结束的 取消协程时 cancel和join经常成对出现
     */
    myJob.join()
//    myJob.cancelAndJoin() // 上面两句等价于cancelAndJoin
    println("end")
}

class HelloKotlin {
}