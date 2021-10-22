package com.example.lib.coroutine4

import kotlinx.coroutines.*

/**
 * Dispatchers.Unconfined详解
 * Dispatchers.Unconfined协程分发器会在调用者线程中启动协程，但是仅仅会持续到第一个挂起点；当挂起函数结束后程序恢复运行时，他会继续协程代码的
 * 执行，但是这时执行协程的线程由执行挂起函数的线程决定（挂起函数由哪个线程执行 后续协程就在这个线程运行）。
 * Dispatchers.Unconfined协程分发器适用于这样的一些协程（协程不由固定线程执行 而由任意线程执行）：它既不会消耗CPU时间，同时也不会更新任何共享的数据（特定于具体的线程）
 *
 * Dispatchers.Unconfined是一种高级的机制，他对于某些特殊情况是很有帮助的：协程执行的分发是不需要的或者协程的分发会产生意料之外的副作用的，这是因为协程中的操作必须立即执行
 *
 * 日常中 我们极少使用Dispatchers.Unconfined分发器
 *
 *
 *
 * Dispatchers.Unconfined官方介绍:
 * A coroutine dispatcher that is not confined to any specific thread.
 * 一种不会指定特定线程的协程分发器
 * It executes initial continuation of the coroutine in the current call-frame
 * and lets the coroutine resume in whatever thread that is used by the corresponding suspending function, without
 * mandating any specific threading policy. Nested coroutines launched in this dispatcher form an event-loop to avoid
 * stack overflows.
 * 它会在当前调用帧中继续执行初始的协程 并且让协程继续在任何线程执行，该线程是之前对应挂起函数所在的线程，而不会强制指定任何特别协程的策略
 * 来自一个事件循环的嵌套协程使用这种分发者可以避免堆栈溢出
 *
 * ### Event loop
 * Event loop semantics is a purely internal concept and have no guarantees on the order of execution
 * except that all queued coroutines will be executed on the current thread in the lexical scope of the outermost
 * unconfined coroutine.
 * 事件循环只是一个单纯的内部概念，它不保证执行的属性，只确定协程队列中的协程会在当前线程执行
 *
 *
 * For example, the following code:
 * ```
 * withContext(Dispatchers.Unconfined) {
 *    println(1)
 *    withContext(Dispatchers.Unconfined) { // Nested unconfined
 *        println(2)
 *    }
 *    println(3)
 * }
 * println("Done")
 * ```
 * 这个程序可能输出123 或132 但是Done一定是最后打印
 * Can print both "1 2 3" and "1 3 2", this is an implementation detail that can be changed.
 * But it is guaranteed that "Done" will be printed only when both `withContext` are completed.
 *
 *
 * Note that if you need your coroutine to be confined to a particular thread or a thread-pool after resumption,
 * but still want to execute it in the current call-frame until its first suspension, then you can use
 * an optional [CoroutineStart] parameter in coroutine builders like
 * [launch][CoroutineScope.launch] and [async][CoroutineScope.async] setting it to the
 * the value of [CoroutineStart.UNDISPATCHED].
 *
 */

fun main() = runBlocking<Unit> {
    launch(Dispatchers.Unconfined) {
        println("Dispatchers.Unconfined， thread：${Thread.currentThread().name}")
        delay(300)
        println("Dispatchers.Unconfined， thread：${Thread.currentThread().name}")
    }

    launch {
        println("No params， thread：${Thread.currentThread().name}")
        delay(2000)
        println("No params， thread：${Thread.currentThread().name}")
    }

}

/**
 * 输出
 * Dispatchers.Unconfined， thread：main
 * No params， thread：main
 * Dispatchers.Unconfined， thread：kotlinx.coroutines.DefaultExecutor
 * No params， thread：main
 *
 * DefaultExecutor它是一个object 并且是一个runnable 本身与时间循环相关
 * internal actual object DefaultExecutor : EventLoopImplBase(), Runnable
 *
 */

class HelloKotlin2 {
}