package com.example.lib.coroutine4

import kotlinx.coroutines.*

/**
 * 既想给协程构建器指定协程名称 又想指定协程分发器
 * 操作符重载
 * 注意加上-Dkotlinx.coroutines.debug
 */
private fun log(logMessage: String) = println("[${Thread.currentThread().name}] $logMessage")

/**
 * 追踪+号 我们发现运算符重载
 * Returns a context containing elements from this context and elements from  other [context].
 * The elements from this context with the same key as in the other one are dropped.
 * ”+“返回了一个一个上下文 该上下文包含了 当前context的元素以及其他context的元素
 * 如果当前context的元素和其他context的元素拥有相同的key 则会被抛弃
 */
fun main() = runBlocking<Unit> {
    launch(CoroutineName("myCoroutineName") + Dispatchers.Default) {
        log("myCoroutine log")
    }
}

/**
输出
[DefaultDispatcher-worker-1 @myCoroutineName#2] myCoroutine log
 */

class HelloKotlin9 {
}