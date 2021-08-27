package com.example.lib.d03classobject

import java.util.ArrayList

// 1 扩展函数与扩展方法
// 你可以为⼀个你不能修改的、来⾃第三⽅库中的类编写⼀个新的函数。这个新增的函数就像那
// 个原始类本来就有的函数⼀样，可以⽤普通的⽅法调⽤。这种机制称为 扩展函数 。此外，也有 扩展属性 ，允许
// 你为⼀个已经存在的类添加新的属性。

fun main() {
    // 2 测试扩展函数
    val demo = D0306Extends<Int>()
    demo.add(1)
    demo.add(10)
    demo.printItems()

    println("hi!".addDot(5))

    // 4 扩展函数案例解析
    printClassName(Rectangle())
    // 5 扩展超类
    val nullObject: Int? = null
    // 调用Any的扩展方法
    println(nullObject.toString())
    val notNull = "abc"
    // 这里调用的直接是Any里面的方法
    println(notNull.toString())

    // 6 扩展属性
    println(demo.size)

    // 7 伴生对象的扩展方法
    // 使用伴生对象的扩展方法
    D0306Extends.printString("Test Companion Object Extension Method");

    // 9 扩展成员方法 仅在扩展接收者内部可以使用
    // myHost--扩展接收者
    val myHost = Host("kotl.in")
    // myConn--分发接收者
    val myConn = Connection(myHost, 443)
    myConn.connect()
    //Host("kotl.in").printConnectionString(443) // 错误， 该扩展函数在 Connection 外不可⽤

    // 10 分发接收者与扩展接收者成员名字冲突
    // 扩展接收者优先 要引⽤分发接收者的成员你可以使⽤
    // 限定的 this 语法

    // 11 扩展方法的覆盖
    // 声明为成员的扩展可以声明为 open 并在⼦类中覆盖
    // 这意味着这些函数的分发对于分发接收者类型是虚拟
    // 的，但对于扩展接收者类型是静态的
    BaseCaller().call(Base())   // “Base extension function in BaseCaller”
    BaseCaller().call(Derived()) // “Base extension function in BaseCaller”
    DerivedCaller().call(Base())  // “Base extension function in DerivedCaller”——分发接收者虚拟解析
    DerivedCaller().call(Derived())  // “Base extension function in DerivedCaller”——扩展接收者静态解析
    // 官方文档解释了一堆 我觉得关键就是fun call(b: Base)中的声明 是什么类型的
    // 如果声明的是Base 那么不管传入的实际类型是什么 总是调用Base的成员扩展方法
    // 如果声明的是Derived 则总是调用Derived的成员扩展方法
}

// 基类Base 基类扩展接收者
open class Base {}

// 子类Derived 子类扩展接收者
class Derived : Base() {}

// 基类分发接收者
open class BaseCaller {
    // Base的成员扩展方法
    open fun Base.printFunctionInfo() {
        println("Base extension function in BaseCaller")
    }

    // Base的子类Derived  的成员扩展方法
    open fun Derived.printFunctionInfo() {
        println("Derived extension function in BaseCaller")
    }

    // 这里声明为Base类型 因此调用的是 Base的成员扩展方法
    fun call(b: Base) {
        b.printFunctionInfo()   // 调用扩展函数
    }
}

// 子类分发接收者
// BaseCaller的子类
class DerivedCaller : BaseCaller() {
    // 覆盖父类  Base的成员扩展方法
    override fun Base.printFunctionInfo() {
        println("Base extension function in DerivedCaller")
    }

    // 覆盖父类  Base的子类Derived  的成员扩展方法
    override fun Derived.printFunctionInfo() {
        println("Derived extension function in DerivedCaller")
    }

    // 继承了父类的call方法
}

// 10 分发接收者与扩展接收者成员名字冲突
class Connection2 {
    fun Host.getConnectionString() {
        // Host的实例是扩展接收者
        toString() // 优先调⽤ Host.toString()
        // Connection2的实例是分发接收者
        // 限定this调用
        this@Connection2.toString() // 调⽤ Connection.toString()
    }
}

