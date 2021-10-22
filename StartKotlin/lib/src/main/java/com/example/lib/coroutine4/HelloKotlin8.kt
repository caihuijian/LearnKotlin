package com.example.lib.coroutine4

import kotlinx.coroutines.*

/**
 * 给协程取名字
 *
 * CoroutineName上下文元素可以让我们对协程取名字，以便能够输出可读性较好的日志
 *
 * 注意加上-Dkotlinx.coroutines.debug
 */
private fun log(logMessage: String) = println("[${Thread.currentThread().name}] $logMessage")

/**
 * CoroutineName看其定义：public data class CoroutineName
 * CoroutineName是一个数据类 继承了AbstractCoroutineContextElement AbstractCoroutineContextElement又实现了接口 Element
 * 接口Element则实现了CoroutineContext接口
 * 因此CoroutineName也是一个CoroutineCoroutineContext
 *
 * CoroutineName可以用来给用户指定协程的名称 该名称可以再debug模式使用
 * User-specified name of coroutine. This name is used in debugging mode.
 * See [newCoroutineContext][CoroutineScope.newCoroutineContext] for the description of coroutine debugging facilities.
 */
fun main() = runBlocking(CoroutineName("myCoroutineName1")) {
    log("hello")
    val value1 = async(CoroutineName("myCoroutineName2")) {
        delay(800)
        log("myCoroutine2 log")
        30
    }

    val value2 = async(CoroutineName("myCoroutineName3")) {
        delay(1000)
        log("myCoroutine3 log")
        5
    }
    log("result is ${value1.await() + value2.await()}")// 可以通过在一个deferred上调用await方法来获取最终计算的结果值
}

/**
输出
[main @myCoroutineName1#1] hello
[main @myCoroutineName2#2] myCoroutine2 log
[main @myCoroutineName3#3] myCoroutine3 log
[main @myCoroutineName1#1] result is 35
 */

class HelloKotlin8 {
}