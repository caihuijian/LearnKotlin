package com.example.lib.d04functions

import java.util.*


fun main() {
    /**
     * 1 高阶函数
     */
    myCalculate(1, 3, { a, b -> a + b })
    myCalculate(1, 3) { a, b -> a + b }
    myCalculate(1, 3, ::myAdd)
    println("====== 1 end =======")

    // 2 高阶函数的应用
    // 过滤掉字符串中所有非字母字符
    println("123hellow123".filter { it.isLetter() })
    println("123ffff".filter { a -> a.isLetter() })
    println("he1llo123".filter2 { a -> a.isLetter() })
    println("====== 2 end =======")

    // 3 高阶函数的应用2
    // 筛选字符串数组中包含字符H h的字符串
    val strings = arrayOf("hello", "world", "kotlin", "hi")
    // 复杂写法
    filterStrings(strings) { string -> string.contains("H") || string.contains("h") }
    // 简化写法
    strings.filter { it.contains("h", ignoreCase = true) }.forEach { println(it) }
    println("-----")
    // 筛选字符串数组中字符串长度=5的
    // 复杂
    strings.filter { string -> string.length == 5 }.forEach { string -> println(string) }
    // 简化
    strings.filter { it.length == 5 }.forEach { println(it) }
    println("====== 3 end =======")

    // 4 高阶函数的应用3
    // 找出字符串数组中包含D或d的字符串 将其改为大写输出
    strings.filter { it.contains("d", true) }.forEach { println(it.uppercase(Locale.getDefault())) }
    println("====== 4 end =======")

    /**
     * 5 lambda表达式的返回值
     * 在默认情况下 lambda表达式的最后一句话会作为该lambda表达式的返回值
     * 我们可以全限定的return语法显示从lambda表达式返回值
     */
    strings.filter {
        val needFilter = it.length == 5
        needFilter //lambda表达式的默认返回值
    }
    // 什么是 全限定的return语法
    strings.filter {
        val needFilter = it.length == 5
        return@filter needFilter // 全限定的return语法 会使用到标签 用法为return@方法名 用来表示lambda的返回
        // 此种用法可以使用 但是通常不用 可读性不高
    }
    println("====== 5 end =======")

    /**
     * 6 匿名函数
     * 匿名函数与普通函数的区别
     * 匿名函数的参数类型如果可以推断出来 可以省略
     * 匿名函数不能之间定义在顶层(因为那样就无法调用了)
     * 匿名函数所有参数必须在圆括号中 参数是lambda表达式也一样
     * 匿名函数主要可以代替lambda表达式 (实际意义不大)
     * 匿名函数的return是返回匿名函数本身 而lambda表达式则不同 直接return表示返回到外层函数
     * 匿名函数的使用开起来不如lambda 了解即可
     */
    // 定义了两个无法使用的匿名函数
    fun(x: Int, y: Int) = x + y
    fun(x: Int, y: Int): Int {
        return x + y
    }

    val strings2 = arrayOf("hello", "world", "kotlin", "hi")
    // lambda
    strings2.filter { it.length == 5 }.forEach { println(it) }
    // 匿名函数代替lambda
    strings2.filter(fun(item: String): Boolean { return item.length == 5 }).forEach(fun(item) { println(item) })
    println("====== 6 end =======")
    /**
     * 7 闭包
     * Lambda 表达式或者匿名函数（以及局部函数和对象表达式） 可以访问其闭包 ，
     * 即在外部作用域中声明的变量。 在 lambda 表达式中可以修改闭包中捕获的变量
     * 如下 forEach中可以访问闭包中的 sum
     */

    var sum = ""
    val strings3 = arrayOf("hello", "world", "kotlin", "hi")
    strings3.filter { it.contains("h") }.forEach { sum += it }
    println(sum)
    println("====== 7 end =======")

    /**
     * 8 带有接收者的函数字面值
     * 这个功能及其类似扩展函数
     * 带有接收者的函数类型，例如 A.(B) -> C，可以用特殊形式的函数字面值实例化—— 带有接收者的函数字面值。
     * 如上所述，Kotlin 提供了调用带有接收者（提供接收者对象）的函数类型实例的能力。
     * 在这样的函数字面值内部，传给调用的接收者对象成为隐式的this，以便访问接收者对象的成员而无需任何额外的限定符，亦可使用this表达式访问接收者对象。
     * 这种行为与扩展函数类似，扩展函数也允许在函数体内部访问接收者对象的成员。
     *
     * 下面的变量是函数类型 Int的类似于扩展函数 接受一个int参数 返回一个int类型 {}里为方法体
     * 个人理解：
     * 接收者类型为Int 它在点号前面
     * 点号后面是函数字面值
     * 在函数内部可以使用this代表函数调用者
     */
    val subtract: Int.(other: Int) -> Int = { other -> this - other }
    // 等价于如下
    val subtract2: Int.(other: Int) -> Int = { this - it }
    println(3.subtract(4))
    println("====== 8 end =======")

    /**
     * 9 函数字面值结合匿名函数
     * 匿名函数语法允许你直接指定函数字面值的接收者类型。 如果你需要使用带接收者的函数类型声明一个变量，并在之后使用它，这将非常有用 -- 来自Kotlin官网
     *
     */
    // 使用匿名函数的case 单表达式
    val sum2: Int.(Int) -> Int = fun Int.(other: Int): Int = this + other
    // 使用匿名函数的case 变体
    val sum3: Int.(Int) -> Int = fun Int.(other: Int): Int { return this + other }
    // 使用lambda表达式的case
    // 对比lambda的形式 完全不知道 “这将非常有用” 有用在哪？
    val sum4: Int.(Int) -> Int = { this + it }
    println(1.sum2(2))
    println(1.sum4(2))
    println("====== 9 end =======")

    /**
     * 10 函数字面值扩展
     * 带有接收者类型的函数的非字面值 可以作为参数传递 前提是所需要接受函数的地方应该有一个接收者类型的参数
     * 类型 String.(Int) -> Boolean 等价于 (String,Int) -> Boolean
     */
    // myEquals是一个函数 并且使用了带有接收者的函数字面值 是String的一个方法 方法体
    // toIntOrNull是string的一个方法 当前如果可以转为int则返回其int值否则返回null
    val myEquals: String.(Int) -> Boolean = { it -> this.toIntOrNull() == it }
    println("456".myEquals(456))
    println("156".myEquals(456))
    // myTestEquals 是一个方法 接受3个参数 第一个参数是个方法，接受string和int参数 返回Boolean值
    fun myTestEquals(op: (String, Int) -> Boolean, a: String, b: Int) = println(op(a, b))
    myTestEquals(myEquals, "456", 456)
    myTestEquals(myEquals, "156", 456)
    // 可以看到myTestEquals第一个参数明明是(String, Int) -> Boolean 却可以将myEquals传入myTestEquals
    // 可见String.(Int) -> Boolean 等价于 (String,Int) -> Boolean
    println("====== 10 end =======")
}

