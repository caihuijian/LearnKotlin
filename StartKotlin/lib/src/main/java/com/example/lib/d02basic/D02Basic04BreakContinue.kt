package com.example.lib.d02basic

/**
 * Kotlin 有三种结构化跳转表达式：
 * return。默认从最直接包围它的函数或者匿名函数返回。
 * break。终⽌最直接包围它的循环。
 * continue。继续下⼀次最直接包围它的循环。
 */

fun main() {
    // 测试return
    testReturn(null)
    testReturn("aa")
    // break continue的典型用法
    classicBreakContinue()
    // 学习使用标签
    testLabel()
    // testLabel1 vs testLabel2 标签的意义
    testLabel1()
    testLabel2()
    // 标签的隐式用法
    testLabel3()
    // testLabel3的另外一种写法
    testLabel4()
    // 模拟Break
    mockBreak()
    // 返回标签时带有返回值
    println(returnLabel(listOf(1, 1, -1)))// 输出[]
    println(returnLabel(listOf(1, 0, 1)))// 输出[number 1, zero, number 1]
}

/**
 * 该方法用于理解
 * 当要返⼀个回值的时候，解析器优先选⽤标签限制的 return，即
 * return@a 1
 * 意为“返回 1 到 @a ”，⽽不是“返回⼀个标签标注的表达式 (@a 1) ”。
 *
 * 传入的参数里面只要有-1 就会返回空list 有0的则返回结果 不会出现"number 0"而是出现"zero"
 */
fun returnLabel(ints: List<Int>): List<String> {
    return ints.map label@{
        if (it == 0) return@label "zero" // return at named label 返回到标签
        if (it == -1) return emptyList() // return at returnLabel 返回整个方法
        "number $it" // expression returned from lambda 返回的表达式
    }
}

//foo(listOf(1, -1, 1)) // []
//foo(listOf(1, 0, 1)) // ["number 1", "zero", "number 1"]

fun mockBreak() {
    // run:Calls the specified function block and returns its result.
    // run:调用指定方法块并返回其结果
    run loop@{
        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return@loop // 从传⼊ run 的 lambda 表达式⾮局部返回
            // 意思应该是返回到run方法调用处而不是退出整个方法 关系到print(" done with nested loop")能否被调用
            // 虽然break只能在循环中使用('break' and 'continue' are only allowed inside a loop) 在
            // 普通的代码中 用return+label的方式可以模拟break的作用
            println(it)
        }
    }
    print(" done with nested loop")
}

fun testLabel1() {
    listOf(1, 2, 3, 4, 5).forEach {
        if (it == 3) return // ⾮局部直接返回到 foo() 的调⽤者 即跳出foo方法
        println(it)
    }
    println("this point is unreachable")
}

fun testLabel2() {
    listOf(1, 2, 3, 4, 5).forEach lit@{// 声明标签
        if (it == 3) return@lit //使用标签 跳到声明标签的地方
        // 局部返回到该 lambda 表达式的调⽤者， 即 forEach 循环 而不是跳出整个方法 其实类似一个continue
        // 但是break和continue都是用在循环中的关键字 标签就起到替代作用
        print(it)
    }
    println(" done with explicit label")
}

fun testLabel3() {
    listOf(1, 2, 3, 4, 5).forEach {// 省略了标签的声明
        if (it == 3) return@forEach // 局部返回到该 lambda 表达式的调⽤者， 即 forEach 循环
        // 常情况下使⽤隐式标签更⽅便。本例就是一个隐式标签 该标签与接受该 lambda 的函数同名
        print(it)
    }
    println(" done with implicit label")
}

fun testLabel4() {
    listOf(1, 2, 3, 4, 5).forEach(fun(value: Int) {// 这里使用了匿名内部类替代lambda表达式
        // 因为return效果是推出函数执行体 那么这里的return只能跳出当前函数 起到的作用和上面一样 即一个continue
        if (value == 3) return // 局部返回到匿名函数的调⽤者， 即 forEach 循环
        print(value)
    })
    print(" done with anonymous function")
}


fun classicBreakContinue() {
    for (i in 1..10) {
        if (i == 3) {
            continue // i 为 3 时跳过当前循环，继续下一次循环
        }
        println(i)
        if (i > 5) {
            break // i 为 6 时 跳出循环
        }

    }
}

fun testLabel() {
    // 声明标签
    loop@ for (i in 1..100) {
        if (i == 10) break@loop //使用标签 跳到声明标签的地方 类似但不等价于C的goto
        println(i)
    }
}

fun testReturn(string: String?) {
    println("start=====")
    // ?:是一个二元表达式 如果 参数string==null 走前面的分支
    // 否则 走后面的分支
    val s = string ?: return
    println(s)
    println("end=====")
}

class D02Basic04BreakContinue {
}