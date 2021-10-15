package com.example.lib.coroutine2

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 如果已经取消的协程 再调用挂起方法（如delay）不能成功调用
 *
 * 对于下面的示例 当我们在协程的finally中调用挂起函数时 会导致出现CancellationException异常 原因在于运行着该
 * 代码块的协程已经被取消了。通常情况下，这没有什么问题，因为大多数关闭动作（如取消一个job 关闭连接）都是瞬时的（非阻塞的）
 * 并不需要使用挂起函数；然而，在极少数情况下，我们想要在取消的协程中进行挂起操作时，我们可以将代码放置到withContext(NonCancellable){}
 * 代码块中，在这种结构中 我们实际使用了withContext函数与NoCancellable的上下文 我们在下一节演示withContext
 *
 * 以下的示例中在主线程中延时1300毫秒
 * 同时协程在不断的等待输出
 * 当主线程延时结束 取消协程 finally中的delay执行失败 之后的语句不再执行
 */
fun main() = runBlocking {
    val myJob = launch {
        try {
            repeat(100) { i ->
                println("job: I am sleeping $i")
                delay(500)
            }
        } finally {
            println("execute finally")
            delay(1000)// 因为走到这里时协程已经取消 会抛出CancellationException异常 因此后面的代码都不执行了
            println("finally code after delay")
        }
    }

    delay(1300)
    println("hello world")
    myJob.cancelAndJoin()
    println("end")
}

class HelloKotlin5 {
}