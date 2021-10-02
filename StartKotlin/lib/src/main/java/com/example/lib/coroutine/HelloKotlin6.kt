package com.example.lib.coroutine

import kotlinx.coroutines.*

/**
 * 协程构建器内部隐藏的CoroutineScope实例
 * 外部协程依赖由外部协程创建的内部协程(不是其他协程构建器创建的内部协程)完成
 */

/**
 * 每一个协程构建器(包括runBlocking)都会向其代码块作用域内部添加一个CoroutineScope实例。我们可以在该作用域中启动协程，而无需
 * 显式地将其join到一起，这是因为外部协程（在下面的例子中就是runBlocking创建的协程）会等待该作用域中所有**由外部协程启动的子协程**全部完成后才会完成
 */
fun main() = runBlocking {
    // （每一个协程构建器都会向其代码块作用域内部添加一个CoroutineScope实例）
    // 从runBlocking的外部协程 创建新的子协程 这种情况下 外部协程依赖内部协程执行结束
    /*GlobalScope.*/launch {//注意对比加不加GlobalScope.的情况
    delay(1000)
    println("Kotlin Coroutines")
}
    println("hello")
}

/**
 * 输出
 * hello
 * Kotlin Coroutines
 */

class HelloKotlin6 {

}