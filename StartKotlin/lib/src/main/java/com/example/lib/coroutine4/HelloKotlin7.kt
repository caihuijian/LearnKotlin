package com.example.lib.coroutine4

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 父协程总是等待子协程执行完成
 * 对于父协程来说，父协程总是会等待所有子协程完成，而不必显示地追踪由它启动的子协程，子协程也不需要调用自身的Job.join方法来让父协程等待子协程完成
 */
fun main() = runBlocking {
    val job = launch {
        repeat(5) { i ->
            launch {
                delay(i * 200L)
                println("Coroutine $i 执行完毕")
            }
        }
        println("hello")
    }
    /**
     * join方法Suspends the coroutine until this job is complete
     * 这里调用这个方法主要目的是让”Coroutine x 执行完毕“在”end“之前输出
     * 事实上 这里注释调join方法 子协程仍然会能够执行结束
     */
    job.join()
    println("end")
}

class HelloKotlin7 {
}