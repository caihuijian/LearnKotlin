package com.example.lib.d01start

import java.lang.NumberFormatException

fun main() {
    // 变量
    variable()
    // string 模板
    stringTemplate()
    // 条件表达式
    condition()
    // 空值检测
    nullCheck()
    // 类型检测与自动类型转换
    typeCheck();
    // 使用For循环
    useFor();
    // 使用while循环
    useWhile();
    // 使用when表达式
    //    TODO("学到了开始章节的when表达式 后面发现学习顺序不对 应该从基础一章开始学习")
    val param = 1.2
    useWhen(param)
}

// kotlin 版本的switch case
fun useWhen(obj: Any) {

    when (obj) {
        1 -> println("One")
        "Hello" -> println("Greeting")
        is Long -> println("Long")
        !is String -> println("Not a string")
        else -> println("Unknown")
    }
}

fun useWhile() {
    val items = listOf("apple", "banana", "kiwi")
    var index = 0
    while (index < items.size) {
        println("item at $index is ${items[index]}")
        index++
    }

    index = 0
    while (index in items.indices) {
        println("item at $index is ${items[index]}")
        index++
    }
}

fun useFor() {
    // 增强for循环版本
    for1()
    // 角标访问版本
    for2()
}

fun for1() {
    // 类似于
    // val items: List<String> = ArrayList(Arrays.asList("aaa", "bbb"))
    val items = listOf("apple", "banana", "kiwi")
    // kotlin中的遍历关键字in
    // 这里省略了item的声明
    for (item in items) {
        println(item)
    }
}

fun for2() {
    val items = listOf("apple2", "banana2", "kiwi2")
    // 类似于使用角标访问数组
    for (index in items.indices) {
        println("item at $index is ${items[index]}")
    }
}

//在使用is关键字内部 不需要再进行强制转换 在特定分支中 参数就是指定类型
fun typeCheck() {
    // kotlin 可以在方法中定义方法
    fun printLength1(obj: Any) {
        // 打印某个对象的字符串长度
        // ${getStringLength(obj) ?: "... err, not a string"} 花括号内是一个表达式
        // ?: 看起来是java三元操作符的简写 ?:前面的如果不是空 直接使用这个值 否则使用?:后面的值
        println(" $obj string length is ${getStringLength(obj) ?: "... err, not a string"} ")
    }

    printLength1("Incomprehensibilities")
    printLength1(1000)
    printLength1(listOf(Any()))
}

fun getStringLength(obj: Any): Int? {// Any代表任意类型
    // is 相当于Java中的instanceof
    if (obj is String) {
        // `obj` 在该条件分支内自动转换成 `String`类型
        return obj.length
    } // 在离开类型检测分支后，`obj` 仍然是 `Any` 类型
    return null
}

fun nullCheck() {
    // 当某个变量的值可以为 null 的时候，必须在声明处的类型后添加 ? 来标识该引⽤可为空
    println("parseInt ==> " + stringToInteger("ww"))
    println("parseInt ==> " + stringToInteger("12"))
    printProduct("w", "2")
}

// 使用?表明 返回值可能为空
fun stringToInteger(s: String): Int? {
    // 用到了Kotlin中使用try catch
    return try {
        Integer.valueOf(s)
    } catch (e: NumberFormatException) {
        null
    }
}

fun printProduct(arg1: String, arg2: String) {
    val x = stringToInteger(arg1)
    val y = stringToInteger(arg2)
    // 直接使⽤ `x * y` 会导致编译错误， 因为它们可能为 null
    if (x != null && y != null) {
        // 在空检测后， x 与 y 会⾃动转换为⾮空值（non-nullable）
        println(x * y)
    } else {
        println("'$arg1' or '$arg2' is not a number")
    }
}

fun condition() {
    println("max is :" + maxOf(2, 3))
    println("max is :" + maxOf2(2, 3))
}

fun maxOf(a: Int, b: Int): Int {
    if (a > b) {
        return a
    } else {
        return b
    }
}

// 这里使用了第一节在函数时使用的简写
fun maxOf2(a: Int, b: Int) = if (a > b) a else b


// const 代表运行时常量
// 相当于 public static final double MY_CONSTANT
const val MY_CONSTANT = 3.1415

fun variable() {
    // 变量声明的最常见方式
    // 变量 变量名:变量类型 = 赋值
    val a: Int = 1 // ⽴即赋值

    // 省略变量类型的声明 可以通过自动推断推断出类型
    val b = 2 // ⾃动推断出 `Int` 类型


    val c: Int // 如果没有初始值类型不能省略
    c = 3 // 明确赋值
    println(c)

    // 使用var来声明可变的变量
    var x = "String"
    println(x)
    x = "new String"
    println(x)

    // 使用val来声明不可变的变量 val有点像final关键字 但不完全相等
    val y = "String1111"
    //y = "new String1"//报错 Val cannot be reassigned

    // val 代表运行时常量
    // 相当于 final double PI
    val PI = 3.14
    println(PI)
    println(MY_CONSTANT)
}

fun stringTemplate() {
    var a = 1
    // $a代表打印变量中的真实的值
    // 并且该次赋值以后 即使a的值变化了 s1的内容也不再变化
    val s1 = "a is $a"
    println(s1)
    // 给变量a赋值新值2
    a = 2
    // 输出 now a is 2 and s1 is a is 1
    println("now a is $a and s1 is $s1")

    // ${}内部是一个表达式 调用了 String的replace方法
    val s2 = "${s1.replace("is", "was")}, but now is $a"
    // 输出 a was 1, but now is 2
    println(s2)
}

class D02Start {

}
