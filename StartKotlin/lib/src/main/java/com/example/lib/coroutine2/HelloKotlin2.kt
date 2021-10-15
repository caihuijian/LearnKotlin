package com.example.lib.coroutine2

import kotlinx.coroutines.*

/**
 * 协程取消的注意点
 * 如果协程正处于某个计算过程中且没有检查取消状态，那么它就是无法被取消的
 *
 * kotlinx.coroutines包下所有的挂起函数都可以被取消
 * 当调用取消方法时 他们会检查取消状态，当取消时抛出CancellationException异常
 * 不过 如果协程正处于某个计算过程中且没有检查取消状态，那么它就是无法被取消的（可以理解为取消失败）
 */

fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {// 取得启动的协程job
        var nextTime = startTime
        var i = 0;
        while (i < 20) {// 在时间没有到达指定时间 该while循环一直在空轮转（while循环满足 而 if条件不满足）
            if (System.currentTimeMillis() > nextTime) {// 每500毫秒 i++ 直到i>=20结束
                println("job:I am sleeping ${i++}")
                nextTime += 500
            }
        }
        println("end while")
    }
    delay(1300)
    println("hello world")

    job.cancelAndJoin() // 取消协程 但是协程处于计算中 且协程没有检查取消状态 因此协程没有被取消 一直等待 直到协程执行完毕
    println("welcome")
}

/**
输出如下
job:I am sleeping 0
job:I am sleeping 1
job:I am sleeping 2
hello world
welcome
job:I am sleeping 3
job:I am sleeping 4
job:I am sleeping 5
job:I am sleeping 6
job:I am sleeping 7
job:I am sleeping 8
job:I am sleeping 9
job:I am sleeping 10
job:I am sleeping 11
job:I am sleeping 12
job:I am sleeping 13
job:I am sleeping 14
job:I am sleeping 15
job:I am sleeping 16
job:I am sleeping 17
job:I am sleeping 18
job:I am sleeping 19
end while
 */

class HelloKotlin2 {
}