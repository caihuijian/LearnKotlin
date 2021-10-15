package com.example.lib.coroutine2

import kotlinx.coroutines.*

/**
 * 已经取消的协程 再调用挂起方法（如delay）需要使用withContext代码块
 *
 * 当我们在协程的finally中调用挂起函数时 会导致出现CancellationException异常 原因在于运行着该
 * 代码块的协程已经被取消了。我们想要在取消的协程中进行挂起操作时，我们可以将代码放置到withContext(NonCancellable){}
 * 代码块中，在这种结构中 我们实际使用了withContext函数与NoCancellable的上下文
 */
fun main() = runBlocking {
    val myJob = launch {
        try {
            repeat(100) { i ->
                println("job: I am sleeping $i")
                delay(500)
            }
        } finally {
            //
            /**
             * withContext: Calls the specified suspending block with a given coroutine context, suspends until it completes, and returns the result
             *
             * NonCancellable
             * A non-cancelable job that is always active. It is designed for withContext function to prevent cancellation of code blocks that need to be executed without cancellation.
             * 防止代码块中的代码被取消
             * Use it like this:
             * withContext(NonCancellable) {
             * // this code will not be cancelled
             * }
             */
            withContext(NonCancellable) {// 注意与上一个demo对比差异
                println("execute finally")
                delay(1000)
                println("finally code after delay")
            }
        }
    }

    delay(1300)
    println("hello world")
    myJob.cancelAndJoin()
    println("end")
}

class HelloKotlin5_1 {
}