package com.example.lib.d05others

import java.io.FileNotFoundException
import kotlin.jvm.Throws

class D0509JavaCallKotlin5 {
    /**
     * 1 Java调用Kotlin抛出检查时异常的方法
     * 因为Kotlin没有checked exception 因此Java调用该方法时 认为该方法是普通方法
     * 可以直接调用 然而却在运行时抛出异常 且method invoked 都不会输出
     */
    fun method() {
        println("method invoked")
        throw FileNotFoundException("file not found")
    }

    /**
     * 2 Java调用Kotlin抛出检查时异常的方法(Java不异常终止)
     * @Throws 注解告诉Java文件 该方法会抛出异常
     */
    @Throws(FileNotFoundException::class)
    fun method2() {
        println("method invoked")
        throw FileNotFoundException("my file not found")
    }

    /**
     * 3 Java调用Kotlin时 空保护失效
     * 这里str在Kotlin明显是一个非空的string
     * 然而Java调用时并不知道这一点
     * 如果直接传入空 会被Kotlin在判空检查时就检查出来 因此method3 invoked都不会输出 直接抛出异常
     */
    fun method3(str: String) {
        println("method3 invoked")
        println(str)
    }
}
