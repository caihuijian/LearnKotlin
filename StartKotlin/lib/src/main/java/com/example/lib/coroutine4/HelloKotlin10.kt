package com.example.lib.coroutine4

import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Default

/**
 * 让普通类也具有协程特点
 * 将Activity变成协程 这样 在他里面创建的协程都会变成子协程
 * 模拟Android中Activity关闭 所有子协程都关闭
 * 达到协程的生命周期与Activity的生命周期绑定的效果
 * 写法是让Activity继承CoroutineScope 并在Activity销毁时取消协程 这样 其子协程也会一并取消
 *
 * 在Android开发中 如果Activity退出 而依附于Activity的协程却没有取消 会导致内存泄漏
 * 这里我们模拟创建Activity及其销毁
 */

class Activity : CoroutineScope by CoroutineScope(Dispatchers.Default) {
    fun destroy() {
        cancel()//调用代理类的方法
    }

    fun doSthCostTime() {
        repeat(8) { i ->
            launch {
                delay(i * 300L)
                println("coroutine $i is finished")
            }
        }
    }
}

fun main() = runBlocking {
    val activity = Activity()
    println("start coroutine")
    activity.doSthCostTime()
    delay(1300L)

    println("mock activity destroyed")
    activity.destroy()

    delay(3000L)
    println("end")

}

/**
 * CoroutineScope内部实现
 * 官方对于CoroutineScope方法的定义和解释
 * public fun CoroutineScope(context: CoroutineContext): CoroutineScope =
 * ContextScope(if (context[Job] != null) context else context + Job())
 *
 * Creates a [CoroutineScope] that wraps the given coroutine [context].
 * 创建一个包含给定协程上下文的CoroutineScope
 *
 * If the given [context] does not contain a [Job] element, then a default `Job()` is created.
 * This way, cancellation or failure of any child coroutine in this scope cancels all the other children,
 * just like inside [coroutineScope] block.
 * 如果给定的context没有Job元素，那么会创建一个默认的Job，这样，在该范围的任何子协程被取消或出现异常会取消其他子协程，就像其他子协程也在该作用域中
 *
 *
 *
 *
 *
 *
 * CoroutineScope另外一种实现 适用于Android UI线程 即MainScope
 * 该方法专门针对图形界面设计
 * Creates the main [CoroutineScope] for UI components.
 *
 * Example of use:
 * ```
 * class MyAndroidActivity {
 *     private val scope = MainScope()
 *
 *     override fun onDestroy() {
 *         super.onDestroy()
 *         scope.cancel()
 *     }
 * }
 * ```
 *
 * The resulting scope has [SupervisorJob] and [Dispatchers.Main] context elements.
 * If you want to append additional elements to the main scope, use [CoroutineScope.plus] operator:
 * `val scope = MainScope() + CoroutineName("MyActivity")`.
 * public fun MainScope(): CoroutineScope = ContextScope(SupervisorJob() + Dispatchers.Main)
 * 该CoroutineScope包含了SupervisorJob以及Dispatchers.Main上下文元素
 *
 *
 *
 * 关于Dispatchers.Main
 * A coroutine dispatcher that is confined to the Main thread operating with UI objects.
 * This dispatcher can be used either directly or via [MainScope] factory.
 * Usually such dispatcher is single-threaded.
 * Dispatchers.Main是一个绑定到操作UI对象的主线程的协程分发器
 * 该分发器可以直接使用或者通过MainScope工厂使用
 * 通常 该分发器是单线程的
 *
 * Access to this property may throw [IllegalStateException] if no main thread dispatchers are present in the classpath.
 *
 * Depending on platform and classpath it can be mapped to different dispatchers:
 * - On JS and Native it is equivalent of [Default] dispatcher.
 * - On JVM it is either Android main thread dispatcher, JavaFx or Swing EDT dispatcher. It is chosen by
 *   [`ServiceLoader`](https://docs.oracle.com/javase/8/docs/api/java/util/ServiceLoader.html).
 *
 * In order to work with `Main` dispatcher, the following artifacts should be added to project runtime dependencies:
 *  - `kotlinx-coroutines-android` for Android Main thread dispatcher
 *  - `kotlinx-coroutines-javafx` for JavaFx Application thread dispatcher
 *  - `kotlinx-coroutines-swing` for Swing EDT dispatcher
 *
 * In order to set a custom `Main` dispatcher for testing purposes, add the `kotlinx-coroutines-test` artifact to
 * project test dependencies.
 *
 * Implementation note: [MainCoroutineDispatcher.immediate] is not supported on Native and JS platforms.
 *
 * public actual val Main: MainCoroutineDispatcher get() = MainDispatcherLoader.dispatcher
 *
 *
 * CoroutineScope是什么
 * Defines a scope for new coroutines. Every **coroutine builder** (like [launch], [async], etc)
 * is an extension on [CoroutineScope] and inherits its [coroutineContext][CoroutineScope.coroutineContext]
 * to automatically propagate all its elements and cancellation.
 * CoroutineScope 定义了一个新 Coroutine 的执行 Scope。每个 coroutine builder 都是 CoroutineScope 的扩展函数，
 * 并且自动的继承了当前 Scope 的 coroutineContext 和取消操作
 */

class HelloKotlin10 {
}