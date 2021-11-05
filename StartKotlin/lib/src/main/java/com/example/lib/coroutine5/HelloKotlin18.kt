package com.example.lib.coroutine5

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

/**
 * flow的中间操作符zip：
 * Flow的组合
 * 将两个Flow合并为一个Flow 如果两个flow的长度不一样 会取较短的那个
 */
fun main() = runBlocking {
    val nums: Flow<Int> = (1..5).asFlow()
    val strs: Flow<String> = flowOf("one", "tow", "three", "four", "five")
    val flow2: Flow<String> = nums.zip(strs) { a, b -> "$a -> $b" }
    flow2.collect { println(it) }
}

class HelloKotlin18 {
}