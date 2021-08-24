package com.example.lib.d03classobject

fun main() {
    val myChild = D0303Interface.Child()
    println(myChild.prop)
    println(myChild.propertyWithImplementation)
}

// 4 接口继承 TODO
class D0303Interface {
    // 2 如何实现接口
    class Child : MyInterface {
        // 覆盖接口中的prop值
        override val prop: Int = 1

        // 覆盖接口中的propertyWithImplementation值
        // 如果不覆盖 则其值为foo
        override val propertyWithImplementation: String = "child string"


        // 该方法必须实现 因为MyInterface没有具体实现
        override fun bar() {
            // ⽅法体
        }

        // 该方法可以不覆盖 因为接口已经有实现
        override fun foo() {
            super.foo()
        }
    }

}

// 1 如何定义接口
interface MyInterface {
    // 3 接口中的属性
    // 你可以在接⼝中定义属性。在接⼝中声明的属性要么是抽象的，要么提供访问器的实现。在接⼝中声明的属性不
    // 能有幕后字段（backing Meld），因此接⼝中声明的访问器不能引⽤它们
    val prop: Int // 抽象属性 没有具体赋值 子类必须实现该属性(给他具体初始值)
    val propertyWithImplementation: String // 非抽象属性 默认值为foo
        get() = "foo"

    fun bar()
    fun foo() {
        // 可选的⽅法体
        // 与Java不同的是 Kotlin的接口可以有具体的实现
        println("foo")
    }
}