package com.example.lib.d05others

/**
 * 属性 (properties)
 * 一个Kotlin的属性会被编译为3个部分
 * 1.一个get方法
 * 2.一个set方法
 * 3.一个私有field域(field)
 *
 * 如果Kotlin的属性以is开头 那么set get方法会发生一些变化
 * get方法方法名与属性名一致
 * set方法会将is替换为set
 * 这种规则适用于所有类型的属性 不一定时Boolean类型
 */
class Test {
    var isStudent: String = "yes"
}

// 定义在顶层空间的属性和方法都是静态属性和方法
fun test1() {
    println("test1")
}

var str: String = "hello"

class D0509JavaCallKotlin {
    fun nonStaticMethod() {
        println("nonStaticMethod")
    }
}