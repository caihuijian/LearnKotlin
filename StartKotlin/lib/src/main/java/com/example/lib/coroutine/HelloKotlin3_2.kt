package com.example.lib.coroutine

import kotlinx.coroutines.*
import kotlinx.coroutines.CoroutineStart.*
import kotlinx.coroutines.launch
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * 前面的用到知识点详解
 */

fun main() {
    GlobalScope.launch {

    }
}

// CoroutineScope详解
// 作用：为一个新的协程定义一个作用域 以及嵌套协程的规则
// 以下摘自Kotlin源码1.3.9
val CoroutineScopeExplain = """
kotlinx.coroutines CoroutineScope.kt 
public interface CoroutineScope
为一个新的协程定义一个作用域。每一个协程构建器（例如launch async等）都是CoroutineScope的一个扩展，并且继承了它的coroutineContext来自动传播CoroutineScope的上下文元素和可取消的特性
Defines a scope for new coroutines. Every coroutine builder (like launch, async, etc) is an extension on CoroutineScope and inherits its coroutineContext to automatically propagate all its elements and cancellation.
获取独立的scope的最佳方式是通过CoroutineScope() 以及 MainScope()等工厂方法。额外的上下文元素可以使用加运算符append到scope
The best ways to obtain a standalone instance of the scope are CoroutineScope() and MainScope() factory functions. Additional context elements can be appended to the scope using the plus operator.
Convention for structured concurrency
手动实现CoroutineScope接口是不推荐的，更推荐使用委托。习惯上，scope的上下文应该包含一个job的实例，以确保强制结构化并发（以便通过取消传播来强制执行结构化并发的规则。）
Manual implementation of this interface is not recommended, implementation by delegation should be preferred instead. By convention, the context of a scope should contain an instance of a job to enforce the discipline of structured concurrency with propagation of cancellation.
每一个协程构建器（例如launch, async等等）以及每一个作用域方法（例如coroutineScope, withContext等等）都提供了自己的scope和它自己的Job实例到它的运行的内部代码块中。 根据约定，他们都等待他们块内的所有协程完成，然后再完成自己（协程嵌套协程 外部的协程要等到内部的协程执行完毕 才能结束自身）从而强制执行结构化并发
Every coroutine builder (like launch, async, etc) and every scoping function (like coroutineScope, withContext, etc) provides its own scope with its own Job instance into the inner block of code it runs. By convention, they all wait for all the coroutines inside their block to complete before completing themselves, thus enforcing the structured concurrency. See Job documentation for more details.
Android usage
Android has first-party support for coroutine scope in all entities with the lifecycle. See the corresponding documentation .
Custom usage
CoroutineScope应当在一个实体中被实现或声明为一个属性 并且有一个定义良好的生命周期 他们负责启动子的协程（这种实体在Android中有一个好的例子 就是Activity）
CoroutineScope should be implemented or declared as a property on entities with a well-defined lifecycle that are responsible for launching children coroutines, for example:
class MyUIClass {
    val scope = MainScope() // the scope of MyUIClass

    fun destroy() { // destroys an instance of MyUIClass
        scope.cancel() // cancels all coroutines launched in this scope
        // ... do the rest of cleanup here ...
    }

    /*
     * 注意：如果当前实例被销毁 或者任何已经启动的协程在launch内部抛出异常，那么所有嵌套的协程会被取消
     * Note: if this instance is destroyed or any of the launched coroutines
     * in this method throws an exception, then all nested coroutines are cancelled.
     */
        fun showSomeData() = scope.launch { // launched in the main thread
           // ... here we can use suspending functions or coroutine builders with other dispatchers
           draw(data) // draw in the main thread
        }
    }
""".trimIndent()

/**
 * CoroutineScope.launch详解
 * CoroutineScope.launch 启动一个新的协程而不会阻塞当前线程 默认情况下 协程会立刻调度执行
 * 启动行为通过start指定
 */
