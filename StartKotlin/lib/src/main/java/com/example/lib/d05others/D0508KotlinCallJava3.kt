package com.example.lib.d05others


fun main() {
    // 1 Kotlin调用Java 异常
    val exception = MyException()
    // 取消如下可以看到 调用Java的需要处理异常的方法 Kotlin不需要对异常进行处理
    // exception.myMethod()

    // 2 查看Java class类的2种方式
    println(MyException()::class.java)
    println(MyException().javaClass)
}

class D0508KotlinCallJava3 {

}