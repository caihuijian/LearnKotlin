package com.example.lib.coroutine4

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

/**
 * 协程调试
 * 在log中显示协程的名称
 * 在Run/Debug configuration中选择想要打印log的文件
 * 选择edit configuration
 * 在VM option上填入 -Dkotlinx.coroutines.debug
 * 这样可以将协程的名字 自动添加到线程后面
 */

// 打印当前线程名称的log函数
private fun log(logMessage: String) = println("[${Thread.currentThread().name}] $logMessage")

fun main() = runBlocking {
    val a = async {
        log("hello")
        10
    }

    val b = async {
        log("welcome")
        20
    }
    log("The result is ${a.await() + b.await()}")
}

/**
 * 输出如下
[main @coroutine#2] hello
[main @coroutine#3] welcome
[main @coroutine#1] The result is 30
分别由主线程的协程2 协程3和协程1执行上述语句
一个线程执行了3个协程
 */

class HelloKotlin3 {
}