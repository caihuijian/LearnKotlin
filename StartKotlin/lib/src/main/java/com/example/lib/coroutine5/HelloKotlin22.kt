package com.example.lib.coroutine5

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking

/**
 * Flow的完成
 * 用于在Flow完成时再做一些处理
 *
 * Flow的完成有两种方式
 * 1 命令式
 * 2 声明式
 *
 * 这一节说命令式
 * 命令式其实也没有什么好说的 就是在Flow终端操作外套一个try finally
 */

private fun myMethod(): Flow<Int> = (1..10).asFlow()

fun main() = runBlocking {
    try {
        myMethod().collect { println(it) }
    } finally {
        println("finally")
    }
}

class HelloKotlin22 {
}