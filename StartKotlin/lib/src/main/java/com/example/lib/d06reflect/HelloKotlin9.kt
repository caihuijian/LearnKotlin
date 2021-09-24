package com.example.lib.d06reflect

/**
 * 使用构造方法的引用(Constructor Reference)
 *
 * 要使用构造方法的引用 有如下要求
 * 1 函数对象的参数与构造方法的参数一致（包括参数个数 顺序 类型）
 * 2 函数返回对象类型与构造方法创建类型一致
 */

class B(val x: Int)

fun myMethod(factory: (x: Int) -> B) {
    val b: B = factory(3)
    println(b.x)
}

fun main() {
    // 使用构造方法的引用
    myMethod(::B)
}

class HelloKotlin9 {
}