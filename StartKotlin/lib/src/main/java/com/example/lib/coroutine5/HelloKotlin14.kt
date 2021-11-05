package com.example.lib.coroutine5

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/**
 * 尝试切换Flow上下文
 */
private fun myMethod(): Flow<Int> = flow {
    // 尝试将Flow的发射上下文更改
    withContext(Dispatchers.Default) {
        for (i in 1..4) {
            emit(i)
        }
    }
}

fun main() = runBlocking {
    myMethod().collect {
        println(it)
    }
}

/*
输出

Exception in thread "main" java.lang.IllegalStateException: Flow invariant is violated:
		Flow was collected in [BlockingCoroutine{Active}@76aa9e23, BlockingEventLoop@5fcb12cd],
		but emission happened in [DispatchedCoroutine{Active}@1f1d9c3, Dispatchers.Default].
		Please refer to 'flow' documentation or use 'flowOn' instead
报错原因 违反了Flow的不变性
Flow收集发生在[BlockingCoroutine{Active}@76aa9e23, BlockingEventLoop@5fcb12cd]
Flow发射发生在[DispatchedCoroutine{Active}@1f1d9c3, Dispatchers.Default]
要实现这样的需求需要使用flowOn代替
 */
class HelloKotlin14 {
}