package com.example.lib.coroutine

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * runBlocking vs coroutineScope
 * 通过coroutineScope builder来声明自己的协程作用域
 */

/**
 * 除去不同的协程构建器(如runBlocking launch)所提供的协程作用域(coroutine scope)外，我们还可以通过coroutineScope builder来声明自己的协程作用域.
 * 该构建器会创建一个协程作用域，并且会等待所有启动的子协程全部完成后自身才会完成。
 *
 * runBlocking与coroutineScope的主要区别是 coroutineScope在等待所有子协程完成Job时不会阻塞当前的线程。
 *
 * 1.runBlocking并非挂起函数；也就是说，调用它的线程会一直位于该函数中，直到协程执行完毕
 * 2.coroutineScope是挂起函数；也就是说，如果其中的协程挂起。那么coroutineScope函数也会挂起。这样，创建coroutineScope的外层
 * 函数就可以继续在同一个线程中执行了，该线程会“逃离”coroutineScope之外，并且可以做其他事情
 */
fun main() = runBlocking {
    launch {
        delay(1000) // 不阻塞线程 计时1s
        println("my job1")
    }
    println("person")// 第一个输出

    // 通过coroutineScope builder来声明自己的协程作用域
    // 只有所有的子协程完成 它自己才会退出 有点类似join（coroutineScope 也是一个挂起函数）
    coroutineScope {
        launch {// 创建新的子协程 计时3s
            delay(3000)
            println("my job2")
        }
        delay(2000)// 计时2s
        println("hello world") // 第二个输出
    }
    println("welcome")// 最后一个输出
    // 其余按照delay时间 间隔1s依次输出
}

/**
 * 输出
 * person
 * my job1
 * hello world
 * my job2
 * welcome
 *
 * person立即输出
 * my job1 间隔1s后输出
 * hello world 间隔1s后输出
 * my job2 间隔1s后输出
 * welcome和my job2同时输出
 */

class HelloKotlin7_2 {
}