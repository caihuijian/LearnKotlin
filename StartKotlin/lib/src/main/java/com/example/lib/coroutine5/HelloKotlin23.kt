package com.example.lib.coroutine5

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.runBlocking

/**
 * Flow的完成
 * 第二种 声明式
 * Flow提供了一个名为onCompletion的中间操作
 * onCompletion会在Flow完成或抛出异常时调用
 *
 * 注意该方法虽然是中间操作 但是它的运行实际时间却是在collect这个终端操作之后（除了onCompletion 其他中间操作都是发生再终端操作之前的）
 */

private fun myMethod(): Flow<Int> = (1..10).asFlow()

fun main() = runBlocking {
    myMethod().onCompletion { println("onCompletion") }.collect { println(it) }
}
/*
输出
1
2
3
4
5
6
7
8
9
10
onCompletion

Process finished with exit code 0

 */

class HelloKotlin23 {
}