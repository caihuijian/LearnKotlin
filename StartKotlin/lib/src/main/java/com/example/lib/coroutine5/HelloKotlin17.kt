package com.example.lib.coroutine5

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * Buffer 缓冲 注意对比Kotlin16
 *
 * buffer的主要作用是对发射的缓冲 减少发射部分的等待时间
 *
 * buffer与flowOn之间有一定的关系：
 * 实际上，flowOn运算符本质上在遇到需要改变CoroutineDispatcher时 同样也会使用缓存机制
 * 所以有时候flowOn可以理解为复杂版的buffer
 */

private fun myMethod(): Flow<Int> = flow {
    for (i in 1..4) {
        delay(100)
        emit(i)
    }
}

fun main() = runBlocking {
    val time = measureTimeMillis {
        myMethod().buffer()/*注意这里添加了缓冲*/.collect { value ->
            delay(200)
            println(value)
        }
    }
    println(time)
}
/*
上一个示例运行至少要（100+200）*4ms=1200ms左右
这里可以节省3次发射需要的100 一共节省300ms左右
 */

class HelloKotlin17 {
}