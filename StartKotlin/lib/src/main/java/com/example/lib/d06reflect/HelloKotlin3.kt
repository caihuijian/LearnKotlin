package com.example.lib.d06reflect

// 函数(方法)引用


// 1 函数引用的普通使用
// 返回一个int的3倍数
fun multiplyBy3(x: Int): Int {
    return x * 3
}

// 2 函数引用的重载
fun multiplyBy3(s: String): Int {
    return s.length
}

fun main() {
    val values = listOf(1, 2, 3) // 返回List 列表

    // val values = arrayOf(1, 2, 3) // 返回Array 数组
    /**
     * Array.map方法的声明
     * public inline fun <T, R> Array<out T>.map(transform: (T) -> R): List<R>
     *     接受一个函数 返回一个一个List
     *     该函数输入一个T类型参数 返回一个R 类型对象 当然 T和R可以相同
     *     map的含义是对集合中的每一个元素施加指定函数
     *
     * 我们可以看到multiplyBy3符合这个函数
     */
    println(values.map(::multiplyBy3)) // 函数类型推断

    val values2 = listOf("hello", "world", "hello world")
    println(values2.map(::multiplyBy3)) // 函数类型推断
}

class HelloKotlin3 {
    fun instanceMethod(s: String): Int {
        return s.length
    }
}

/**
 * 总结
 * 函数引用 即传入函数作为参数
 * 函数类型如果能从上下文推断 那么支持重载
 * ::multiplyBy3表示函数类型 (Int)->Int 或者 (String) ->Int的值
 * 如果该方法不是来自顶层的方法 而是某个类的普通方法或者扩展方法 需要加上类的索引
 */
val myRef: (Int) -> Int = ::multiplyBy3
val myRef2: (String) -> Int = ::multiplyBy3
val myRef3: String.(Int) -> Char = String::get // 注意类比 声明和使用
val myRef4: HelloKotlin3.(String) -> Int = HelloKotlin3::instanceMethod