// 3 高阶函数的应用2
fun filterStrings(strings: Array<String>, filter: (String) -> Boolean) {
    for (string in strings) {
        if (filter(string)) {
            println(string)
        }
    }
}

// 2 高阶函数的应用 start
fun String.filter(predicate: (Char) -> Boolean): String {
    val buffer = StringBuffer()
    for ((index, chars) in this.withIndex()) {
        if (predicate(chars)) {
            buffer.append(chars)
        }
    }
    return buffer.toString()
}

fun String.filter2(predicate: (Char) -> Boolean): String {
    val buffer = StringBuffer()
    for (index in this.indices) {
        if (predicate(this[index])) {
            println("[$index] is letter")
            buffer.append(this[index])
        } else {
            println("[$index] not letter")
        }
    }
    return buffer.toString()
}
// 2 高阶函数的应用 end

/**
 * 1 高阶函数示例
 * 该函数接受三个参数 第三个参数为一个名为calculate的函数
 * calculate函数实际只是个形参 规定了外部传入的函数接受2个int返回一个Int
 * 具体算法由外部决定
 */
fun myCalculate(a: Int, b: Int, calculate: (Int, Int) -> Int): Int {
    return calculate(a, b)
}

fun myAdd(a: Int, b: Int): Int {
    return a + b
}

class D0401Functions3