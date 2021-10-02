package com.example.lib.coroutine

import kotlinx.coroutines.*

/**
 * runBlocking{}
 * 使用纯协程实现HelloKotlin1
 */
fun main() {
    GlobalScope.launch {//GlobalScope.launch创建协程 并且不阻塞当前线程 协程计时1s
        delay(1000)
        println("Kotlin coroutine")
    }

    println("hello")

    runBlocking {
        delay(2000)
    }
    println("world")
}

/**
 * 输出
 * hello
 * Kotlin coroutine
 * world
 *
 * 输出分析：
 *
 * 主线程输出hello
 * 主线程利用runBlocking创建一个协程 该协程阻塞主线程 协程需要计时2s
 * GlobalScope.launch计时1s完毕 输出Kotlin coroutine
 * 再过一秒  runBlocking创建的协程执行完毕 主线程不再阻塞 输出world
 */


/**
 * runBlocking详解
 * runBlocking是kotlin的一个方法 其注释摘抄如下
 *
 * 返回一个新的协程并且阻塞当前线程，阻断直到这个协程执行完毕
 * Runs a new coroutine and **blocks** the current thread _interruptibly_ until its completion.
 * 该方法不应该在协程中使用。他被设计来 桥接常规阻塞代码与以挂起风格写成的库 或是使用在main方法和test中
 * This function should not be used from a coroutine. It is designed to bridge regular blocking code
 * to libraries that are written in suspending style, to be used in `main` functions and in tests.
 *
 *
 * 当前builder的默认CoroutineDispatcher是事件循环的内部实现 它处理此阻塞线程中的延续，直到此协程完成。
 * The default [CoroutineDispatcher] for this builder is an internal implementation of event loop that processes continuations
 * in this blocked thread until the completion of this coroutine.
 * See [CoroutineDispatcher] for the other implementations that are provided by `kotlinx.coroutines`.
 *
 * 当在 [context] 中明确指定 [CoroutineDispatcher] 时，新的协程会在当前线程被阻塞的同时在指定调度程序的上下文中运行。 如果指定的调度程序是另一个 `runBlocking` 的事件循环，则该调用使用外部事件循环。
 * When [CoroutineDispatcher] is explicitly specified in the [context], then the new coroutine runs in the context of
 * the specified dispatcher while the current thread is blocked. If the specified dispatcher is an event loop of another `runBlocking`,
 * then this invocation uses the outer event loop.
 *
 * 如果当前阻塞线程被打断 那么协程job会被取消 并且它的runBlocking调用会抛出InterruptedException
 * If this blocked thread is interrupted (see [Thread.interrupt]), then the coroutine job is cancelled and
 * this `runBlocking` invocation throws [InterruptedException].
 *
 * See [newCoroutineContext][CoroutineScope.newCoroutineContext] for a description of debugging facilities that are available
 * for a newly created coroutine.
 *
 * 参数 context： 协程上下文 默认值是当前线程的一个事件循环
 * @param context the context of the coroutine. The default value is an event loop on the current thread.
 * 参数 block： 协程运行体
 * @param block the coroutine code.
 *
 * @Throws(InterruptedException::class)
 */
class HelloKotlin3 {

}