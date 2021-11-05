package com.example.lib.coroutine5

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

/**
 * Flow的执行
 *
 * Flow 只有执行了终端操作符之后(例如collect) flow才会真正执行
 * 例如下面 我们调用myMethod方法后 myMethod立即返回 但是其中的代码不会运行
 */
private fun myMethod(): Flow<Int> = flow {
    println("myMethod execute")
    for (i in 1..4) {
        delay(100)
        emit(i)
    }
}

fun main() = runBlocking {
    println("enter")
    val flow = myMethod()//只调用这个方法 不会执行myMethod内部的内容
    println("1111")
    flow.collect {
        println(it)
    }
    println("2222")
}

class HelloKotlin5 {
}