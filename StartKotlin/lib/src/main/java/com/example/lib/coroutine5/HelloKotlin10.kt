package com.example.lib.coroutine5

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import java.lang.Exception

/**
 * Flow 的中间运算符 take
 * 它本质是使用Kotlin的异常来实现只取出限定数量的数据‘
 *
 * 限定处理发射数量的中间操作符 当超出限制数量 会抛出异常
 * Flow<T>.take方法可以限制指定数量的flow中的发射的元素
 *
 * Returns a flow that contains first count elements.
 * When count elements are consumed, the original flow is cancelled.
 * Throws IllegalArgumentException if count is not positive.
 * 返回包含第一个 count 元素的流。
 * 当 count 元素被消耗时，原始流被取消。
 * 如果 count 不是正数，则抛出 IllegalArgumentException。
 */
private fun myNumbers(): Flow<Int> = flow {
    try {
        emit(1)
        emit(2)
        println("middle")
        emit(3)
        emit(4)
    } catch (ex: Exception) {
        println(ex)
    } finally {
        println("finally")
    }
}

fun main() = runBlocking {
    myNumbers().take(2).collect {
        println(it)
    }
}
/*
输出
1
2
kotlinx.coroutines.flow.internal.AbortFlowException: Flow was aborted, no more elements needed
finally

Process finished with exit code 0
 */

class HelloKotlin10 {
}