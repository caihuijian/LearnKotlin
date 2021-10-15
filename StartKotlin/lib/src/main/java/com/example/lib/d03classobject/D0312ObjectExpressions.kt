package com.example.lib.d03classobject

// object关键字作用有三
// 1 对象声明 : object单独使用
// 2 对象表达式 :object 对象作为参数或者被用于赋值 例如匿名内部类
// 3 伴生对象: object与companion一起使用 伴生对象的初始化时机与类实例绑定在一起
// 伴生对象内部的属性和方法就类似于Java里面的静态方法 但是只是类似而已，虽然用法上和静态方法一样但是
// 只有加上@JvmStatic的属性和方法 才是真的静态属性和静态方法

fun main() {
    // 0 对象声明
    AppConfig.setSth()
    println(AppConfig)
    println(AppConfig)// 单例

    myPrint(PrintMessage())
    // 1 对象表达式常用场景
    // 要创建一个对某个类做了轻微改动的类的对象
    // object 使用demo
    // 对PrintMessage类稍作修改
    myPrint(object : PrintMessage() {
        override fun printA() {
            println("printAAAAA")
            // 6 对象表达式可以访问继承对象的属性和方法
            println(x)
        }
    })

    // 2 对象表达式也可运用于接口
    // Android 中的callback onClickListener就是这种思路
    useInter(object : InterfacePrint {
        override fun printInter() {
            println("print interface")
        }
    })

    // 3 object 继承多个类demo
    println(ClassA(10).y)
    println(aAndB.y)

    // 4 创建一个简单对象
    foo()

    // 5 object 匿名对象作为返回值
    // 作用不大 感觉没什么意义
    println(ClassC().publicFoo().javaClass)
    ClassC().callPrivateFoo()
    ClassC().bar()

    // 10 使用object对象
    DataProviderManager2.printDataProviderManager2()

    // 11 伴生对象
    // 伴生对象只存在于class中 且每个class只有一个
    // 调用伴生对象的成员
    MyClass.create()
    // 使用类名引用伴生对象
    println(MyClass.Factory)

    // 12 使用Companion引用伴生对象
    // 省略伴生对象的类名 直接引用伴生对象
    println(MyClass2.Companion)

    // 13 使用类的名称引用伴生对象
    // 使用类的名称作为伴生对象的引用
    // 无论伴生对象是否具名 都可以使用类的名称作为伴生对象的引用
    val x = MyClass3
    println(x)
    val y = MyClass4
    println(y)

    // 14 伴生对象可以继承接口
    val f: Factory<MyClass5> = MyClass5// MyClass5就是伴生对象
}

interface Factory<T> {
    fun create(): T
}

class MyClass5 {
    companion object : Factory<MyClass5> {
        override fun create(): MyClass5 = MyClass5()
    }
}

// 13 使用类的名称作为伴生对象的引用
class MyClass3 {
    companion object Named {}
}

class MyClass4 {
    companion object {}
}

// 12 省略伴生对象的类名 直接引用伴生对象
class MyClass2 {
    // 伴生对象的声明
    companion object {
        fun create(): MyClass = MyClass()
    }
}

// 伴生对象
class MyClass {
    // 伴生对象的声明
    companion object Factory {
        fun create(): MyClass = MyClass()
    }
}

// 报错
//val objectVal = object MyData{}

open class Base1 {
    fun funBase() {}
}

// 6 对象声明
// 7 对象声明可以有父类
// 8 object没有构造方法
// 9 对象是一个单例 且对象声明的初始化过程是线程安全的并且在首次访问时进行
object DataProviderManager2 : Base1() {
    init {
        println("init object")
    }

    // 10
    fun printDataProviderManager2() {
        println("printDataProviderManager2")
    }
}

// 5 object 匿名对象作为返回值
class ClassC {
    // 私有函数，所以其返回类型是匿名对象类型
    private fun foo() = object {
        val x: String = "x"
    }

    fun callPrivateFoo() {
        println(foo().javaClass)
    }

    // 公有函数，所以其返回类型是 Any
    fun publicFoo() = object {
        val x: String = "x"
    }

    fun bar() {
        val x1 = foo().x        // 没问题 作用域为
        println(x1)
        //val x2 = publicFoo().x  // 错误：未能解析的引用“x” Any没有属性x
    }
}

// 4 创建一个简单对象
fun foo() {
    val simpleObject = object {
        var x: Int = 1
        var y: Int = 0
    }
    println(simpleObject.x + simpleObject.y)
}

// 3 object 继承多个类demo
open class ClassA(x: Int) {
    open val y: Int = x
}

interface InterfaceB {
    fun printB() {
        println("printB")
    }
}

val aAndB: ClassA = object : ClassA(1), InterfaceB {
    override val y = 15
}

// 2 对象表达式也可运用于接口
fun useInter(interfaceOnj: InterfacePrint) {
    interfaceOnj.printInter()
}

interface InterfacePrint {
    fun printInter()
}

// 1 object 使用demo
fun myPrint(printObj: PrintMessage) {
    printObj.printA()
    printObj.printB()
}

open class PrintMessage() {
    var x = 10

    open fun printA() {
        println("printA")
    }

    fun printB() {
        println("printBB")
    }
}

// 0 对象声明
object AppConfig {
    init {
        println("init config")
    }

    fun setSth() {
        println("set something")
    }

    var a = 1
}

class D0312ObjectExpressions