package com.example.lib.d03classobject

fun main() {
    // 1 嵌套类
    // 通过外部类调用嵌套类
    val demoNestClass = Outer.Nested().foo() // == 2
    println(demoNestClass)

    // 3 内部类
    // 内部类 vs 嵌套类
    // 内部类使用inner关键字修饰
    // 内部类有一个外部类的引用 因此内部类可以在类体中访问外部类的属性和方法 嵌套类不行
    val demoInnerClass = Outer2().Inner().foo() // == 1
    println(demoInnerClass)
    println(Outer2().Inner().testInner())

    // 4 匿名内部类
    setOnClickListener(object : OnClickListener {
        override fun onClick() {
        }
    })
}

// 4 匿名内部类 demo
fun setOnClickListener(l: OnClickListener) {
}

interface OnClickListener {
    fun onClick()
}

class Outer2 {
    private val bar: Int = 1
    fun testOuter() {
        println("testOuter")
    }

    fun test() {
        println("outer test")
    }

    inner class Inner {
        fun foo() = bar
        fun testInner() {
            testOuter()
            println("testInner")
            this@Outer2.test() // 调用外部类的同名方法
            this.test() // 调用内部类的同名方法
        }

        private fun test() {
            println("inner test")
        }
    }
}

// 2 更多嵌套类的形式start
interface OuterInterface {
    // a接口嵌套类
    class InnerClass

    // b接口嵌套接口
    interface InnerInterface {
        fun callback()
    }
}

class OuterClass {
    // c类嵌套类
    class InnerClass

    // d类嵌套接口
    interface InnerInterface
}
// 2 更多嵌套类的形式end

// 1 嵌套类demo
class Outer {
    private val bar: Int = 1
    fun testOuter() {
        println("testOuter")
    }

    class Nested {
        fun foo() = 2
        // fun foo() = bar // 嵌套类无法访问外部类的属性
        //fun testInner(){ // 嵌套类无法访问外部类的方法
        //    testOuter()
        //}
    }
}

class D0310NestedAndInnerClass