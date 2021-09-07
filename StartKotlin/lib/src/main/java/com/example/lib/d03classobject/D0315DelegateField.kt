package com.example.lib.d03classobject

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

// 属性委托

fun main() {
//    // 1属性委托
//    var e = MyDelegateExample()
//    e.delegateProperty = "hello"
//    println(e.delegateProperty)
//
//    // 2 延迟属性 Lazy
//    println(myLazyValue)
//    println(myLazyValue)
//    // 3 非空属性
//    // 适用于无法在创建对象时就赋初值的变量
//    val myPerson = MyPerson()
//    myPerson.address = "shanghai"
//    println(myPerson.address)// 注释上面的一行将抛出IllegalStateException
    // 4 可观测属性(observable)
    // 观察者模式
    val person2 = Person2()
    person2.age = 25
    person2.age = 40
    println(person2.age)
    // 5 map 属性

}

// 4 可观测属性start
class Person2 {
    // observable是一个函数 第一参数为属性的默认值 第二个参数为一个名为onChange的函数 该函数也是一个lambda表达式
    // lambda表达式 接受三个入参 1属性 2属性的旧值 3属性的新值 lambda表达式的返回值为Unit 即没有返回值
    // onChange实际也是一个CallBack 他会在属性值发生变化时回调
    var age: Int by Delegates.observable(20) { property, oldValue, newValue ->
        println("${property.name}, oldValue:$oldValue, newValue:$newValue")
    }

    var age2: Int by Delegates.vetoable(20) { property, oldValue, newValue ->
        when {
            oldValue <= newValue -> true
            else -> false
        }
    }
}
// 4 可观测属性 end

// 3 非空属性start
class MyPerson {
    // Returns a property delegate for a read/write property with a non-null value that is initialized not during object construction time but at a later time. Trying to read the property before the initial value has been assigned results in an exception.
    // Delegates.notNull返回一个属性的代理
    // 该属性不是对象构造函数调用时初始化 而是在后面某一个时间点初始化
    // 尝试调用未初始化值的变量会抛出异常
    var address: String by Delegates.notNull()
}
// 3 非空属性end

// 2 延迟属性 start
// 延迟属性只有在第一次访问的时候才会初始化 之后继续访问会使用第一次访问时候的缓存值返回
// lazy 实际是一个函数
//                       方法名lazy lazy的参数名initializer 参数类型为 () -> T,是一个函数,也是一个lambda表达式,该函数没有入参,其返回值是T
//                       lazy方法返回值为Lazy<T> 是一个委托
// public actual fun <T> lazy(initializer: () -> T): Lazy<T> = SynchronizedLazyImpl(initializer)
// lazy函数还有一个重载方法
// public actual fun <T> lazy(mode: LazyThreadSafetyMode, initializer: () -> T): Lazy<T>
// 其参数有以下可选值
// LazyThreadSafetyMode.NONE 不使用锁来同步对lazy属性实例值的访问;如果从多个线程访问该实例，它的行为不可预知。只有确定初始化操作只在一个线程执行时使用 可以减少线程同步的开销
// LazyThreadSafetyMode.SYNCHRONIZED 用于确保只有单个线程可以初始化实例。该值也是lazy初始化的默认值
// LazyThreadSafetyMode.PUBLICATION 与SYNCHRONIZED相反 可以在并发访问未初始化的 lazy的 实例值时多次调用初始化函数 但只有第一个返回值会被用作lazy属性的实际的值。
val myLazyValue: Int by lazy(LazyThreadSafetyMode.NONE) {// 一个函数的最后一次参数如果是lambda表达式 可以省略最后一个参数 并将lambda表达式写在方法外部 用花括号包起来
    println("init")// 同一个线程初始化时 只会打印一次
    30
}
// 2 延迟属性 end


// 1属性委托 start
class MyDelegateExample {
    // 委托属性语法
    // val/var <属性名>: <类型> by <表达式>
    // 如果是val 可以不需要setValue方法
    var delegateProperty: String by MyDelegateProperty()
}

class MyDelegateProperty {
    // 操作符重载
    // 参数:代理对象 代理属性
    // 含义为代理了哪个对象的哪个属性
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    // 参数:代理对象 代理属性 设置的新值
    // 含义为代理了哪个对象的哪个属性 其新值是多少
    // 但是注意 这里设置的新值只是打印了一下 实际丢弃了 没有实际赋值给属性
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}
// 1属性委托 end

class D0315DelegateField