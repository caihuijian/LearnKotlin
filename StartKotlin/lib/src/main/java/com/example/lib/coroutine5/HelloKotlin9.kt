package com.example.lib.coroutine5

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking

/**
 * Flow 的中间运算符 transform
 * transform内部可以随意处理每一个Flow元素 理论上 transform可以实现其他任意的中间操作符或者他们的组合
 *
 * Applies transform function to each value of the given flow.
 * The receiver of the transform is FlowCollector and thus transform is a flexible function that may transform emitted element, skip it or emit it multiple times.
 * This operator generalizes filter and map operators and can be used as a building block for other operators, for example:
 *
 * 将变换函数应用于给定Flow的每个值。
 * 变换的接收者是 FlowCollector，因此变换是一个灵活的函数，可以变换发射的元素，跳过它或多次发射它。
 * 此运算符概括了过滤器和映射运算符，可用作其他运算符的构建块
 */
private suspend fun myExecution(input: Int): String {
    delay(500)
    return "output: $input"
}

fun main() = runBlocking {
    (1..10).asFlow().transform { input ->
        if (input > 5) {
            // transform可以让flow多次发射值 collect都能收集到
            emit("my input: $input")
            emit(myExecution(input))
            emit("hello")
        }
        // input为输入  emit的内容为输出
    }.collect { println(it) }
}

/*
输出
my input: 6
output: 6
hello
my input: 7
output: 7
hello
my input: 8
output: 8
hello
my input: 9
output: 9
hello
my input: 10
output: 10
hello

Process finished with exit code 0

 */
class HelloKotlin9 {
}