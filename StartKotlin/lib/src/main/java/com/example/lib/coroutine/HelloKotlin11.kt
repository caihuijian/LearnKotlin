package com.example.lib.coroutine

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 挂起函数
 * 被suspend修饰的函数称为挂起函数
 * 挂起函数只能使用在协程或其他的挂起函数中
 */
fun main() = runBlocking {
    launch {
        hello()
    }
    println("welcome")
}

suspend fun hello() {
    delay(400)
    println("hello")
    world()
}

suspend fun world() {
    println("world")
}

class HelloKotlin11 {

}