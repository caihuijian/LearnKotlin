package com.example.lib.d07scope.function

/**
 * takeUnless 内置函数
 * takeUnless与takeIf 完全相反
 *
 * name.takeUnless {true/false}
 * true: 返回null
 * false: 返回name本身
 *
 * name.takeIf {true/false}
 * true: 返回name本身
 * false: 返回null
 *
 * 为什么需要takeUnless？ takeIf一个就够了？
 * takeUnless的目的是 结合isNullOrBlank()判断变量是否经过初始化
 */

class Manager {
    var infoValue: String? = null
}

fun main() {
    val manager = Manager()
    val r = manager.infoValue.takeUnless { it.isNullOrBlank() } ?: "没有初始化"
    println(r)

    manager.infoValue = "初始化为aaa"
    val r2 = manager.infoValue.takeUnless { it.isNullOrBlank() } ?: "没有初始化"
    println(r2)
}

class HelloKotlin8 {
}