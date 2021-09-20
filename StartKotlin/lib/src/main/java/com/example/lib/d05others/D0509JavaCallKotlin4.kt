package com.example.lib.d05others

/**
 * 1 JvmName注解 解决方法命名冲突
 *
 * 由于类型擦除 (Java在生成的字节码文件中会抹去泛型信息) 以下两个方法在字节码的签名一致
 * 因此报错
 * Platform declaration clash: The following declarations have the same JVM signature
 * (myFilter(Ljava/util/List;)Ljava/util/List;)
 * 大意是 myFilter都接受一个List参数 返回一个List
 * (为什么会接受一个List参数? 这是因为Java没有扩展方法的概念 Kotlin的扩展方法的实现原理是将类的实例传入,然后调用该实例的方法
 * 因为这里是List的扩展方法 因此会将List实例作为参数传入扩展方法)
 *
 * 要想让这两个方法同时存在 可以使用 @JvmName注解
 */
fun List<String>.myFilter(): List<String> {
    return listOf("hello", "world")
}

@JvmName("myFilter2")
fun List<Int>.myFilter(): List<Int> {
    return listOf(1, 2, 3)
}

// 2 JvmName注解 解决方法命名冲突2
class MyClass {
    val a: Int
        /**
         * Kotlin默认给属性a提供了get方法 签名为getA
         * 因此如果另外又定义了getA方法 则会产生命名冲突
         * 需要使用JvmName注解 将方法名改为另一个名字
         * 但是注意 这里的JvmName还是给Java类用的 不影响Kotlin自身调用 a 属性
         */
        @JvmName("getAValue")
        get() = 20

    fun getA() = 30
}

/**
 * 3 Java调用Kotlin带有默认参数的构造方法
 * 如果没有加上@JvmOverloads
 * Java能调用的构造方法 只有两个参数的构造方法 无法省略参数
 * 加上@JvmOverloads后 编译的文件会生成一个参数的构造方法 该方法内部调用了两个参数的构造方法
 */
class MyClass5 @JvmOverloads constructor(x: Int, y: String = "hello") {
    // 4 Java调用Kotlin带有默认参数的普通方法
    // JvmOverloads注解同样适用于普通方法
    @JvmOverloads
    fun myMethod(a: Int, b: String, c: Int = 2) {
        println("a:$a,b:$b,c:$c")
    }
}

fun main() {
    // 1 JvmName注解 解决方法命名冲突 示例
    // 需要注意的是 这里的别名只给Java使用 在Kotlin中使用方法仍然应该使用myFilter而非myFilter2
    val list = listOf<Int>()
    println(list.myFilter())

    val list2 = listOf<String>()
    println(list2.myFilter())

    // 2 JvmName注解 解决方法命名冲突2 示例
    println(MyClass().getA())
    // 注意 这里调用a的方式和Java不同
    println(MyClass().a)

    println(MyClass5(1))
}

class D0509JavaCallKotlin4 {
}