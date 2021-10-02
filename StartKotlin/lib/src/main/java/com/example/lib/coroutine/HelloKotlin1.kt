package com.example.lib.coroutine

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// 协程的第一个示例
// CoroutineScope.launch{}
// 协程结合线程
fun main() {
    /**
     * GlobalScope继承自CoroutineScope
     * CoroutineScope.launch{} 是最常用的Coroutine builders协程构建器，不阻塞当前线程，在后台创建一个新协程
     * 此外 我们没有指定CoroutingDispatcher 因此协程运行于当前线程 注意 协程依附于线程，若线程结束 则协程不复存在
     */
    GlobalScope.launch {// 在后台启动一个新的协程并继续
        delay(1000)// 非阻塞的等待 1 秒钟（默认时间单位是毫秒）
        println("Kotlin coroutine")// 在延迟后打印输出
    }

    println("hello")// 协程已在等待时主线程还在继续
    Thread.sleep(2000)// 阻塞主线程 2 秒钟来保证 JVM 存活
    println("world")// 最后输出
}

/**
 * 输出
 * hello
 * Kotlin coroutine
 * world
 * 分析：
 * GlobalScope.launch开辟一个协程 不阻塞当前线程 继续执行main线程 同时协程开始计时
 * 输出hello
 * main线程进入睡眠 开始计时 此时 协程和线程都在计时
 * 协程计时1s时间到 输出Kotlin coroutine
 * 又过了1s main线程计时总共2s 计时时间到 输出world
 *
 * 扩展
 * 思考 如果将Thread.sleep(2000)修改为 Thread.sleep(500) 会是输出什么呢？
 *
 * 答案输出：
 * hello
 * world
 *
 * 分析：因为协程计时需要1s 主线程计时只计时到0.5s 因此主线程计时完毕时 协程就不存在了
 * 因此没有机会输出Kotlin coroutine
 */

class HelloKotlin1 {
}