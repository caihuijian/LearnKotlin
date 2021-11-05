package com.example.lib.coroutine5

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * Buffer 缓冲
 * 这里没有使用缓冲
 */

private fun myMethod(): Flow<Int> = flow {
    for (i in 1..4) {
        delay(100)
        emit(i)
    }
}

fun main() = runBlocking {
    val time = measureTimeMillis {
        myMethod().collect { value ->
            delay(200)
            println(value)
        }
    }
    println(time)
}
/*
很明显 这里运行至少要（100+200）*4ms
 */

class HelloKotlin16 {
}