package com.example.lib.d03classobject

/**
 * 扩展函数的解析是静态的
 * 1.扩展本身不会真正修改目标类(它不会真的插入属性或方法 而是在调用时将当前对象作为第一个参数传递进去 然后调用方法或者属性)
 * 2.扩展函数的解析是静态的 它不是动态的 不支持多态 调用取决于声明类型
 * 3.调用由对象声明类型决定 而不是实际类型
 */

open class AA
class BB : AA()

fun AA.a() = "a"
fun BB.a() = "b"

fun myPrint(aa: AA) {
    println(aa.a())
}

fun main() {
    myPrint(AA())
    // 扩展函数的解析是静态的 不支持多态 调用由对象声明类型决定
    myPrint(BB())
}

class D03Add2 {

}