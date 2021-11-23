package com.example.lib.d07scope.function

/**
 * let函数详解
 *
 * 1 let函数返回的是lambda表达式 即根据lambda表达式最后一行不同而不同
 * 2 let内部可以使用it代表调用者
 *
 * let的应用：与？结合用于判空
 * 安全调用运算符 ?. 以及 Elvis运算符(空值合并运算符)  ?:
 */

fun main() {
    // 普通方式 对集合第一个元素相加
    val list = listOf(6, 5, 4, 7, 8)
    val value1 = list.first()
    val res = value1 + value1
    println(res)

    // 使用let 对集合第一个元素相加
    val res2 = list.let {
        it.first() + it.first()// res2的值类型为let表达式的最后一行
        //true
        //"abc"
    }
    println(res2)
    println("===============")

    // 普通方式判空
    println(getMethod1(/*null*/"chj"))
    println(getMethod2(/*null*/"chj"))
    println(getMethod3(/*null*/"chj"))
    println(getMethod4(/*null*/"chj"))
}

// 普通方式判空
fun getMethod1(value: String?): String {
    return if (value == null) "传入空参" else "参数不为空 $value"
}

// 普通方式判空 简化版
// 这里用的是单表达式函数 当函数返回单个表达式时，可以省略花括号并且在 = 符号之后指定代码体即可
fun getMethod2(value: String?) = if (value == null) "传入空参" else "参数不为空 $value"

// let方式判空 let结合? 可以方便地判空
// 安全调用运算符?. 用于可空类型变量安全调用非空类型的属性或者函数，而不会抛出空指针异常，当变量为null时，直接返回空值null，否则进行调用并返回结果
// Elvis运算符(空值合并运算符):  ?:    ?:是一个二元运算符，作用是判断可空类型时空值合并，语法为：可空类型数据 ?: 空值合并到的数据，
// Kotlin 中不存在三目运算符。当数据非空时，直接返回数据，当数据为空时，返回合并到的数据
fun getMethod3(value: String?): String {
    return value?.let {
        "参数不为空 $it"// 走到这里 value必定不为空
    } ?: "传入空参"
}

// let方式判空 let结合? 简化版
fun getMethod4(value: String?) =
    value?.let {
        "参数不为空 $it"// 走到这里 value必定不为空
    } ?: "传入空参"


class HelloKotlin3 {
}