package com.example.lib.coroutine5

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

/**
 * Flow builder(流构建器)
 * 1 flow构建器 是经常被使用的流构建器
 * 2 flowOf构建器 可以用于定义能够发射固定数量值的流
 * 3 asFlow构建器 对于各种集合与序列来说，他们都提供了asFlow（）扩展方法来将自身转换为Flow
 *
 *
 *
 * 非局部返回
 * inline,noinline,crossinline
 * non-local reutrns:非局部返回 实际上表示的是在一个方法内部，我们可以在其中通过一个lambda表达式的返回来直接将外层方法返回返回
 * crossinline的作用实际上表示被标记的lambda是禁止非局部返回？
 */

fun main() = runBlocking {
    // asFlow构建器可以将各种集合转为Flow 例如 LongRange IntRange IntArray Array<T> Sequence<T>
    (1..10).asFlow().collect {
        println(it)
    }

    println("-------")

    // flowOf构建器可以用于定义能够发射固定数量值 flowOf的参数是一个可变参数
    flowOf(10, 20, 3, 4, 50).collect {
        println(it)
    }
}

class HelloKotlin7 {
}