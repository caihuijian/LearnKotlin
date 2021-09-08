package com.example.lib.d03classobject

import java.util.*
import kotlin.properties.Delegates
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

// 属性委托

fun main() {
    // 1属性委托
    var e = MyDelegateExample()
    e.delegateProperty = "hello"
    println(e.delegateProperty)

    // 2 延迟属性 Lazy
    println(myLazyValue)
    println(myLazyValue)

    // 3 非空属性
    // 适用于无法在创建对象时就赋初值的变量
    val myPerson = MyPerson()
    myPerson.address = "shanghai"
    println(myPerson.address)// 注释上面的一行将抛出IllegalStateException

    // 4 可观测属性
    // 观察者模式 变化后收到通知
    val person2 = Person2()
    person2.age = 25
    person2.age = 40
    println(person2.age)
    // 观察者模式 变化前收到通知
    person2.age2 = 19
    println("after set age2 19 now value is ${person2.age2}")
    person2.age2 = 25
    println("after set age2 25 now value is ${person2.age2}")
    person2.age2 = 21
    println("after set age2 21 now value is ${person2.age2}")

    // 5 map 属性委托
    // 如果一个类里面的属性太多 可以将属性托管给map 这样 属性值可以在map中批量输出和输入
    // 5.1 可读map委托
    // mapOf用于创建Map
    val student = MyStudent(mapOf(
            "name" to "zhangsan",
            "age" to 19,
            "address" to "shanghai",
            "birthday" to Date()
    ))
    println(student.name)
    println(student.age)
    println(student.address)
    println(student.birthday)

    // 需要手动指定Map的类型 否则Kotlin会自动进行类型推断
    // 将map2推断为<String,String>
    // 5.2 可读可写map委托
    val map2: MutableMap<String, Any?> = mutableMapOf(
            "name" to "lisi",
            "address" to "shanghai"
    )
    val student2 = MyStudent2(map2)
    println(map2["name"])
    println(student2.name)
    println(map2["address"])
    println(student2.address)
    println("-----")

    // 操作student中的name
    student2.name = "wangwu"
    // 通过map委托 操作student中的address属性
    map2["address"] = "hunan"
    println("-----")
    println(map2["name"])
    println(student2.name)
    println(map2["address"])
    println(student2.address)

//     6 属性委托的原理
//     该结论可以通过反编译kotlin code确认
//     对于每个委托属性来说 Kotlin编译器会在底层生成一个对应的辅助属性,将所有对原属性的操作 都转交为对该辅助属性的操作
//     例如 有委托属性prop Kotlin编译器会在底层生成一个对应的辅助属性prop$delegate 所有对prop的操作(包括读写) 都会委托给
//     这个由kotlin编译器生成的额外的辅助属性

    // 7 提供委托 (providing a delegate)
    // 可以理解为 是否提供委托?
    // 之前的委托是无条件委托 即在任何情况下 都会交给对应的委托
    // 而提供委托可以选择在某些条件交给委托处理 在另外某些条件 不交给委托处理
    // 提供委托的实现方式
    val people2 = MyPeople()
    println(people2.name)
    println(people2.address)
}

// 7 提供委托 (providing a delegate) start
class MyPeople {
    val name: String by PeopleLauncher()
    val address: String by PeopleLauncher()
}

// 这个类相当于一个中间类
// 或者说一个工厂类 决定使用哪个代理类 或者不使用代理类而是抛出异常
class PeopleLauncher {
    operator fun provideDelegate(thisRef: MyPeople, prop: KProperty<*>): ReadOnlyProperty<MyPeople, String> {
        println("start to verify if need delegate")
        when (prop.name) {
            "name", "address" -> return MyProvideDelegate()// 如果故意写错属性名称 将会抛出异常
            else -> throw Exception(" invalid prop name")
        }
    }
}

class MyProvideDelegate : ReadOnlyProperty<MyPeople, String> {
    override fun getValue(thisRef: MyPeople, property: KProperty<*>): String {
        return "hello world"// 所有属性值都是hello world
    }
}
// 7 提供委托 (providing a delegate) end

// 5 map 属性委托 start
// map 委托(只读)
// 将属性存储到map中
// map的key要与属性的名称保持一致
class MyStudent(map: Map<String, Any?>) {
    val name: String by map
    val age: Int by map
    val address: String by map
    val birthday: Date by map
    val oldName: String by this::name// 委托给同一个类的另一个属性 感觉意义不大
}

// map 委托(读写)
// 将属性存储到map中
// map的key要与属性的名称保持一致
class MyStudent2(map: MutableMap<String, Any?>) {
    var name: String by map
    var address: String by map
}
// 5 map 属性委托 end


// 4 可观测属性start
class Person2 {
    // observable Returns a property delegate for a read/write property that calls a specified callback function when changed.
    // observable 返回一个属性的读写委托 该委托会在属性值发送变化后触发一个指定的回调
    // 注意观察其返回类型是 ReadWriteProperty<Any?, T>
    // 它是一个接口 里面的两个方法的签名其实和我们之前学的属性委托的两个方法签名几乎一致

    // observable是一个函数 第一参数为属性的默认值 第二个参数为一个名为onChange的函数 该函数也是一个lambda表达式
    // lambda表达式 接受三个入参 1属性 2属性的旧值 3属性的新值 lambda表达式的返回值为Unit 即没有返回值
    // onChange实际也是一个CallBack 他会在属性值发生变化时回调
    var age: Int by Delegates.observable(20) { property, oldValue, newValue ->
        println("${property.name}, oldValue:$oldValue, newValue:$newValue")
    }

    // vetoable是一个函数 vetoable是可否决的意思 接受两个参数 第一参数为属性的默认值 第二个参数为一个名为onChange的函数 该函数也是一个lambda表达式
    // lambda表达式 接受三个入参 1属性 2属性的旧值 3属性的新值 lambda表达式的返回值为Boolean
    // 若返回值为true 代表修改生效 否则 修改无效
    // onChange实际也是一个CallBack 他会在属性值即将变化时回调
    var age2: Int by Delegates.vetoable(20) { property, oldValue, newValue ->
        when {
            // 当旧值小于等于新值 修改生效
            oldValue <= newValue -> true
            // 否则不生效
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

// 可读可写的属性的委托 可以选择实现ReadWriteProperty<Any?,String>的两个方法 也可以自己写两个方法
class MyDelegateProperty /*: ReadWriteProperty<Any?,String>*/ {
    /*
    override fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
    */
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