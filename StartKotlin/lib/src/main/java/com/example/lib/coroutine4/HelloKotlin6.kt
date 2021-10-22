package com.example.lib.coroutine4

import kotlinx.coroutines.*

/**
 * 父子协程的关系
 * GlobalScope在父子协程中的特例
 *
 * 当一个协程是通过另一个协程的CoroutineScope来启动的，那么该协程会通过CoroutineScope.coroutineContext来继承其上下文信息，
 * 同时，新协程的Job就会成为父协程的Job的一个孩子；当父协程被取消执行时，该父协程的所有孩子都会通过递归的方式一起取消
 *
 * 特例：GlobalScope启动协程时，对于新启动的协程来说，他是没有父Job的。因此，它不会绑定到其所启动的协程范围上（GlobalScope）,因此
 * 这种情况下，新启动的协程可以独立运行
 * GlobalScope的定义为 public object GlobalScope : CoroutineScope 它是一个object 实现了CoroutineScope接口
 *
 *
 * 官方对GlobalScope的解释为
 * A global [CoroutineScope] not bound to any job.
 * GlobalScope是一个全局的没有绑定到任何job的CoroutineScope
 *
 * Global scope is used to launch top-level coroutines which are operating on the whole application lifetime
 * and are not cancelled prematurely.
 * Another use of the global scope is operators running in [Dispatchers.Unconfined], which don't have any job associated with them.
 * GlobalScope被用来启动顶层协程 这些协程在整个应用生命周期运行且不会过早地取消（注意这里过早地被取消是指被其他协程连带取消 如果是用GlobalScope自己的Job取消
 * 还是可以成功取消的）
 * GlobalScope的另一种用途是结合协程分发器Dispatchers.Unconfined使用，这种情况下启动的协程不会有任何与之关联的job
 *
 * Application code usually should use an application-defined [CoroutineScope]. Using
 * [async][CoroutineScope.async] or [launch][CoroutineScope.launch]
 * on the instance of [GlobalScope] is highly discouraged.
 * 应用代码通常应该使用一个应用程序定义的CoroutineScope。在GlobalScope内部使用CoroutineScope.async或者CoroutineScope.launch是极度不建议的
 *
 *
 * CoroutineScope是什么
 * Defines a scope for new coroutines. Every **coroutine builder** (like [launch], [async], etc)
 * is an extension on [CoroutineScope] and inherits its [coroutineContext][CoroutineScope.coroutineContext]
 * to automatically propagate all its elements and cancellation.
 * CoroutineScope 定义了一个新 Coroutine 的执行 Scope。每个 coroutine builder 都是 CoroutineScope 的扩展函数，
 * 并且自动的继承了当前 Scope 的 coroutineContext 和取消操作
 *
 */
private fun log(logMessage: String) = println("[${Thread.currentThread().name}] $logMessage")

fun main() = runBlocking<Unit> {//runBlocking 在主线程开启了一个协程 该协程会阻塞主线程
    log("outer")
    val job = launch {// launch启动一个新的协程
        log("outer.launch")
        GlobalScope.launch {// GlobalScope.launch再次启动一个协程
            log("job1 hello")
            delay(1000)
            log("job1 world")// 这句话会执行 因为虽然父协程取消了 但GlobalScope.launch没有父协程
        }

        launch {// launch再再次启动一个协程
            delay(100)
            log("job2 hello")
            delay(1000)
            log("job2 world")//这句话不会执行 因为父协程取消之后 子协程也被取消了
        }
    }
    // 最终runBlocking内部有一个launch启动的协程 launch启动的协程内部有两个协程 一个是launch启动的一个是GlobalScope.launch启动的

    delay(500)
    job.cancel()// 这里取消的不是最外层的协程 而是launch启动的协程 // 可以思考一下 如果同时注释这里的job.cancel()和delay(1000) 输出会是什么 学完HelloKotlin7再回来看看呢？
    // launch启动的协程内部有两个协程 按照取消父协程的解释 除了GlobalScope.launch启动的协程 所有子协程都会被取消 可以推测最后输出
    delay(1000)// 注意这里如果注释调这个延时 GlobalScope.launch启动的协程第二次输出也会消失 因为整个程序已经结束了
    log("end")
}

/**
 * 输出
[main @coroutine#1] outer
[main @coroutine#2] outer.launch
[DefaultDispatcher-worker-1 @coroutine#3] job1 hello
[main @coroutine#4] job2 hello
[DefaultDispatcher-worker-1 @coroutine#3] job1 world
[main @coroutine#1] end
 */

class HelloKotlin6 {
}