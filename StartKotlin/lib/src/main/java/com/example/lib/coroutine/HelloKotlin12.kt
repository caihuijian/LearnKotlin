package com.example.lib.coroutine

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * 全局协程类似与守护线程
 * GlobalScope.launch启动的协程就是全局协程
 * 全局协程类似于守护线程（daemon thread） （当主线程执行完毕时 如果剩余的线程全部是守护线程 主线程会直接结束 守护线程的生命周期也会完结）
 * 使用GlobalScope启动的协程 不会保持线程的生命周期，他们就像守护线程一样
 */

fun main() {
    GlobalScope.launch {
        // 重复100次（输出I am delaying $repeatNumber 并等待400ms）
        repeat(100) { repeatNumber ->
            println("I am delaying $repeatNumber")
            delay(400)
        }
    }
    Thread.sleep(2000) // 主线程被sleep阻塞 一旦时间到达 GlobalScope创建的协程即使没有执行完毕 也会直接结束
    println("main thread down")
}

/**
输出
I am delaying 0
I am delaying 1
I am delaying 2
I am delaying 3
I am delaying 4
main thread down
 */

class HelloKotlin12 {
}