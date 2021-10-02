package com.example.lib.coroutine

import kotlin.concurrent.thread

/**
 * CoroutineScope.launch{} 使用线程的等价写法
 * 使用纯线程实现HelloKotlin1
 */
fun main() {

    // thread是Kotlin的一个方法 接受若干参数 最后一个参数是一个lambda表达式 他是thread的执行体
    // thread创建的线程默认自动执行
    thread {
        Thread.sleep(1000)// 子线程不会阻塞主线程 计时1s
        println("Kotlin coroutine")
    }

    println("hello")// 立即输出
    Thread.sleep(2000)// 阻塞主线程2s
    println("world")// 最后输出
}


/**
 * 以下代码复制自kotlin.concurrent.Thread.kt
 *
 * Creates a thread that runs the specified [block] of code.
 * 创建一个线程 其运行体是参数block
 *
 * @param start if `true`, the thread is immediately started.
 * 参数start如果为true 线程会立即执行
 * @param isDaemon if `true`, the thread is created as a daemon thread. The Java Virtual Machine exits when
 * the only threads running are all daemon threads.
 * 参数isDaemon如果是true 线程会被创建为守护线程。Java虚拟机会在所有存活线程都是守护线程的时候退出
 * @param contextClassLoader the class loader to use for loading classes and resources in this thread.
 * 参数contextClassLoader 当前线程用来加载类和资源的类加载器
 * @param name the name of the thread.
 * 参数name 当前线程的名称
 * @param priority the priority of the thread.
 * 参数priority 当前线程的优先级
 */
public fun thread(
    start: Boolean = true,
    isDaemon: Boolean = false,
    contextClassLoader: ClassLoader? = null,
    name: String? = null,
    priority: Int = -1,
    block: () -> Unit
): Thread {
    val thread = object : Thread() {
        public override fun run() {
            block()
        }
    }
    if (isDaemon)
        thread.isDaemon = true
    if (priority > 0)
        thread.priority = priority
    if (name != null)
        thread.name = name
    if (contextClassLoader != null)
        thread.contextClassLoader = contextClassLoader
    if (start)
        thread.start()
    return thread
}

class HelloKotlin2 {
}