package com.example.lib.coroutine2

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

/**
 * 协程的超时设置withTimeoutOrNull
 * 由withTimeout函数所抛出的TimeoutCancellationException是CancellationException的子类，当CancellationException异常
 * 抛出时，我们并未在控制台上看到异常信息，因为在取消的协程中，CancellationException被认为是一种协程完成的正常原因
 * 既然CancellationException仅仅是一个异常而已 所有的资源都会以通常的方式来关闭，那么我们可以将相关的代码放到一个try...catch块中；
 * try {
 *     withTimeout(xxx) {
 *        ...
 *     }
 * }
 * catch (ex: TimeoutCancellationException) {
 * }
 *
 * 此外，相对于withTimeout Kotlin还提供了一个更便利的方法withTimeoutNotNull
 * 从功能角度上看 他非常类似withTimeout，不过当超时发生时，它不会抛出CancellationException异常 而是直接返回null
 *
 */
fun main() = runBlocking {
    val res = withTimeoutOrNull(1900) {
        repeat(4) {// 修改为4 就不会抛出TimeoutCancellationException
                i ->
            println("hello $i")
            delay(400)
        }
        "haha" // 协程没有超时 则res会被赋值为“haha” 否则 res等于null
    }

    println("result is $res")
}

class HelloKotlin7 {
}