package com.example.lib.coroutine

import java.lang.Thread.sleep

fun main() {
    val start = System.currentTimeMillis()
    val repeatTimes = 20000
    repeat(repeatTimes) {
        // 开启100个线程 线程等待1s后输出A
        thread {
            sleep(100)
            println("A")
            if (it == repeatTimes - 1) {
                val totalTime = System.currentTimeMillis() - start
                println("thread $repeatTimes cost time $totalTime")
            }
        }
    }
    println("hello world")
}

class HelloKotlin9 {
}