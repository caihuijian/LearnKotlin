package com.example.lib.d02basic


fun main() {
    // if 表达式
    conditionIf()
    // when 表达式
    conditionWhen()
    // for 循环遍历
    conditionFor()
    // while 循环
    conditionWhile()
}

fun conditionWhile() {
    testWhile()
    testDoWhile()
}

fun testDoWhile() {
    var parameter = 5
    do {
        parameter--
        println(parameter)
    } while (parameter > 0)
}

fun testWhile() {
    var parameter = 5
    while (parameter > 0) {
        parameter--
        println(parameter)
    }
}

fun conditionFor() {
    val arrayOfString: Array<String> = arrayOf("Hello", "World","Kotlin")
    // 经典for循环
    classicTraverse(arrayOfString)
    // iterator遍历
    iteratorTraverse(arrayOfString)
    val arrayOfInt: IntArray = intArrayOf(11, 14, 17)
    // 区间表达式的for循环
    intervalExpressionTraverse()
    // 下标遍历
    indexTraverse(arrayOfString)
    // withIndex 遍历
    withIndexTraverse(arrayOfString)
}

fun withIndexTraverse(arrayOfString: Array<String>) {
    for ((index, value) in arrayOfString.withIndex()) {
        println("the element at $index is $value")
    }
}

fun indexTraverse(arrayOfString: Array<String>) {
    for (i in arrayOfString.indices) {
        println(arrayOfString[i])
    }
}

fun intervalExpressionTraverse() {
    // 输出1 2 3
    for (element in 1..3) {
        println(element)
    }
    // 从6 降序到0 步长2 输出6 4 2 0
    for (i in 6 downTo 0 step 2) {
        println(i)
    }
}

fun iteratorTraverse(arrayOfString: Array<String>) {
    val iterator =  arrayOfString.iterator()
    while (iterator.hasNext()){
        println(iterator.next())
    }
}

fun classicTraverse(arr: Array<String>) {
    for (item in arr) {
        println(item)
    }
}

// when 语句相当于Java中的switch case
fun conditionWhen() {
    // when 作为表达式使用
    println(expression(2))
    // when 作为表达式使用 所有分支已经覆盖可能结果
    println(expression2(false))
    // when 不作为作为表达式使用
    sentence(2)
    // when 表达式也可以将条件合并
    multiCondition(1)
    // 我们可以甚至可以用方法（⽽不只是常量）作为分⽀条件
    println(expressionAsCondition("hello"))
    println(expressionAsCondition("hello1"))
    // 我们可以⽤任意表达式（⽽不只是常量）作为分⽀条件
    expressionAsCondition2(11)
    expressionAsCondition2(1)
    // when里面的类型推断
    println(hasPrefix(1))
    println(hasPrefix("prefix"))
    // when 表达式是bool值
    boolWhen(1, 3)
}

fun boolWhen(x: Int, y: Int) {
    when {
        isEven(x) -> println("x is isEven")
        isEven(y) -> println("y is isEven")
        else -> println("x y are Odd.")
    }
}

fun isEven(x: Int): Boolean {
    return x % 2 == 0
}


// 判断字符串是否以prefix打头
// 将when表达式的值直接赋值给方法返回值
fun hasPrefix(x: Any): Boolean = when (x) {
    // is String == true后 该分支里面 x是String类型的
    is String -> x.startsWith("prefix")
    else -> false
}

fun expressionAsCondition(x: String): String {
    // 我们可以⽤任意表达式（甚至是方法作为分支条件 ⽽不只是常量）作为分⽀条件
    val str: String = "test"
    return when (x) {
        getResString(str) -> "greeting"
        else -> "none of the above"
    }
}

fun getResString(param: String): String {
    return if (param == "test") {
        "hello"
    } else {
        "unknown"
    }
}

val arrayOfInt: IntArray = intArrayOf(11, 14, 17)
fun expressionAsCondition2(x: Int) {
    // 我们可以⽤任意表达式（⽽不只是常量）作为分⽀条件
    when (x) {
        in 1..10 -> print("x is in the range")
        in arrayOfInt -> print("x is valid")
        !in 10..20 -> print("x is outside the range")
        else -> print("none of the above")
    }
}

fun parseInt(s: String): Int {
    TODO("Not yet implemented")
}

fun multiCondition(x: Any) {
    when (x) {
        // when 表达式也可以将条件合并
        0, 1 -> println("x == 0 or x == 1")
        else -> println("otherwise")
    }
}

fun sentence(obj: Any) {
    // when 不作为作为表达式使用(不使用when的返回结果) 可以没有else分支
    when (obj) {
        1 -> println("x==1")
        2 -> println("x==2")
    }
}

fun expression(obj: Any): String {
    // when 作为表达式使用(使用when的返回结果) 必须有else分支 除非所有分支已经覆盖可能结果
    return when (obj) {
        1 -> "x==1"
        2 -> "x==2"
        else -> { // 注意这个块
            "unknown"
        }
    }
}

fun expression2(obj: Boolean): String {
    // when 作为表达式使用(使用when的返回结果) 必须有else分支 除非所有分支已经覆盖可能结果
    return when (obj) {
        true -> "true..."
        false -> "false..."
    }
}

fun conditionIf() {
    // 传统⽤法
    val a = 10
    val b = 12
    var max1: Int = a
    if (a < b) max1 = b
    // With else
    val max2: Int
    if (a > b) {
        max2 = a
    } else {
        max2 = b
    }
    // if本身是一个表达式 可以将他的结果传入参数
    val max3 = if (a > b) {
        println("Choose a")
        a
    } else {
        println("Choose b")
        b
    }

    // val max4 = if (condition) A else B
    // 如果condition成立 max4 = A 否则 max4 = B
    println(max3)
}

class D02Basic03Contrl {

}