package com.example.lib.coroutine

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * delay
 */
fun main() = runBlocking {// 开始执行主协程
    GlobalScope.launch {// 在后台用新的协程构建器 启动一个新的协程并继续
        delay(1000)
        println("Kotlin coroutine")
    }
    println("hello")// 主协程在这里会立即执行
    delay(2000)// 延迟 2 秒来保证 JVM 存活
    println("world")// 最后输出
}

/**
 * 输出
 * hello
 * Kotlin coroutine
 * world
 */

class HelloKotlin4 {
}