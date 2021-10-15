package com.example.lib.coroutine2

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 使用finally来关闭资源
 * join 与 cancelAndJoin都会等待所有的清理动作完成之后才会继续往下执行
 */

fun main() = runBlocking {
    val myJob = launch {
        try {
            repeat(100) { i ->
                println("job sleeping $i")
                delay(500)
            }
        } finally {
            println("finally执行") // cancelAndJoin会等待finally执行完毕
        }
    }

    delay(1300)
    println("hello world")
    myJob.cancelAndJoin()
    println("end")
}

class HelloKotlin4 {
}