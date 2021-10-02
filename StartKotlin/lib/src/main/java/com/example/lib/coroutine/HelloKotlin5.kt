package com.example.lib.coroutine

import kotlinx.coroutines.*

/**
 * Job的join方法
 * 它会挂起协程 直到Job完成
 * join能够实现多个协程合作 即 一个协程等待另一个协程完成后执行
 *
 * Job是一个后台的Job。概念上讲，job是一个可以取消的 有生命周期的东西，job完成后它的生命周期就结束了
 * A background job. Conceptually, a job is a cancellable thing with a life-cycle that culminates in its completion.
 */
fun main() = runBlocking {
    val myJob: Job = GlobalScope.launch {//GlobalScope.launch开启协程 协程不阻塞当前线程 并计时1s
        delay(1000)
        println("Kotlin Coroutines")
    }
    println("hello")// 主线程继续执行 输出hello
    myJob.join() // myJob.join() 挂起协程(GlobalScope.launch创建的协程) 直到myJob执行完毕
    println("world")
}

/**
 * join 方法详解
 * join方法会挂起当前协程直到job完成。当job出于任何原因而完成该调用就可以正常恢复(没有异常的情况下) 并且Job依赖的协程仍然是active的
 * Suspends the coroutine until this job is complete. This invocation resumes normally (without exception)
 * when the job is complete for any reason and the [Job] of the invoking coroutine is still [active][isActive].
 * 如果Job仍然处于new state的状态 该方法也会启动对应的协程
 * This function also [starts][Job.start] the corresponding coroutine if the [Job] was still in _new_ state.
 *
 * 只有这个job的所有子job完成 当前job才能完成
 * Note that the job becomes complete only when all its children are complete.
 *
 * 这个挂起方法是可以取消的并且一直在检查 调用协程Job是否被取消
 * This suspending function is cancellable and **always** checks for a cancellation of the invoking coroutine's Job.
 * 如果在调用此挂起函数时或在挂起时调用协程的 [Job] 被取消或完成，则此函数将抛出 [CancellationException]。
 * If the [Job] of the invoking coroutine is cancelled or completed when this
 * suspending function is invoked or while it is suspended, this function
 * throws [CancellationException].
 *
 * 特别是，这意味着父协程在使用 `launch(coroutineContext) { ... }` 构建器启动的子协程上调用 `join`，如果子进程崩溃，则会抛出 [CancellationException]，
 * 除非在上下文中安装了非标准的 [CoroutineExceptionHandler]。
 * In particular, it means that a parent coroutine invoking `join` on a child coroutine that was started using
 * `launch(coroutineContext) { ... }` builder throws [CancellationException] if the child
 * had crashed, unless a non-standard [CoroutineExceptionHandler] is installed in the context.
 *
 * 该函数可以在带有 [onJoin] 子句的 [select] 调用中使用。 使用 [isCompleted] 无需等待即可检查此作业是否已完成。
 * This function can be used in [select] invocation with [onJoin] clause.
 * Use [isCompleted] to check for a completion of this job without waiting.
 *
 * [cancelAndJoin] 函数结合了 [cancel] 和 `join` 的调用。
 * There is [cancelAndJoin] function that combines an invocation of [cancel] and `join`.
 */

/**
 * 输出
 * hello
 * Kotlin Coroutines
 * world
 */

class HelloKotlin5 {
}