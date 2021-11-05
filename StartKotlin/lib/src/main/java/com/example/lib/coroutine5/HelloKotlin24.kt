package com.example.lib.coroutine5

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import java.lang.RuntimeException

/**
 * Flow的完成 onCompletion的优势
 *
 * onCompletion的优势在于他有一个可空的Throwable参数 通过判断该参数 可以知道
 * Flow的完成是正常结束还是抛出异常导致结束
 */
private fun myMethod(): Flow<Int> = flow {
    emit(1)
    throw RuntimeException()//注意对比这句话注释之后的输出
}

fun main() = runBlocking {
    myMethod().onCompletion { cause ->
        if (cause != null) {
            println("Flow completed exceptionally")
        }
    }
        .catch { cause -> println("catch exception") }
        .collect { println(it) }
}

class HelloKotlin24 {
}