package com.example.lib.coroutine5

/**
 * 一个方法返回多个结果
 * 方式2 返回一个序列(sequence)
 * 如果在获取每一个结果时都需要执行一定的计算，这种计算是一种阻塞行为，每计算出一个结果就立即返回给调用端
 * 特点
 * 1 序列中的数据并不像集合那样一次性返回，而是计算完一个数据后就返回
 * 2 序列中的计算过程会占用主线程执行，因此会阻塞主线程
 */
private fun myMethod(): Sequence<Int> = sequence {
    for (i in 100..105) {
        Thread.sleep(100)
        yield(i)
    }
}

fun main() {
    myMethod().filter { it % 2 == 0 }.map { it -> "result: $it" }.forEach { println(it) }
}

class HelloKotlin2 {
}