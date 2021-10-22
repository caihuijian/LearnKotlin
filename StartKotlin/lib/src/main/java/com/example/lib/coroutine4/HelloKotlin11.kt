package com.example.lib.coroutine4

import kotlinx.coroutines.*

/**
 * 协程 线程与ThreadLocal
 *
 * 协程不会绑定到特定的线程（比如yield协程后 协程下次继续执行可能会切换到另外的线程
 * 又比如HelloKotlin4中可以切换协程上下文，切换上下文的同时可能就切换了线程）
 * ThreadLocal却是和线程绑定的
 *
 * 本节的目的是协程在A线程运行时 可以得到线程A中保存的数据
 * 如果协程切换到B线程运行 又可以得到线程B中保存的数据
 * 在A B 线程之间来回切换 不会导致数据丢失 错乱
 *
 * 关于ThreadLocal可以参考我之前的文章
 * https://blog.csdn.net/u011109881/article/details/119334195
 * 需要注意其set get方法 以及其中的Map对象的Key 与 Value
 *
 * 最终会发现本案例没有什么特殊的 就是不同的线程中的ThreadLocal里面保存的值不同
 *
 * 可以加上配置 -Dkotlinx.coroutines.debug
 */
private val threadLocal = ThreadLocal<String>()
private fun log(logMessage: String) = println("[${Thread.currentThread().name}] $logMessage")

fun main() = runBlocking {
    threadLocal.set("main")
    log("current thread local value ${threadLocal.get()}")

    val job = launch(Dispatchers.Default + threadLocal.asContextElement(value = "launch")) {
        log("launch current thread local value ${threadLocal.get()}")
        yield()//将当前方法执行者让出来 让其他线程运行
        log("after yield current thread local value ${threadLocal.get()}")
    }
    job.join()
    log("last current thread local value ${threadLocal.get()}")
}

/**
 * asContextElement的部分官方文档
 * 定义：
 * public fun <T> ThreadLocal<T>.asContextElement(value: T = get()): ThreadContextElement<T> = ThreadLocalElement(value, this)
 *
 * Wraps [ThreadLocal] into [ThreadContextElement]. The resulting [ThreadContextElement]
 * maintains the given [value] of the given [ThreadLocal] for coroutine regardless of the actual thread its is resumed on.
 * By default [ThreadLocal.get] is used as a value for the thread-local variable, but it can be overridden with [value] parameter.
 * Beware that context element **does not track** modifications of the thread-local and accessing thread-local from coroutine
 * without the corresponding context element returns **undefined** value. See the examples for a detailed description.
 *
 * asContextElement是ThreadLocal<T>的扩展方法 它将ThreadLocal封装为ThreadContextElement，ThreadContextElement为协程中给定的ThreadLocal维护给定
 * 的value 而无论该协程运行在那个线程。
 * hreadLocal.get用作thread-local变量的默认值，但是它可以被参数value覆盖
 * 注意上下文元素不会追踪thread-local的修改 并且从不对应的上下文元素访问协程的thread-local会返回不确定的值 参见示例
 *
 *
 * Yield挂起方法:
 * public suspend fun yield(): Unit
 *
 * Yields the thread (or thread pool) of the current coroutine dispatcher to other coroutines to run if possible.
 * yield代表让步 大致意思是 如果可能，让当前协程分发者或其他协程的other线程（或线程池）接着运行下面的代码
 * This suspending function is cancellable.
 * 该挂起方法可以被取消
 * If the [Job] of the current coroutine is cancelled or completed when this suspending function is invoked or while
 * this function is waiting for dispatch, it resumes with a [CancellationException].
 * 如果当挂起方法被调用或该方法正在等待分发的时候，当前协程的Job被取消或者完成，最终会引发 CancellationException异常
 *
 * **Note**: This function always [checks for cancellation][ensureActive] even when it does not suspend.
 *
 *
 * 输出
 * [main @coroutine#1] current thread local value main
 * [DefaultDispatcher-worker-1 @coroutine#2] launch current thread local value launch
 * [DefaultDispatcher-worker-1 @coroutine#2] after yield current thread local value launch
 * [main @coroutine#1] last current thread local value main
 *
 * 我这里的输出与视频不一样 yield之后没有切换线程
 */

class HelloKotlin11 {
}