package com.example.lib.coroutine4

import kotlinx.coroutines.*
import java.util.concurrent.Executors

/**
 * 协程与线程的关系：协程依赖于线程执行
 *
 * 协程上下文：（Coroutine Context）
 * 协程总是在某个上下文中运行，这个上下文实际由CoroutineContext的一个实例来表示，该实例是由Kotlin标准库定义的
 * 协程上下文本质是各种元素的集合。主要元素包括 协程的Job 协程分发器Dispatcher
 *
 * 协程分发器：（Dispatcher）
 * CoroutineDispatcher是一个抽象类 Base class to be extended by all coroutine dispatcher implementations.
 * 所有协程分发器都应该继承这个抽象类
 *
 * 其主要功能是决定协程运行在哪个线程。
 * 协程分发器的常见case有
 * 1.分发器可以将协程的执行限制在一个具体指定的线程
 * 2.可以将协程分发到一个线程池中，由线程池中的某个线程执行协程
 * 3.不加任何限制的去执行协程的代码（在这种情况下 我们所指定的协程代码到底是由哪个线程或线程池执行是不确定的，它需要根据程序的实际执行情况确定；
 * 这种方式的协程分发器我们开发中极少使用，它只用在一些极为特殊的情况下）
 *
 * 所有的协程构建器（coroutine builder）如launch async都会接受一个可选的CoroutineContext参数，该参数可以用于显示指定协程所运行的
 * 分发器及其他上下文元素
 *
 * 背景： CoroutineContext与CoroutineDispatcher的关系
 * 首先CoroutineDispatcher是所有协程分发器的基类
 * 我们查看一个协程分发器的定义例如（Dispatchers.Default） 其类型是CoroutineDispatcher
 * CoroutineDispatcher继承自AbstractCoroutineContextElement(ContinuationInterceptor)并实现了ContinuationInterceptor
 * 注意ContinuationInterceptor实现了接口 CoroutineContext.Element
 * 而CoroutineContext.Element接口实现了CoroutineContext
 * 也就是说CoroutineDispatcher本身也是一个CoroutineContext
 * 因此 CoroutineScope.launch的第一个参数虽然是CoroutineContext类型 但是我们还是可以传入CoroutineDispatcher的类型 例如
 * Dispatchers.Unconfined Dispatchers.Default等
 *
 *
 * 示例：协程与线程的关系
 * 程序分析：
 * 1.当通过launch来启动协程并且不指定协程分发器时，他会继承启动它的那个CoroutineScope的协程上下文与分发器。对本案例，他会继承runBlocking
 * 的上下文，而runBlocking是运行在主线程中的 因此该例子协程运行在main线程
 * 2.Dispatchers.Unconfined是一种特殊的协程分发器，它在本案例中运行在main线程，但实际上，其运行机制与不指定协程分发器完全不同，日常极少
 * 使用该协程分发器 后面详细看看这种分发器 目前了解即可
 * 3.Dispatchers.Default是默认协程分发器，当协程是通过GlobalScope来启动的时候，他会使用该默认的分发器启动协程，它会使用一个后台的
 * 共享线程池来运行协程。因此，launch(Dispatchers.Default){} 等价于GlobalScope.launch {}
 * 我们查看Default的定义如下
 *
 * The default [CoroutineDispatcher] that is used by all standard builders like
 * [launch][CoroutineScope.launch], [async][CoroutineScope.async], etc
 * if no dispatcher nor any other [ContinuationInterceptor] is specified in their context.
 * 该默认协程分发器会被标准协程构建器（例如CoroutineScope.launch CoroutineScope.async等使用）
 * 这种情况下，如果协程构建器没有指定其他的ContinuationInterceptor或者协程分发器，那么他们就会使用Dispatchers.Default来创建协程
 *
 * It is backed by a shared pool of threads on JVM. By default, the maximal level of parallelism used
 * by this dispatcher is equal to the number of CPU cores, but is at least two.
 * Level of parallelism X guarantees that no more than X tasks can be executed in this dispatcher in parallel.
 * 它背后由一个JVM的共享线程池支撑。默认情况下，最大并行度等于CPU核心数，但是并行数至少有两个
 * 并行度 X 保证在这个调度器中并行执行的任务不超过 X 个
 *
 * 看完定义对于Dispatchers.Default 我们知道
 * a.CoroutineScope.launch CoroutineScope.async等协程构建器默认会使用这种协程分发器
 * b.Dispatchers.Default背后由JVM共享线程池来支撑，具体由线程池中的哪个线程执行协程 开发者不确定
 * c.最大并行度等于CPU核心数，但是并行数至少有两个
 *
 * 4.Executors.newSingleThreadExecutor().asCoroutineDispatcher() 创建一个单线程的线程池，该线程池中的线程用来执行我们所指定的
 * 协程代码；在实际开发中，使用专门的线程运行协程的代价是非常高的，因此在协程执行完毕后，我们必须释放资源，这里就需要使用close方法来关闭相应的
 * 协程分发器，从而释放资源；也可以将协程分发器存储在顶层变量中，以便在程序的其他地方进行复用
 *
 * 查看官方文档关于CoroutineDispatcher的介绍可以加深我们对于各种协程分发器的理解
 * The following standard implementations are provided by kotlinx.coroutines as properties on the Dispatchers object:
 *
 *
 * Dispatchers.Default — is used by all standard builders if no dispatcher or any other ContinuationInterceptor is
 * specified in their context. It uses a common pool of shared background threads. This is an appropriate choice for
 * compute-intensive coroutines that consume CPU resources.
 *
 * Dispatchers.IO — uses a shared pool of on-demand created threads and is designed for offloading of IO-intensive
 * blocking operations (like file I/O and blocking socket I/O).
 *
 * Dispatchers.Unconfined — starts coroutine execution in the current call-frame until the first suspension,
 * whereupon the coroutine builder function returns. The coroutine will later resume in whatever thread used by the
 * corresponding suspending function, without confining it to any specific thread or pool. The Unconfined dispatcher
 * should not normally be used in code.
 *
 * Private thread pools can be created with newSingleThreadContext and newFixedThreadPoolContext.
 * An arbitrary Executor can be converted to a dispatcher with the asCoroutineDispatcher extension function.
 * This class ensures that debugging facilities in newCoroutineContext function work properly.
 *
 */

