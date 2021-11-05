package com.example.lib.coroutine5

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking

/**
 * flowOn 切换Flow上下文
 *
 * 借助flowOn，可以让Flow在发射元素所在的上下文与收集（终端操作符）所处的上下文是不同的。
 * 值得注意的是：flowOn运算符改变了Flow的顺序性。
 * 现在 收集操作发生在一个协程上，而发射操作发射在另外的协程
 * flowOn运算符本质上会改变上下文中的CoroutineDispatcher,并且为上游的flow创建另外一个协程
 * 上游可以简单理解为发生在当前操作的前面 不过 终端操作即使发生在中间操作之前，也不能称之为上游
 *
 * 注意加上-Dkotlinx.coroutines.debug
 */

private fun log(logMessage: String) = println("[${Thread.currentThread().name}] $logMessage")

private fun myMethod(): Flow<Int> = flow {
    for (i in 1..4) {
        Thread.sleep(100)
        log("emit: $i")
        emit(i)
    }
}.flowOn(Dispatchers.Default)// 对比14 的差异 在这里切换emit的上下文

fun main() = runBlocking {
    myMethod().collect { value ->
        log("Collected $value")
    }
}
/*
输出
[DefaultDispatcher-worker-1 @coroutine#2] emit: 1
[main @coroutine#1] Collected 1
[DefaultDispatcher-worker-1 @coroutine#2] emit: 2
[main @coroutine#1] Collected 2
[DefaultDispatcher-worker-1 @coroutine#2] emit: 3
[main @coroutine#1] Collected 3
[DefaultDispatcher-worker-1 @coroutine#2] emit: 4
[main @coroutine#1] Collected 4

可以看到Flow的发射与收集工作在不同的协程
 */

class HelloKotlin15 {

}