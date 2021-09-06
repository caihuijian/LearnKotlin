package com.example.lib.d03classobject

fun main() {
//    // 1 接口的委托
//    // 有点像策略模式 初始化的时候 传入不同的接口实现 相当于传入不同的策略
//    // 一个类实现多个接口,并委托给不同的接口实现类
//    // 只有接口可以委托 类不行
//    val b = BaseImpl2(10)
//    val d = BaseImpl4(10)
//    val derived1 = Derived2(b,d)
//    derived1.print()
//    derived1.println()
//
//    val c = BaseImpl3(10)
//    val derived2 = Derived2(c,d)
//    derived2.print()
//    derived2.println()
//
//    // 2 覆盖由委托实现的接口
//    val e = BaseImpl5(10)
//    Derived3(e).printMessage()
//    Derived3(e).printMessageLine()
//
//    // 3 易错点
//    val b = BaseImpl6(10)
//    val derived = Derived4(b)
//    // 注意下面的输出不同
//    // 此print调用的是BaseImpl6中的print 因此message使用的也是BaseImpl6中的message
//    derived.print()
//    // 以下打印的是真实Derived4实例中的message变量
//    println(derived.message)
}

// 3 start
interface Base5 {
    val message: String
    fun print()
}

class BaseImpl6(val x: Int) : Base5 {
    // 必须覆盖的属性
    override val message = "BaseImpl: x = $x"

    // 必须覆盖的方法
    override fun print() {
        println(message)
    }
}

class Derived4(b: Base5) : Base5 by b {
    // 在 b 的 `print` 实现中不会访问到这个属性
    override val message = "Message of Derived"
    // 但是如果覆盖了接口的方法 那么两次输出将会一致
//    override fun print() {
//        println(message)
//    }
}
// 3 end

// 2 覆盖由委托实现的接口
interface Base4 {
    fun printMessage()
    fun printMessageLine()
}

class BaseImpl5(val x: Int) : Base4 {
    override fun printMessage() {
        println("printMessage$x")
    }

    override fun printMessageLine() {
        println("printMessageLine$x")
    }
}

class Derived3(b: Base4) : Base4 by b {
    // 覆盖Base4中的printMessage方法
    override fun printMessage() {
        println("abc")
    }
}
// 2 覆盖由委托实现的接口 end

// 1 接口的委托 start
// 接口2
interface Base2 {
    fun print()
}

// 接口3
interface Base3 {
    fun println()
}

// 实现接口2的类2
class BaseImpl2(val x: Int) : Base2 {
    override fun print() {
        println(x)
    }
}

// 实现接口2的类3
class BaseImpl3(val x: Int) : Base2 {
    override fun print() {
        println(" haha $x")
    }
}

// 实现接口3的类4
class BaseImpl4(val x: Int) : Base3 {
    override fun println() {
        println(" println $x")
    }
}

// 尝试委托类 start
open class ClassAA {
    open fun classAAPrint() {
        println("classAAPrint")
    }
}

open class ClassAAImpl : ClassAA() {
    override fun classAAPrint() {
        println("ClassAAImplPrint")
    }
}
// 尝试委托类 end

// 需要实现接口2 接口3
// 但该类没有真正实现接口2 接口3 而是把实现委托给其他实现了该接口的类
// 并且这些接口的实现都可以委托给其他的类
class Derived2(b: Base2, c: Base3) : Base2 by b, Base3 by c /*,ClassAA by ClassAAImpl 只有接口可以委托*/
// 1 接口的委托end

class D0314Delegate 