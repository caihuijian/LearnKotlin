package com.example.lib.coroutine5

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

/**
 * Flow的终端操作符（Terminal Operation）
 * Flow的终止操作符都是挂起函数 终止操作才会真正开始流的收集
 *
 * 主要终端操作符
 * 1 除了collect 其他终端操作符 toList toSet
 * 2 只获取第一个元素
 * 3 reduce 将一系列值合成一个单个的值
 * Accumulates value starting with the first element and applying operation to current accumulator value and each element.
 * Throws NoSuchElementException if flow was empty.
 * 从第一个元素开始累加值，并将操作应用于当前累加器值和每个元素。
 * 如果流为空，则抛出 NoSuchElementException。
 */

fun main() = runBlocking {
    val result = (1..4).asFlow()
        .reduce { a, b -> a + b }
    println(result)

    val result1 = (1..4).asFlow()
        .toList()
    println(result1)

    val result2 = (1..4).asFlow()
        .toSet()
    println(result2)
}

class HelloKotlin11 {
}