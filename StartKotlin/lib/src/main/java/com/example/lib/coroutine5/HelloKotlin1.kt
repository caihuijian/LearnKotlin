package com.example.lib.coroutine5

/**
 * 一个方法返回多个结果
 * 方式1 返回一个集合
 * 特点
 * 1 方法本身是阻塞的，即主线程会进入该方法内部执行，一直执行到方法结束
 * 2 集合本身是一次性返回给调用者的，即集合中的全部元素均已经获得之后才统一返回给调用端
 */
private fun myMethod(): List<String> = listOf("hello", "world", "welcome")

fun main() {
    myMethod().forEach { println(it) }
}

class HelloKotlin1 {
}