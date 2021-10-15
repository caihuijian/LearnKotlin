package com.example.lib.coroutine2

import kotlinx.coroutines.*

/**
 * 协程取消的注意点
 * 如果协程正处于某个计算过程中 如何正确取消协程
 *
 * 有两种方式可以让协程能够被成功取消
 * 1 周期性调用一个挂起函数 该挂起函数会检查取消状态 例如挂起函数delay
 * 2 显示地检查取消状态
 *
 * 下面地示例采用第二种方式取消协程
 */

fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {// 取得启动的协程job
        var nextTime = startTime
        var i = 0;
        /**
         *   Returns true when the current Job is still active (has not completed and was not cancelled yet).
         *   Check this property in long-running computation loops to support cancellation:
         *   while (isActive) {
         *   // do some computation
         *   }
         * isActive是CoroutineScope地一个扩展属性
         * 它当协程仍然处于活跃状态（还没有完成且没有取消）返回true
         * 在长期计算循环中检查该属性可以让协程能够被取消
         */
        while (isActive) {// 与HelloKotlin2唯一不同地方
            if (System.currentTimeMillis() > nextTime) {// 每500毫秒 i++ 直到i>=20结束
                println("job:I am sleeping ${i++}")
                nextTime += 500
            }
        }
        println("end while")
    }
    delay(1300)
    println("hello world")

    job.cancelAndJoin() // 取消协程 但是协程处于计算中 但是它检查取消状态 因此协程立即被取消
    println("welcome")
}

/**
输出：
job:I am sleeping 0
job:I am sleeping 1
job:I am sleeping 2
hello world
end while
welcome
 */

class HelloKotlin3 {
}