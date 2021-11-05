package com.example.lib.coroutine5

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

/**
 * 流异常
 * 在中间操作时发生异常
 *
 */
private fun myMethod(): Flow<String> =
    flow {
        for (i in 1..3) {
            println("emitting $i")
            emit(i)
        }
    }.map { value ->
        // 在中间操作发生异常
        check(value <= 1) { "crash on $value" }
        "string $value"
    }

fun main() = runBlocking {
    try {
        myMethod().collect {
            println(it)
        }
    } catch (e: Throwable) {
        println("caught $e")
    }
}

/*
输出：
emitting 1
string 1
emitting 2
caught java.lang.IllegalStateException: crash on 2
 */
class HelloKotlin21 {
}