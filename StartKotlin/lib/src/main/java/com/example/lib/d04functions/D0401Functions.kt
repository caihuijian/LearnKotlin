package com.example.lib.d04functions

// 函数
fun main() {

//    // 1 函数的默认参数
//    test()
//    test(2)
//    // b 是具名参数
//    // 显示指定参数名
//    test(b = 3)
//    test(3, 2)
//    test(a = 3)
//    // 2 函数默认值在函数覆盖时的注意点
//
//    // 3 无默认值参数在有默认值参数
//    // 这种情况如果想省略参数 只能通过具名参数的方式来使用
//    test2(1, 2) // 要么不省略任何参数
//    test2(b = 3) // 要么 只能使用具名参数
//
//    // 4 函数最后一个参数是一个函数
//    // 调用已有方法
//    test3(2, 3, ::test)
//    // 另外新写方法
//    test3(4, 5, { a, b -> println(a - b) })
//    // 将上面的方法缩写
//    // 即 函数最后一个参数是一个lambda表达式 可以将其移动到小括弧外部
//    test3(4, 5) { a, b -> println(a - b) }
//    // 省略一个参数
//    test3(4) { a, b -> println(a - b) }
//    test3(b = 4) { a, b -> println(a - b) }
//    // 调用函数时 如果同时使用了位置参数和具名参数，那么所有位置参数必须要位于第一个具名参数之前
//    // 例如 foo(1,x=2) OK foo(x=2,2) NG
//
//    // 5 可变参数vararg
//    test4("a", "b", "c")
//    test4New("a", "b", "c")
//
//    // 6 * spread operator 分散运算符
//    val arrays:Array<String> = arrayOf("str1", "str2", "str3")
//    // 不能直接传入string数组 Kotlin会以为只有一个参数  Type mismatch. Required: String Found: Array<String>
//    // test4(strings) // 报错
//    // 需要使用 * 字符拆分符 将string数组拆分为多个参数
//    test4(/*strings =*/*arrays)
//    test4(/*strings =*/*arrayOf("a","b"))
//    test4("a")
//
//    // 7 具名参数与Java
//    // 在Kotlin中调用Java代码时不能使用具名参数 因为Java字节码不总是会保留参数名信息
//    // 即 具名参数不能用在Kotlin调用Java代码时
//
//    // 8 函数简写
//    myPrintNew("======")
//
//    // 9 单表达式函数
//    add(1,2)
//
//    // 10 显式返回类型
//    // 拥有方法体或者说块体的方法(即花括号)需要定义显式的返回类型 除非该方法返回Unit
//    // Kotlin 不会推断拥有方法体或者说块体的方法的返回值类型 因为这种函数可能有非常复杂的控制流程
//    // 对于阅读代码的人和编译器来说 返回类型不是那么明显了 因此Kotlin统一不对所有显示返回类型的方法做类型推断
//    // 例如addNew就是一个 显示返回类型的方法
//
//    // 11 可变参数的补充
//    // 一个方法中 只允许有一个参数为可变参数，通常是最后一个参数 但是有例外
//    // 如果vararg不是最后一个参数 那么可变参数后面的参数需要通过具名参数进行传递
//    // 如果其后的参数是函数类型 可以通过圆括号外部传递lambda表达式来实现
    println(convert2List("hello", "world"))
    val elements = arrayOf("welcome", "kotlin", "test")
    println(convert2List("1", "2", elements))
    println(convert2List("1", "2", *elements))//注意和上面区别
    println(convert2ListNew("hello", "world", a = "1", b = "2"))

    println(convert2ListNew2("hello", "world") { a, b ->
        println("a is $a b is $b")
    })
    println(convert2ListNew2("hello") { a, b ->
        println("a is $a b is $b")
    })
}

// 11 可变参数的补充
fun <T> convert2List(vararg element: T): List<T> {
    val res = ArrayList<T>()
    element.forEach { res.add(it) }
    return res
}

// 11 可变参数的补充 可变参数后面还有其他参数
fun <T> convert2ListNew(vararg element: T, a: T, b: T): List<T> {
    val res = ArrayList<T>()
    element.forEach { res.add(it) }
    res.add(a)
    res.add(b)
    return res
}

// 11 可变参数的补充 可变参数后面的参数是一个函数
fun <T> convert2ListNew2(vararg element: T, myPrint: (x: T, y: T) -> Unit): List<T> {
    val res = ArrayList<T>()
    element.forEach { res.add(it) }
    if (res.size >= 2) {
        myPrint(res[0], res[1])
    } else {
        println("list too short")
    }
    return res
}

// 9 单表达式函数
// 函数的返回类型如果可以通过类型推断判断出来 那么返回类型可以省略
fun add(a: Int, b: Int) = a + b

fun addNew(a: Int, b: Int): Int {
    return a + b
}

// 8 函数简写
fun myPrint(name: String) {
    println(name)
}

// 完整写法
fun myPrintNew(name: String): Unit {
    println(name)
    return Unit
}

// 5 可变参数vararg
fun test4(vararg strings: String) {// strings 属于string数组类型 // 注意string数组和Array<String> 不一样
    println(strings.javaClass)
    strings.forEach { println(it) }
}

fun test4New(vararg strings: String) {
    println(strings.javaClass)
    strings.forEach { a ->
        println(a)// 当只有一个参数时 可以简写为上面那种形式 println(it)
    }
}

// 4 函数最后一个参数是一个函数
// 如下 第三个参数是一个接受2个参数 返回值为空的函数
fun test3(a: Int = 1, b: Int = 2, compute: (x: Int, y: Int) -> Unit) {
    compute(a, b)
}

// 3 无默认值参数在有默认值参数
fun test2(a: Int = 0, b: Int) = println(a + b)

// 2 函数默认值在函数覆盖时的注意点 start
open class A {
    open fun function(a: Int, b: Int = 4) = a + b
}

class B : A() {
    // 对于重写的方法来说 子类重写的方法会使用父类方法的参数默认值 即使父类方法的参数没有默认值
    // 子类也不能拥有自己参数的默认值
    // 个人理解 这里Kotlin的设计是为了避免函数默认值混乱
    // An overriding function is not allowed to specify default values for its parameters
    override fun function(a: Int, b: Int) = a + b
}
// 2 函数默认值在函数覆盖时的注意点 end

// 1 函数的默认参数
fun test(a: Int = 0, b: Int = 1) {
    println(a - b)
}

// 如果函数体只有一句话 可以去掉{} 写成如下形式
fun testNew(a: Int = 0, b: Int = 1) = println(a - b)

class D0401Functions {
}