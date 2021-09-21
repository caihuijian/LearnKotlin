package com.example.lib.d06reflect

import kotlin.reflect.KMutableProperty

/**
 * 属性引用(Method Reference)
 * 属性引用与之前的函数引用的用法完全一致 通过::属性名 的形式调用
 */

// 1 常量的属性引用
val age = 3

// 2 变量的属性引用 注意是var 不是val
var age2 = 5


fun main() {

    // 首先我们需要了解一个继承结构 KProperty0继承自KProperty KProperty继承自KCallable
    // KClass和KProperty有点像
    /**
     * ::age代表类型KProperty<Int>的属性对象实例 我们可以通过get()获取其值 通过name获取其名字
     * KProperty：Represents a property, such as a named val or var declaration. Instances of this class are obtainable by the :: operator.
     * KProperty是一个接口 KProperty代表属性 例如val或var的声明 KProperty的class实例可以通过::操作符获取
     * ::age就是获取到了一个KProperty<Int>的实例
     */
    println(::age)
    /**
     * get方法返回KProperty0类型的实例 它是一个接口
     * Represents a property without any kind of receiver. Such property is either originally declared in a receiverless context such as a package, or has the receiver bound to it.
     * KProperty0代表一个没有任何接收者的属性. 这种属性定义在一个无接收者的上下文(例如package内) 或者没有被一个接收者接收者绑定
     * 定义在package内也就是定义在顶层空间的意思 没有绑定到接收者可以反过来理解 String::length就是绑定到了String上 String是接受者
     * 没有绑定到接收者就是::前面没有东西
     *
     */
    println(::age.get())
    /**
     * The name of this callable as it was declared in the source code. If the callable has no name, a special invented name is created. Nameless callables include:
     * constructors have the name "",
     * property accessors: the getter for a property named "foo" will have the name "<get-foo>", the setter, similarly, will have the name "<set-foo>".
     * name属性是KCallable接口的一个属性 他代表源码中定义的名字 如果可调用项没有名称，则会创建一个特殊的虚构名称。匿名可调用项包括：
     * 1.构造函数的名称为""
     * 2.属性访问器：名为foo的属性的getter名称为“<get foo>”，setter将具有名称“<set foo>”(Kotlin中不需要自己定义属性的get set方法 这里解释的应该就是set get方法的name)
     *
     * 这里我们可以先只关注普通的name
     */
    println(::age.name)
    println("========1 end========")

    /**
     * ::age2 返回的类型是KMutableProperty<Int> 它有一个set方法
     * 理解了KProperty后 KMutableProperty其实是类似的
     */
    println(::age2)

    // 通过反射调用set get方法
    ::age2.set(10)
    println(::age2.get())

    // 正常调用set get方法
    age2 = 20
    println(age2)
}

class HelloKotlin5 {
}