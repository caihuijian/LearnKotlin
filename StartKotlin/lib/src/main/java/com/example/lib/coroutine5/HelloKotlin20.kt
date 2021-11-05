package com.example.lib.coroutine5

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

/**
 * Flow异常处理
 *
 * try catch可以捕获Flow整个生命周期任意阶段的异常
 * flow有3个阶段
 *
 * 本例子是流的终端操作阶段发生异常
 *
 * 1 流元素生成阶段 （例如下面的flow构建器的代码块中）
 * 2 流的中间操作阶段
 * 3 流的终端操作阶段
 */
private fun myMethod(): Flow<Int> = flow {
    for (i in 1..3) {
        println("emitting $i")
        emit(i)
    }
}

fun main() = runBlocking {
    try {
        myMethod().collect { value ->
            println(value)
            // 在发射2时发生异常 被捕获并停止之后的发射
            // check函数判断条件不成立会抛出IllegalStateException 并将其内容也会作为异常的参数
            // 流在终端操作 collect时发生异常
            check(value <= 1) {
                "Collected $value"
            }
        }
    } catch (e: Throwable) {
        println("caught $e")
    }
}

/*
输出
emitting 1
1
emitting 2
2
caught java.lang.IllegalStateException: Collected 2
 */
class HelloKotlin20 {
}