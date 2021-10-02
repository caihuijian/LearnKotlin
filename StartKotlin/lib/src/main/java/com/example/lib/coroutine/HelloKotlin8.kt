package com.example.lib.coroutine

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 协程是轻量级的
 * 对比HelloKotlin8和HelloKotlin9可以看出为什么说协程是轻量级的（相比于线程）
 * 注意这里代码和视频略有不同 增加了计算时间的逻辑 然而这个只是大致统计时间 因为最后一次线程或者协程不一定
 * 是最后一个执行 不过仍然能够看到明显的效果
 *
 * 视频中线程创建超过1万个 JMV就会内存溢出 而协程创建超过一万个却没事
 * 在我的电脑上现象不一样 线程和协程无论创建多少个 都不会内存溢出
 * 但是当数目大于20000时 会发现协程的执行速度远大于线程 不过不同的电脑可能现象不一样
 *
 */

fun main() = runBlocking {
    val start = System.currentTimeMillis()
    val repeatTimes = 20000
    repeat(repeatTimes) {
        // 开启100个协程 协程等待1s后输出A
        launch {
            delay(100)
            println("A")
            if (it == repeatTimes - 1) {
                val totalTime = System.currentTimeMillis() - start
                println("coroutine $repeatTimes cost time $totalTime")
            }
        }
    }
    println("hello world")
}

class HelloKotlin8 {
}