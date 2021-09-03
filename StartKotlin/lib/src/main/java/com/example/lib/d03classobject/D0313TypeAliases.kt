package com.example.lib.d03classobject

import java.io.File

// 1 为类型提供别名1
typealias NodeAB = HashMap<NodeA, NodeB>

// HashMap<NodeA,NodeB> 取名别名NodeAB
val map_AB1 = NodeAB()
val map_AB = HashMap<NodeA, NodeB>() // 等价
// 2 为类型提供别名2
typealias FileTable<K> = MutableMap<K, MutableList<File>>

interface MapInterface2 : FileTable<String> {
}

interface MapInterface : MutableMap<String, MutableList<File>> {// 等价
}
// 3 为函数类型提供另外的别名
typealias MyHandler = (Int, String, Any) -> Unit

fun three1(parameter: MyHandler) = parameter(1, "AAAAAA", 1.9)
fun three2(parameter: (Int, String, Any) -> Unit) = parameter(1, "AAAAAA", 1.9)

class RealHandler1 {
    fun invoke(p1: Int, p2: String, p3: Any): Unit {
        println(p1)
        println(p2)
        println(p3)
    }
}

// MyHandler 也可以理解为一个接口
class RealHandler2 : MyHandler {
    // 这里的override不能删除 invoke方法名称无法修改
    override fun invoke(p1: Int, p2: String, p3: Any): Unit {
        println(p1)
        println(p2)
        println(p3)
    }
}

// 4 内部类和嵌套类创建新名称
class AAA {
    inner class Inner {
        fun printAAA() {
            println("AAA Inner printAAA")
        }
    }
}

private class BBB {
    class Inner {
        fun printBBB() {
            println("BBB Inner printBBB")
        }
    }
}

typealias AAAInner = AAA.Inner
// private typealias 才能给private类的内部类取别名
private typealias BBBInner = BBB.Inner

// 5 为函数类型提供另外的别名2
typealias Predicate<T> = (T) -> Boolean

// 因为上面的定义 所以下面的代码有等价表达
fun foo1(parameter: Predicate<Int>) = parameter(42)
fun foo2(parameter: (Int) -> Boolean) = parameter(42)

fun main() {
    // 3 为函数类型提供另外的别名
    val p1: (Int, String, Any) -> Unit = { i: Int, s: String, any: Any ->
        println(i)
        println(s)
        println(any)
    }
    three1(p1)
    val p2: MyHandler = { i: Int, s: String, any: Any ->
        println(i)
        println(s)
        println(any)
    }
    three2(p2)
    // 讲MyHandler理解为一个接口
    RealHandler1().invoke(1, "ABC", 1.33)
    RealHandler2().invoke(1, "ABC", 1.33)


    // 4 内部类和嵌套类创建新名称
    //val aaaInner = AAAInner() // 报错 内部类只能在包含内部类的类中创建
    BBBInner().printBBB()// 嵌套类可以在外部创建

    // 5 为函数类型提供另外的别名2
    val f1: (Int) -> Boolean = { it > 0 }
    println(foo1(f1)) // 输出 "true"
    // 等价
    val f2: Predicate<Int> = { it > 0 }
    println(foo2(f2)) // 输出 "true"


    val p3: (Int) -> Boolean = { it > 0 }
    println(listOf(1, -2, 3, 0).filter(p3)) // 输出 "[1,3]"
    // 等价
    val p4: Predicate<Int> = { it > 0 }
    println(listOf(1, -2, 3, 0).filter(p4)) // 输出 "[1,3]"
}

class NodeA {}

class NodeB {}

class D0313TypeAliases