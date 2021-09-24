package com.example.lib.d06reflect

/**
 * 属性引用作为方法使用
 */
fun main() {
    // 1 属性引用作为方法使用
    val values = listOf("a", "ab", "abc")
    /**
     * 这里为什么可以使用属性引用？
     * map接受一个函数作为参数
     * (T) -> R: 接受T 返回R
     * 这里可以理解为 接受每一个item的字符串String 返回 String的length
     *
     * 也就是说属性引用可以用在不接受参数的方法上
     */
    println(values.map(String::length))
    println("=======1 end========")

    // 要想访问一个类中的成员属性 (有接收者的属性)
    // 需要使用全限定名称(需要加上具体类名 如这里的MyClass)
    val x = MyClass::x
    println(x.get(MyClass(10)))
    println(x.get(MyClass()))
}

// 2 有接收者的属性引用
class MyClass(val x: Int = 2)

class HelloKotlin6 {
}