val CoroutineScopeDotLaunch = """
    可以看到CoroutineScope.launch接受三个参数 前两个参数有默认值 返回一个Job对象
    suspend关键字用来定义一个挂起函数 该关键字只能用在协程内部或者一个suspend函数中
    kotlinx.coroutines Builders.common.kt public fun CoroutineScope.launch(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() → Unit
    ): Job
 * 启动一个新的协程而不会阻塞当前线程 并且以Job的形式返回当前协程的引用
 * Launches a new coroutine without blocking the current thread and returns a reference to the coroutine as a [Job].
 * 当生成的job被取消时 当前协程也会取消
 * The coroutine is cancelled when the resulting job is [cancelled][Job.cancel].
 *
 * 协程上下文继承自CoroutineScope。额外的上下文元素可以通过context参数指定。
 * The coroutine context is inherited from a [CoroutineScope]. Additional context elements can be specified with [context] argument.
 * 如果上下文没有指定dispatcher调度器/分发器 或者任何其他ContinuationInterceptor（） 那么默认会使用Dispatchers.Default
 * If the context does not have any dispatcher nor any other [ContinuationInterceptor], then [Dispatchers.Default] is used.
 * 父Job也继承自CoroutineScope 但是它也可以被一个对应的上下文元素覆盖掉
 * The parent job is inherited from a [CoroutineScope] as well, but it can also be overridden
 * with a corresponding [context] element.
 *
 * 默认情况下 协程会立刻调度执行
 * By default, the coroutine is immediately scheduled for execution.
 * 其他开启选项可以通过start参数指定
 * Other start options can be specified via `start` parameter. See [CoroutineStart] for details.
 * 一个可选的 start参数是CoroutineStart.LAZY以延迟启动协程 在这种情况下 协程Job被创建为 new state
 * An optional [start] parameter can be set to [CoroutineStart.LAZY] to start coroutine _lazily_. In this case,
 * 它可以通过调用Job.start方法被显示地启动 也可以在第一次调用Job.join时被隐式启动
 * the coroutine [Job] is created in _new_ state. It can be explicitly started with [start][Job.start] function
 * and will be started implicitly on the first invocation of [join][Job.join].
 *
 * 当前协程地未捕获异常默认会取消当前上下文地父job
 * Uncaught exceptions in this coroutine cancel the parent job in the context by default
 * （除非CoroutineExceptionHandler被指定） 这意味着当launch被其他协程地上下文调用时 任何未捕获异常都会导致父协程取消
 * (unless [CoroutineExceptionHandler] is explicitly specified), which means that when `launch` is used with
 * the context of another coroutine, then any uncaught exception leads to the cancellation of the parent coroutine.
 *
 * See [newCoroutineContext] for a description of debugging facilities that are available for a newly created coroutine.
 *
 * @param context additional to [CoroutineScope.coroutineContext] context of the coroutine.
 * @param start coroutine start option. The default value is [CoroutineStart.DEFAULT].
 * @param block the coroutine code which will be invoked in the context of the provided scope.

""".trimIndent()

/**
 * CoroutineScope.launch第二个参数start详解
 */
val launchStart = """
    启动方式时枚举类型CoroutineStart的实例
 * Defines start options for coroutines builders.
 * 定义了协程构建器的启动方式 它被用在CoroutineScope.launch CoroutineScope.async以及其他构建器方法的start参数中
 * It is used in `start` parameter of [launch][CoroutineScope.launch], [async][CoroutineScope.async], and other coroutine builder functions.
 *
 * The summary of coroutine start options is:
 *                立刻根据上下文调度协程执行
 * * [DEFAULT] -- immediately schedules coroutine for execution according to its context;
                延时启动协程 只有需要的时候才启动 
 * * [LAZY] -- starts coroutine lazily, only when it is needed;
                 根据上下为自动（以不可取消的方式）调度协程
 * * [ATOMIC] -- atomically (in a non-cancellable way) schedules coroutine for execution according to its context;
                 立即执行协程直到遇到它在当前线程的第一个挂起点
 * * [UNDISPATCHED] -- immediately executes coroutine until its first suspension point _in the current thread_.    
""".trimIndent()


class HelloKotlin3_2 {
}