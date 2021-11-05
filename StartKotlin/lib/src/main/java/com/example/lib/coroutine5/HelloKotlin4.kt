package com.example.lib.coroutine5

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 一个方法返回多个结果
 * 方式4 返回一个Flow
 *
 * 如果返回一个List<String> 那么代表只能一次性返回所有的值。要想能够像sequence一样一次返回一个值
 * 并且还不会阻塞主线程，可以使用Flow<String>类型
 *
 * 特点
 * 1 Flow的创建使用flow构建器
 * 2 位于flow构建器中的代码是可以挂起的 而不需要额外的suspend关键字
 * 3 使用emit函数发射函数
 * 4 Flow里面的值通过collect来收集
 *
 * flow简单介绍
 * flow是一种冷异步数据流，它按顺序发出值，正常或异常完成
 *
 * flow的构建器有如下几种
 * flowOf(...) functions to create a flow from a fixed set of values.
 * asFlow() extension functions on various types to convert them into flows.
 * flow { ... } builder function to construct arbitrary flows from sequential calls to emit function.
 * channelFlow { ... } builder function to construct arbitrary flows from potentially concurrent calls to the send function.
 *
 * flow有中间操作符 如map、filter、take、zip 等
 * 中间操作不会执行流程中的任何代码，也不会挂起函数本身
 * flow还有终端操作符 flow上的终端操作符要么是暂停函数，如 collect、single、reduce、toList 等，
 * 要么是启动给定范围内流的收集的 launchIn 操作符
 *
 */

private fun myMethod(): Flow<Int> = flow {
    for (i in 1..4) {
        delay(100)
        //Thread.sleep(100) //sleep1
        emit(i)
    }
}

fun main() = runBlocking<Unit> {
    launch {
        for (i in 1..4) {
            println("hello in launch $i")
            delay(200)
            //Thread.sleep(100) //sleep2
            // sleep方法不管加在那里 都会导致并行运行失效 因为Thread.sleep(100)需要睡眠主线程
            // 因此加了sleep的方法就类似变成了非挂起函数
        }
    }
    myMethod().collect {
        println(it)
    }
    // 这里launch里面的输出和myMethod().collect并行运行
}

class HelloKotlin4 {
}