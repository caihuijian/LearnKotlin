package com.example.lib.coroutine5

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

/**
 * onCompletion陷阱
 *
 * onCompletion只能看到来自Flow上游的异常（发生在onCompletion之前的中间操作符）
 * 而看不到下游的异常（发生在onCompletion之后的中间操作符及终端操作）
 */
private fun myMethod(): Flow<Int> = (1..10).asFlow()

fun main() = runBlocking {
    myMethod().onCompletion { cause -> println("Flow completed with $cause") }
        .collect { value ->
            // 这里异常检测在终端操作中
            // 虽然onCompletion会在collect之后执行 但是由于onCompletion是中间操作符 collect属于 onCompletion的下游
            // 它无法知道终端操作符的执行状态
            // 因此"Collected $value"无法传递给 中间操作onCompletion； 这会导致onCompletion的参数cause为空
            check(value <= 1) { "Collected $value" }
            println(value)
        }
}

/*
输出与视频中的输出不一样
我的输出
1
Flow completed with java.lang.IllegalStateException: Collected 2
Exception in thread "main" java.lang.IllegalStateException: Collected 2
	at com.example.lib.coroutine5.HelloKotlin25Kt$main$1$invokeSuspend$$inlined$collect$1.emit(Collect.kt:133)
	at kotlinx.coroutines.flow.FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$9.collect(SafeCollector.common.kt:115)
	at kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1.collect(SafeCollector.common.kt:114)
	at com.example.lib.coroutine5.HelloKotlin25Kt$main$1.invokeSuspend(HelloKotlin25.kt:38)
	at kotlin.coroutines.jvm.internal.BaseContinuationImpl.resumeWith(ContinuationImpl.kt:33)
	at kotlinx.coroutines.DispatchedTask.run(DispatchedTask.kt:56)
	at kotlinx.coroutines.EventLoopImplBase.processNextEvent(EventLoop.common.kt:274)
	at kotlinx.coroutines.BlockingCoroutine.joinBlocking(Builders.kt:84)
	at kotlinx.coroutines.BuildersKt__BuildersKt.runBlocking(Builders.kt:59)
	at kotlinx.coroutines.BuildersKt.runBlocking(Unknown Source)
	at kotlinx.coroutines.BuildersKt__BuildersKt.runBlocking$default(Builders.kt:38)
	at kotlinx.coroutines.BuildersKt.runBlocking$default(Unknown Source)
	at com.example.lib.coroutine5.HelloKotlin25Kt.main(HelloKotlin25.kt:27)
	at com.example.lib.coroutine5.HelloKotlin25Kt.main(HelloKotlin25.kt)

Process finished with exit code 1


视频中的输出不是
Flow completed with java.lang.IllegalStateException: Collected 2
而是
Flow completed with null
不清楚是不是Kotlin内部有调整
 */

class HelloKotlin25 {
}