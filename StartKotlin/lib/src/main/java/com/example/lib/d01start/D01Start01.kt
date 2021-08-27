package com.example.lib.d01start

// 0 写在前面的话
// 将Kotlin转为Java步骤:
// Tools → Kotlin icon Kotlin → Show Kotlin Bytecode
// Decompile
// 会将Kotlin Code的二进制转为Java Code 方便对比学习

// 同时可以在Android studio中将java code转换为kotlin code
// 选择Java file 右键 convert java file to kotlin file
// 一时不知道kotlin的语法 可以先写java 再转为kotlin
// 甚至可以先写好Java 的代码 直接copy到Kotlin文件中
// Android Studio 会检测到剪切板是Java Code 询问你是否将Java代码转换为Kotlin代码
// 点击确认 可以直接转换

// 1 kotlin程序的入口
// 与普通的Java文件不同 写在类外部
fun main() {
    println("Hello World")
    // 2 Kotlin函数的学习
    method();
}

fun method() {
    println(sum(13, 14));
    println(sum1(14, 14));
    println(printSum(13,15))
    println(printSum1(33,22))
}

// kotlin方法的定义
/*
    方法关键字 方法名(参数1：参数1类型,参数2：参数2类型...):返回值类型{
      方法体
    }
*/
fun sum(a: Int, b: Int): Int {
    println("start $a+$b")
    return a + b
}

// 如果方法体内部只有一句话 可以简写成如下
// 返回类型可以推断 “=”类似"return"
fun sum1(a: Int, b: Int) = a + b

// 像sum方法内部有两行代码 无法缩写成sum1的样子
//fun sum2(a: Int, b: Int) = println("start $a+$b") a + b// 报错

// Unit 无意义的值 类似于 void
fun printSum(a: Int, b: Int): Unit {
    println("sum of $a and $b is ${a + b}")
}

// Unit类型可以忽略不写
// 方法体内部的$a $b代表变量的实际值
// ${a + b} {}内部是一个表达式
// 如果返回值类型是Unit 还会打印kotlin.Unit的信息
fun printSum1(a: Int, b: Int) {
    println("sum1 of $a and $b is ${a + b}")
}

class D01Start {

}
