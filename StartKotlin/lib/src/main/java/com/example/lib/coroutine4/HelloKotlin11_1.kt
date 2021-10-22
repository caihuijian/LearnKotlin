package com.example.lib.coroutine4

import kotlinx.coroutines.*

/**
 * 猜想一下这里的代码的输出
 */
private val threadLocal = ThreadLocal<String>()
private fun log(logMessage: String) = println("[${Thread.currentThread().name}] $logMessage")

fun main() = runBlocking {
    threadLocal.set("main")
    log("current thread local value ${threadLocal.get()}")

    val job = launch(Dispatchers.Default) {
        threadLocal.set("AA")
        log("current thread local value ${threadLocal.get()}")
        yield()
        log("current thread local value ${threadLocal.get()}")
    }
    job.join()
    log("current thread local value ${threadLocal.get()}")
}

/**
 * 输出
 *
 * [main] current thread local value main
 * [DefaultDispatcher-worker-1] current thread local value AA
 * [DefaultDispatcher-worker-1] current thread local value AA
 * [main] current thread local value main
 *
 * 不明白threadLocal.asContextElement的意义了 不指定这个 线程里面的数据还是可以在协程间切换
 * 那么asContextElement的意义何在
 */

class HelloKotlin11_1 {
}