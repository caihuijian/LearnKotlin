package com.example.lib.coroutine4

import kotlinx.coroutines.Job
import kotlinx.coroutines.isActive
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

/**
 * Job详解
 *
 * 1.如何获取Job
 * 2.为什么可以这么用
 *
 * 类似于CoroutineScope.launch的方法 它返回一个Job对象 这个要获取对象比较容易
 * 但是像runBlocking这种不将Job返回的方法 如何获取Job对象呢
 * 协程Job是归属其上下文的一部分，Kotlin为我们提供了一种简洁的方式获取协程自身的Job对象
 * 即 coroutineContext[Job]表达式来访问上下文中的对象
 */

fun main() = runBlocking {
    val job: Job? = coroutineContext[Job]
    println(job)
    // public val CoroutineContext.isActive: Boolean
    //    get() = this[Job]?.isActive == true
    // The coroutineContext.isActive expression is a shortcut for coroutineContext[Job]?.isActive == true. See Job.isActive.
    println(coroutineContext.isActive)
    println(coroutineContext[Job]?.isActive)//等价于上面
}

/**
 * 输出
 * "coroutine#1":BlockingCoroutine{Active}@2d38eb89
 */

/**
 * 1.coroutineContext属性是什么
 * coroutineContext是接口 CoroutineScope的一个成员变量 因此基本所有协程都可以直接访问该变量
 * 以下是官方对于coroutineContext变量的介绍
 *
 * The context of this scope.
 * 当前作用域的上下文
 * Context is encapsulated by the scope and used for implementation of coroutine builders that are extensions on the scope.
 * Accessing this property in general code is not recommended for any purposes except accessing the [Job] instance for advanced usages.
 * 上下文被当前作用域封装 用于作为作用域扩展的协程构建器的实现
 * 除了访问 [Job] 实例以进行高级用途外，不建议出于任何其他目的在通常代码中访问此属性。
 *
 * By convention, should contain an instance of a [job][Job] to enforce structured concurrency.
 * 按照惯例，应该包含一个 [Job] 的实例来强制结构化并发。
 *
 *
 *
 * 2.coroutineContext属性详解：
 * coroutineContext在AbstractCoroutine有一个实现
 * protected val parentContext: CoroutineContext
 * public final override val context: CoroutineContext = parentContext + this
 * // The context of this scope which is the same as the [context] of this coroutine.
 * public override val coroutineContext: CoroutineContext get() = context
 * 而 runBlocking间接实现了CoroutineScope 因此在runBlocking中可以直接使用coroutineContext对象
 *
 * 3.CoroutineContext
 * Persistent context for the coroutine. It is an indexed set of [Element] instances.
 * An indexed set is a mix between a set and a map.
 * Every element in this set has a unique [Key].
 * CoroutineContext是针对于协程的持久性上下文 它是一种Element实例的带有索引的集合
 * 一个index固定的，介于集合和Map之间的混合体
 * 在这set中 每一个元素含有一个独立的Key
 * 其中有一个属性
 * public operator fun <E : Element> get(key: Key<E>): E?
 * 其作用是Returns the element with the given [key] from this context or `null`.
 * 从当前context返回指定key的元素或者null
 *
 * 4.Job来自哪里 为什么可以通过Job来访问Job对象 通过coroutineContext[Job]表达式来访问上下文中的对象
 * Key for [Job] instance in the coroutine context.
 * 使用Job实例索引可以当前协程上下文中找到Job实例
 *
 * 查看CoroutineContext源码 发现如下定义
 * public interface Key<E : Element>?
 * 官方解释为 Key for the elements of [CoroutineContext]. [E] is a type of element with this key.
 * 而Job定义为 public interface Job : CoroutineContext.Element 他是一个接口 实现了CoroutineContext.Element
 * 他有一个伴生对象public companion object Key : CoroutineContext.Key<Job> 这个伴生对象是协程上下文job实例的key
 * （Key for [Job] instance in the coroutine context.）
 * 因此 Job直接使用 是用了伴生对象的特性
 *
 * 5.输出的BlockingCoroutine是个啥
 * 我们在Builders.kt找到了BlockingCoroutine的定义private class BlockingCoroutine<T>(
 * parentContext: CoroutineContext,
 * private val blockedThread: Thread,
 * private val eventLoop: EventLoop?
 * ) : AbstractCoroutine<T>(parentContext, true)
 *
 * 它的实例化在runBlocking的执行体中
 * val coroutine = BlockingCoroutine<T>(newContext, currentThread, eventLoop)
 * 可以看到runBlocking的协程最终由BlockingCoroutine执行
 */

class HelloKotlin5 {
}