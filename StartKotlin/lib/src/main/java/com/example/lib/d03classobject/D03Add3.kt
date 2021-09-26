package com.example.lib.d03classobject

/**
 * 1 扩展方法名字与成员方法名字冲突
 * 成员方法优先
 */
class CC {
    fun foo() {
        println("member")
    }
}

fun CC.foo() {
    println("member2")
}

/**
 * 2 扩展方法支持重载
 */
fun CC.foo(string: String) {
    println("member2 $string")
}

fun main() {
    // 扩展方法名字与成员方法名字冲突 成员方法优先
    CC().foo()

    // 扩展方法支持重载
    CC().foo("aa")
}

class D03Add3 {
}