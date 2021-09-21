package com.example.lib.d06reflect

/**
 * 函数组合
 * 将多个函数组合在一起 形成一个函数
 */

/**
 * 该方法接受两个参数 两个参数都是函数类型
 * 返回一个结果 一个结果也是函数类型
 * 接受的参数1 接受类型B 返回类型C
 * 接受的参数2 接受类型A 返回类型B
 * 返回的函数  接受类型A 返回类型C
 *
 * 那么内部调用可以先调用参数2的函数 再调用参数1的函数 正好符合返回的函数的输入输出
 * 有点成语接龙的意思
 * 注意 {}代表lambda表达式 即函数类型
 * 该函数真正的用途是对输入参数依次调用f2 f1 最后输出
 */
fun <A, B, C> myCompose(f1: (B) -> C, f2: (A) -> B): (A) -> C {
    return { x -> f1(f2(x)) }
}

// 三个函数的函数组合
fun <A, B, C, D> myCompose(f0: (C) -> D, f1: (B) -> C, f2: (A) -> B): (A) -> D {
    return { x -> f0(f1(f2(x))) }
}

// 判断一个int是否为偶数 (Int)->Boolean
fun isEven(x: Int) = 0 == x % 2

// 返回字符串长度 (String)->Int
fun length(s: String) = s.length

fun main() {
    // 两个函数组合成一个函数 接受一个String 返回一个Boolean 表示接受参数String的长度如果为偶数则返回true
    val evenLength = myCompose(::isEven, ::length)
    val strings = listOf("1", "12", "123", "1234", "12345")
    // filter方法 接受一个T类型 返回一个Boolean
    println(strings.filter(evenLength))
}

class HelloKotlin4 {
}