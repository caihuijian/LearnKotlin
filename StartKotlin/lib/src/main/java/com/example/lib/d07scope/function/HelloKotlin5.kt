package com.example.lib.d07scope.function

import com.example.lib.d01start.getStringLength
import sun.security.util.Length

/**
 * with 函数详解
 *
 * 1 with函数返回的是lambda表达式 即根据lambda表达式最后一行不同而不同
 * 2 with内部可以使用this代表调用者
 *
 * 总结 with 和 let 几乎完全一样 只不过调用方式有些许差别
 * with(str){
 * }
 *
 * str.let{
 * }
 */

fun main() {
    val string = "abc"

    // 具名函数操作
    val r1 = with(string, ::getStrLength)
    val r2 = with(r1, ::getLengthInfo)
    val r3 = with(r2, ::getInfoMap)
    with(r3, ::show)

    // 匿名操作
    with(with(with(with(string) {
        this.length
    }) {
        "你的字符串长度为：$this"
    }) {
        "[$this]"
    }) {
        println(this)
    }
}

fun getStrLength(string: String) = string.length
fun getLengthInfo(length: Int) = "你的字符串长度为：$length"
fun getInfoMap(info: String) = "[$info]"
fun show(content: String) = println(content)

class HelloKotlin5 {
}