/**
 * 使用不同的协程分发器对比
 */
fun main() = runBlocking {
    launch {
        println("No params， thread：${Thread.currentThread().name}")
        /**
         * 输出 No params， thread：main
         * 说明这种情况下 该协程运行在主线程
         */
    }

    launch(Dispatchers.Unconfined) {
        // delay(100) // 多这一句 协程就会运行在不同的线程
        println("Dispatchers.Unconfined， thread：${Thread.currentThread().name}")
        /**
         * 输出 Dispatchers.Unconfined， thread：main
         * 说明这种情况下 该协程“碰巧”运行在主线程
         */
    }

    launch(Dispatchers.Default) {
        println("Dispatchers.Default， thread：${Thread.currentThread().name}")
        /**
         * 测试的时候输出 Dispatchers.Default， thread：DefaultDispatcher-worker-2
         *
         */
    }

    // 改进写法
    val threadDispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
    launch(threadDispatcher) {
        println("SingleThreadExecutor， thread：${Thread.currentThread().name}")
        /**
         * close方法：
         * Closes this coroutine dispatcher and shuts down its executor.
         * It may throw an exception if this dispatcher is global and cannot be closed.
         */
        threadDispatcher.close()// 如果不加这句 程序无法退出
        /**
         * 输出 SingleThreadExecutor， thread：pool-1-thread-1
         * 说明这种情况下 该协程运行在我们创建的第一个线程池里的第一个线程
         */
    }

    // 验证GlobalScope.launch等价于launch(Dispatchers.Default)
    GlobalScope.launch {
        println("GlobalScope.launch， thread：${Thread.currentThread().name}")
    }

    println("end")


    /* asCoroutineDispatcher方法详解
     asCoroutineDispatcher是Kotlin为ExecutorService提供的扩展方法 专门用于将ExecutorService创建的线程池作为协程的分发器，即
     协程会运行在该线程池中的线程上
     asCoroutineDispatcher 返回ExecutorCoroutineDispatcher
     ExecutorCoroutineDispatcher 继承自CoroutineDispatcher
     而CoroutineDispatcher是所有协程分发器的基类
     按照这种写法 线程无法终止 程序不会停止 需要改进写法
     launch(Executors.newSingleThreadExecutor().asCoroutineDispatcher()) {
         println("SingleThreadExecutor， thread：${Thread.currentThread().name}")
     }*/
}

class HelloKotlin1 {
}