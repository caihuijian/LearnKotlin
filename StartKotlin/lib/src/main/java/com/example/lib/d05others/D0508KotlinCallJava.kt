package com.example.lib.d05others


// Kotlin调用Java
fun main() {
    val list = ArrayList<Any>();
    list.add("hello")
    list.add("world")
    list.add("hello world")

    for (item in list) {
        println(item)
    }
    println("-----------")
    for (i in 0 until list.size) {
        println(list[i])
    }
    println("-----------")

    // 调用Java model类
    val people = People()
    people.age = 25
    people.isMarried = false
    people.name = "zhangsan"
    println(people.age)
    println(people.isMarried)
    println(people.name)
    println("-----------")
    /**
     * Kotlin调用Java代码时 Kotlin的空检查可能会失效
     *
     * 在Java中 所有引用都可能为null 在Kotlin中 对null有着严格限制
     * 在Kotlin中 将来自Java的声明类型称为平台类型(Platform Types)
     * 对于这种类型(平台类型)来说 Kotlin的null检查会变得缓和 不再那么严格.
     * 这么做的目的是使得空安全的语义在Java和Kotlin中保持一致
     * 当我们调用平台类型引用的方法时 Kotlin就不会在编译期间施加空安全检测 使得编译可以通过
     * 但是在运行时可能抛出异常 因为平台类型的引用值可能为空
     */

    // 当我们调用平台类型引用的方法时 Kotlin就不会在编译期间施加空安全检测 demo
    // ArrayList是一个平台类型 可以看它的定义 不过是Java ArrayList的一个别名
    // ArrayList是一个平台类型 Kotlin不会对其施加严格的空检查 list2[0]可能报错
    val list2 = ArrayList<String>()
    //list2.add("hello")
    val size = list2.size
    val item = list2[0] //数组越界
    // 是否是平台类型由Kotlin编译器自己识别 无法主动创建一个平台类型

    val s: String? = item // 允许
    // 为什么s2是一个非空类型的String 却可以将其赋值为一个可能为空的item? 因为这是一个平台类型 Kotlin的检查不再严格
    val s2: String = item // 允许 但是运行时可能报错(如果item为空)

    // kotlin 非空检查的原理: 如果声明一个非空类型 则会在该变量赋值时产生一个断言 断言赋值不为空 如果断言失败 则编译期就报错
    /**
     * 如果我们使用了不可空的类型 编译器会在赋值时生成一个断言。这会防止Kotlin的不可空变量持有一个null值；这一点也适用于方法的参数传递
     * 我们在一个平台类型值传递到不可空的参数或方法参数时 也同样会生成这个断言 但是该断言只能检测明显空的情况
     *
     * 总体来说 Kotlin会竭尽所能防止null赋值蔓延到程序的其他地方 而是在问题发生时就立刻通过断言来解决
     */
}

class D0508KotlinCallJava {
}