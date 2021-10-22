package com.example.lib.coroutine4

/**
 * asContextElement示例
 */

import kotlinx.coroutines.*
import java.util.concurrent.Executors

private fun log(logMessage: String?) = println("[${Thread.currentThread().name}] $logMessage")

fun main() = runBlocking {
    val myThreadLocal = ThreadLocal<String?>()
    val threadDispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
    // 下面的这句运行在主线程的协程1 myThreadLocal没有初始化 得到null
    log("0" + myThreadLocal.get()) // Prints "null"
    launch(Dispatchers.Default + myThreadLocal.asContextElement(value = "foo")) {
        // 下面的这句切换线程 运行在DefaultDispatcher-worker-2线程 myThreadLocal刚初始化为foo 得到foo
        log("1" + myThreadLocal.get()) // Prints "foo"
        withContext(threadDispatcher) {
            // 下面的这句再次切换线程池 运行在pool-1-thread-1 @coroutine#2 myThreadLocal的值延用外层的值 得到foo
            log("2" + myThreadLocal.get()) // Prints "foo", but it's on other thread
        }
    }
    // 下面的这句运行在主线程的协程1 主线程的myThreadLocal仍然没有初始化 得到null
    log("3" + myThreadLocal.get()) // Prints "null"


    //The context element does not track modifications of the thread-local variable, for example:
    //上下文元素不会追踪thread-local值的修改


    myThreadLocal.set("main")//修改主线程myThreadLocal的值

    // 下面的这句再次切换线程池 运行在pool-1-thread-1 @coroutine#1
    withContext(threadDispatcher) {
        log("4" + myThreadLocal.get()) // Prints "main" // 与官网说的不一样 这里输出null？？？
        myThreadLocal.set("UI")
    }
    // 下面的这句运行在主线程的协程1 主线程的myThreadLocal被初始化为main 得到main 与其他线程的值修改无关
    log("5" + myThreadLocal.get()) // Prints "main", not "UI"


    //Use `withContext` to update the corresponding thread-local variable to a different value, for example:
    // 使用withContext更新对应线程的thread-local变量为不同的值

    withContext(myThreadLocal.asContextElement(value = "foo")) {// 将主线程中的thread-local的值修改为foo
        // 下面的这句运行在主线程的协程1 得到值为foo
        log("6" + myThreadLocal.get()) // Prints "foo"
    }


    //Accessing the thread-local without corresponding context element leads to undefined value:
    // 访问不对应上下文元素的thread-local 会导致得到一个不确定的值 这里还有问题

    val tl = ThreadLocal.withInitial { "initial" }// 初始化ThreadLocal的初始值为initial

    runBlocking {
        // 下面的这句运行在主线程的协程3 "main @coroutine#3" 得到值为initial
        log("7" + tl.get()) // Will print "initial"
        // Change context
        withContext(tl.asContextElement("modified")) {// 使用withContext更新”main @coroutine#3“的ThreadLocal值为modified
            // // 下面的这句运行在主线程的协程3 "main @coroutine#3" 得到值为modified
            log("8" + tl.get()) // Will print "modified"
        }
        // Context is changed again

        log("9" + tl.get()) // <- WARN: can print either "modified" or "initial" // 这里我的输出一直都是initial？？？

    }
    threadDispatcher.close()
}

/*
我的输出
[main @coroutine#1] 0null
[main @coroutine#1] 3null
[DefaultDispatcher-worker-1 @coroutine#2] 1foo
[pool-1-thread-1 @coroutine#2] 2foo
[pool-1-thread-1 @coroutine#1] 4null
[main @coroutine#1] 5main
[main @coroutine#1] 6foo
[main @coroutine#3] 7initial
[main @coroutine#3] 8modified
[main @coroutine#3] 9initial

Process finished with exit code 0
 */

class HelloKotlin11_2 {
}