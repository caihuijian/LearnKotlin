package com.example.lib.coroutine

/**
 * lambda 表达式深入
 * 当函数参数是函数时 并且该函数只有一个参数 可以不传入任何参数
 * 之前讲lambda表达式时提到 当函数只有一个参数时 在调用该函数时 我们可以用it代替这个唯一参数
 * 而这个it又是可以省略的
 * 如果函数体内部没有使用it 就像是it这个参数不存在一样
 *
 */

fun main() {
    // test参数中有一个是函数 该函数不接受参数
    test(5, action = {
        println("hello")
    })

    // 当函数参数是函数时 并且该函数只有一个参数 完整格式如下
    // 在调用该函数时 我们可以用it代替这个唯一参数
    // 然后it还是是可以省略的
    test2(x = 1, action = { it ->
        println("hello $it")
    })

    test2(x = 1, action = {
        println("hello $it")
    })

    // 进阶版如下
    // 看起来就是我们没有输入任何参数
    test2(5, action = {
        println("hello")
    })

    // test2(5,::test)// 上面说的那种情况 不适用在函数引用上

    test(5, ::test3)
    test2(5, ::test4)
    test5(5, ::test6)

    test5(5, action = { x, y ->
        test6(x, y)
    })
}

fun test(x: Int, action: () -> Unit) {
}

fun test2(x: Int, action: (para: Int) -> Unit) {
}

fun test3() {}

fun test4(x: Int) {}

fun test5(x: Int, action: (Int, Int) -> Unit) {
    action(1, 2)
}

fun test6(x: Int, y: Int) {
    println(x + y)
}


class HelloKotlin10 {
}