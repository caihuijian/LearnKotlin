package com.example.lib.coroutine5

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

/**
 * Flatten Flow
 *
 * 将Flow<Flow<Int>> ->Flow<Int>
 */
private fun myMethod(i: Int): Flow<String> = flow {
    emit("first: $i")
    delay(100)
    emit("second: $i")
}

fun main() = runBlocking {
    // 如下写法会生成 Flow<Flow<String>>
    val startTime1 = System.currentTimeMillis()
    (1..3).asFlow().onEach { delay(100) }
        .map { myMethod(it) }
        .collect { value ->
            // value实际还是一个Flow
            println("$value at ${System.currentTimeMillis() - startTime1} ms")
        }
    println("============")

    (1..3).asFlow().onEach { delay(100) }
        .map { myMethod(it) }
        .collect { value ->
            // value实际还是一个Flow
            //println("${value.collect { println(it) }} at ${System.currentTimeMillis() - startTime1} ms")
            value.collect { println("$it at ${System.currentTimeMillis() - startTime1} ms") }
        }

    println("============")
    val startTime2 = System.currentTimeMillis()
    (1..3).asFlow().onEach { delay(100) }
        .flatMapConcat { myMethod(it) }// 打平Flow
        .collect { value ->
            println("$value at ${System.currentTimeMillis() - startTime2} ms")
        }
    println("============")
}

/*
输出
kotlinx.coroutines.flow.SafeFlow@64a294a6 at 131 ms
kotlinx.coroutines.flow.SafeFlow@7e0b37bc at 236 ms
kotlinx.coroutines.flow.SafeFlow@3b95a09c at 346 ms
============
first: 1 at 464 ms
second: 1 at 567 ms
first: 2 at 677 ms
second: 2 at 787 ms
first: 3 at 897 ms
second: 3 at 1005 ms
============
first: 1 at 109 ms
second: 1 at 218 ms
first: 2 at 327 ms
second: 2 at 435 ms
first: 3 at 547 ms
second: 3 at 654 ms
============

Process finished with exit code 0


 */

class HelloKotlin19 {
}