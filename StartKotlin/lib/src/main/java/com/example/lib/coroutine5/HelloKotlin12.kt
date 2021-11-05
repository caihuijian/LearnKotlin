package com.example.lib.coroutine5

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

/**
 * Flow 默认是顺序执行的
 * 对于Flow的收集来说，它是运行在调用了终止操作符的那个协程上。默认情况下，它不会启动新的协程。
 * 每个emit的元素值都会由所有的中间操作进行处理，最后再由终止操作进行处理。本质上就是每一个元素
 * 依次执行 中间操作 终端操作，这样一个一个顺序执行
 */

fun main() = runBlocking<Unit> {
    (1..10).asFlow().filter {// 有的元素在第一步中就被过滤了
        println("第一步 filter: $it")
        it % 2 == 0
    }.map {
        println("第二步 map: $it")
        "Hello: $it"
    }.collect {
        println("第三步 final: $it")
        println("=============")
    }
}

class HelloKotlin12 {
}