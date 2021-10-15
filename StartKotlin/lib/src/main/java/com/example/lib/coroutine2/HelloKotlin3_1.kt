package com.example.lib.coroutine2

import kotlinx.coroutines.*

/**
 * 协程取消的注意点
 * 如果协程正处于某个计算过程中 如何正确取消协程2
 *
 * 有两种方式可以让协程能够被成功取消
 * 1 周期性调用一个挂起函数 该挂起函数会检查取消状态 例如挂起函数delay
 * 2 显示地检查取消状态
 *
 * 下面地示例采用第二种方式取消协程
 * 这里我们实际上用的是HelloKotlin1的代码
 *
 * 对比HelloKotlin3 HelloKotlin3_1之所以可以成功取消协程 因为
 * 1.cancel中修改状态
 * 追踪cancel的实现
 * 以JobSupport为例 按照顺序追踪 cancel cancelInternal cancelImpl makeCancelling 内部最终会修改协程isActive的状态
 * 2.delay这个挂起函数检查state
 * delay:
 * Delays coroutine for a given time without blocking a thread and resumes it after a specified time.
 * This suspending function is cancellable.
 * If the Job of the current coroutine is cancelled or completed while this suspending function is waiting,
 * this function immediately resumes with CancellationException.
 * Note that delay can be used in select invocation with onTimeout clause.
 * Implementation note: how exactly time is tracked is an implementation detail of CoroutineDispatcher in the context.
 * Params:
 * timeMillis - time in milliseconds.
 * 延迟指定时间的协程而不会阻塞线程 并且在等待时间到达的时候 协程能够再次启动
 * 该挂起方法是可以取消的
 * 如果当前协程的job在挂起方法正在等待时被取消或完成
 * 那么该方法会被立马继续并抛出CancellationException
 *
 * 总结一下cancel将isActive更改为false 并且挂起函数可以检测到状态变化/或者可以主动判断isActive的值
 * 说到底 协程要能取消成功 需要感知到isActive状态的变化
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

class HelloKotlin3_1 {
}