// 9 扩展成员属性与方法
class Host(val hostName: String) {
    // 带有一个String的主构造函数
    fun printHostName() {
        print(hostName)
    }
}

class Connection(val host: Host, val port: Int) {
    // 带有2个String的主构造函数
    fun printPort() {
        print(port)
    }

    // 在Connection内部扩展Host类的方法
    // 这样Connection的实例称为 分发接收者
    // Host类的实例称为 扩展接收者
    fun Host.printConnectionString() {
        printHostName() // 调⽤ Host.printHostname()
        print(":")
        printPort() // 调⽤ Connection.printPort()
    }

    fun connect() {
        host.printConnectionString() // 调⽤扩展函数
    }
}

/**
8 扩展方法 扩展属性的作用域
如果定义在顶层的扩展方法和属性 在其他包内都可以使用 使用方法如下
import com.example.lib.d03classobject.addDot
import com.example.lib.d03classobject.size

fun main() {
"aaa".addDot(2)
"aa".size
}

但是如果添加private修饰符 或者定义在类内部（8 扩展属性demo3）则外部无法使用
 */

// 7 伴生对象的扩展方法Demo
// 定义伴生对象的扩展方法
fun D0306Extends.Companion.printString(string: String) {
    println(string)
}


// 6 扩展属性 demo
val <T> D0306Extends<T>.size: Int // = 1 //Initializer is not allowed here because this property has no backing field
    // 扩展属性不支持幕后字段field
    // 它没有默认的set get方法
    // 因此 扩展属性强制我们给他编写get方法
    get() = this.arr.size

// 6 扩展属性 demo2
val String.size: Int
    get() {
        return this.length
    }

// 5 扩展超类 接受可空类型的参数
fun Any?.toString(): String {
    if (this == null) {
        return "null"
    }
    // 空检测之后， “this”会⾃动转换为⾮空类型， 所以下⾯的 toString()
    // 解析为 Any 类的成员函数
    // 因为Any的toString不接受null
    return toString()
}

// 4 扩展函数案例解析
open class Shape
class Rectangle : Shape()

// 创建Shape的扩展方法
fun Shape.getName() = "Shape"

// 创建Rectangle的扩展方法
// 但需要注意的是 getName不是真正的插入到Rectangle内部 因此该方法也不会有覆盖的概念
// 与父类的 getName没有任何关系
fun Rectangle.getName() = "Rectangle"
fun printClassName(s: Shape) {// 这里声明的是什么类型 则调用谁的getName方法
    // 而不会考虑它的实际类型
    // VS java
    // java的父类引用指向子类对象 调用的被覆盖的方法 实际调用的是子类方法
    println(s.getName())
}

// 2 扩展函数demo1
fun D0306Extends<Int>.add(number: Int) {
    this.arr.add(number)
}

// 2 扩展函数demo2
fun String.addDot(count: Int): String {
    return this + ".".repeat(count)
}

/**
 * 反编译出的addDot方法
@NotNull
public static final String addDot(@NotNull String $this$addDot, int count) {
Intrinsics.checkNotNullParameter($this$addDot, "$this$addDot");
return $this$addDot + StringsKt.repeat((CharSequence)".", count);
}
 */
// 3 扩展函数原理
// 扩展不能真正的修改他们所扩展的类。通过定义⼀个扩展，你并没有在⼀个类中插⼊新成员，仅仅是可以通过该
// 类型的变量⽤点表达式去调⽤这个新函数
// 扩展函数原理是 个人觉得是 创建一个静态方法 调用者作为参数传入,在该静态方法内部操作调用者

class D0306Extends<T>() {

    // 8 扩展属性demo3
    fun String.printIt() {
        println(this)
    }

    // 伴生对象
    companion object {

    }

    var arr: MutableList<T> = ArrayList()
    fun printItems() {
        for (item in arr) {
            println(item)
        }
    }
}