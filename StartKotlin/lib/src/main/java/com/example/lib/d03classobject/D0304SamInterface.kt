package com.example.lib.d03classobject

fun main() {
    // 2 函数式接口的应用2
    // 测试使用SAM函数
    println("res = ${res1.add(2, 9)}")
}

class D0304SamInterface {}

// 1 什么是函数式（SAM）接⼝
// 只有⼀个抽象⽅法的接⼝称为函数式接⼝或 SAM（Single Abstract Method 单⼀抽象⽅法）接⼝。函数式接⼝可以有多个⾮抽象成员，但
// 只能有⼀个抽象成员。
// 可以⽤ fun 修饰符在 Kotlin 中声明⼀个函数式接⼝。
// 函数式接口 VS 普通接口
// 在interface关键字前+fun 并且接口只有一个方法
// 那么这个接口就是 函数式（SAM）接⼝
fun interface KRunnable {
    fun invoke()
    // 如果添加这一句话会报错
    // Fun interfaces must have exactly one abstract method
    // 函数式接口只能有一个抽象方法
    // fun invoke2()
}

// 2 函数式接口的应用1:SAM 转换
// SAM 转换 即使⽤ lambda 表达式替代创建实现函数式接⼝的类
// 例如有一个函数式接口
fun interface IntPredicate {
    fun add(i: Int, j: Int): Int
}

// 不用SAM转换 即直接继承接口
val res = object : IntPredicate {
    override fun add(i: Int, j: Int): Int {
        return i + j
    }
}

// 使用SAM转换
val res1 = IntPredicate { i, j -> i + j }
// 个人猜测 函数式接口只有一个抽象接口 那么可以 用接口名直接代表抽象方法 方法体内部就是lambda表达式的抽象接口的实现