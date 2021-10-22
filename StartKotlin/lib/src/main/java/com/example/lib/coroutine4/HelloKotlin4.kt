package com.example.lib.coroutine4

import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/**
 * 协程上下文切换
 *
 * 可以使用runBlocking等协程构建器 或者 withContext切换协程上下文
 */
// 打印当前线程名称的log函数
private fun log(logMessage: String) = println("[${Thread.currentThread().name}] $logMessage")

fun main() {
    // newSingleThreadContext是一个协程分发器
    // Context1 代表上下文的名称 使用use是为了可以自动关闭线程资源
    // use接受一个lambda表达式作为参数 block: (T) -> R
    // 示例中ctx1表示上下文参数
    newSingleThreadContext("Context1").use { ctx1 ->
        log("111")//主线程执行
        newSingleThreadContext("Context2").use { ctx2 ->
            log("222")//主线程执行
            runBlocking(ctx1) {//用runBlocking切换到Context1
                log("started in context1")// 切换到Context1线程的协程1执行这句话
                withContext(ctx2) {// 让代码块在ctx2上下文执行
                    log("working in context2")//切换到Context2线程的协程1执行这句话
                }
                log("back to context1")//再次回到Context1线程的协程1执行这句话
            }
        }
    }

}
/**
 * use函数的官方介绍
 * Executes the given [block] function on this resource and then closes it down correctly whether an exception
 * is thrown or not.
 * 在它自己的资源上执行给定block块 并且不管是否发生异常都会正确的关闭资源（不需要调用close方法）
 *
 * @param block a function to process this [Closeable] resource.
 * @return the result of [block] function invoked on this resource.
 */

/**
 * 输出
[main] 111
[main] 222
[Context1 @coroutine#1] started in context1
[Context2 @coroutine#1] working in context2
[Context1 @coroutine#1] back to context1

Context1的协程一号 执行started in context1
Context2的协程一号 执行working in context2
Context1的协程一号 执行back to context1
两个线程的协程名字一样 他们也不是同一个协程
 */
class HelloKotlin4 {
}