package com.example.lib.coroutine5

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 * 一个方法返回多个结果
 * 方式3 返回一个集合（结合协程）
 * 特点
 * 1 不会阻塞主线程
 * 2 集合本身是一次性返回给调用者的
 */
private suspend fun myMethod(): List<String> {
    delay(100)
    return listOf("hello", "world", "welcome")
}

fun main() = runBlocking {
    myMethod().forEach { println(it) }
}

class HelloKotlin3 {
}