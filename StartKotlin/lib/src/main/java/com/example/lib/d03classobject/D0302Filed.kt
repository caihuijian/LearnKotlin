package com.example.lib.d03classobject

import java.util.*
import javax.xml.ws.Action

// 10 编译期常量
// 与编译期相对的是运行期 编译期常量就代表在编译时就存在的常量
// 编译期常量可以声明的位置有:1 顶层 2 object声明 3  companion object伴生对象

// 以下的声明用Java表示为
// public static final String CONST_VARIABLE =
const val CONST_VARIABLE1: String = "CONST_VARIABLE1"// 编译期常量这里声明在顶层

object DataProviderManager {
    // 关于object声明的章节后面详说
    const val CONST_VARIABLE3: String = "CONST_VARIABLE3"// 编译期常量这里声明在object声明
}

fun main() {
//    val add = D0302Filed.Address()
//    // 测试自定义get start
//    println(add.isEmpty)
//    add.size = 0
//    println(add.isEmpty)
//    // 测试自定义get end
//
//    // 测试自定义set start
//    add.stringRepresentation = "AccAbb"
//    println(add.stringRepresentation)
//    // 测试自定义set end

    // 测试延迟初始化
    val filed = D0302Filed()
}

class D0302Filed {
    constructor() {
        // 12 判断延迟初始化与否的变量 isInitialized
        // 此检测方法仅可检测位于同⼀个类型内或者位于其中⼀个外围类型中 或者位于相同⽂件
        // 的顶层的属性
        // 是否调用setup 关系到lateInitString.isInitialized的返回值
        // this.setup()
        if (this::lateInitString.isInitialized) {
            println(this.lateInitString)
        } else {
            println("lateInitString is not initialized")
        }
    }

    companion object {
        const val CONST_VARIABLE2: String = "CONST_VARIABLE2"// 编译期常量这里声明在伴生对象
    }

    class Address {
        // 1 声明属性
        // Kotlin 类中的属性既可以⽤关键字 var 声明为可变的，也可以⽤关键字 val 声明为只读的。
        var name: String = "Holmes, Sherlock"
        var street: String = "Baker"
        var city: String = "London"
        var state: String? = null
        val zip: String = "123456"
        var allByDefault: Int? = 0 // 需要显式初始化器， 隐含默认 getter 和 setter
        var initialized = 1 // 类型 Int、 默认 getter 和 setter

        // 3 Getters 与 Setters
        // 声明⼀个属性的完整语法是
        /*
        var <propertyName>[: <PropertyType>] [= <property_initializer>]
        [<getter>]
        [<setter>]*/
        // 上面中括号里面都是可以省略的部分
        var completeProperty: Int = 0
            get
            set

        // 4 var vs val
        // ⼀个只读属性的语法和⼀个可变的属性的语法有两⽅⾯的不同：1、只读属性的⽤ val 开始代替 var 2、只读属
        // 性不允许 setter
        // 通过将Kotlin反编译为Java 看出 valFiled只有get方法 没有set方法
        var varFiled = 1
        val valFiled = 2

        // 5 自定义get
        var size = 1
        val isEmpty: Boolean
            get() = this.size == 0

        // 6 自定义set
        // 由于可以推断stringRepresentation是String类型 : String其实可以省略
        var stringRepresentation: String = ""
            get() = field // field 在set与get方法内部代表当前属性
            // 照惯例，setter 参数的名称是 value ，但是如果你喜欢你可以选择⼀个不同的名称
            set(value) {
                field = value.uppercase(Locale.getDefault()) // 自定义set
            }

        fun copyAddress(address: Address): Address {
            val result = Address() // Kotlin 中没有“new”关键字
            // 2 访问属性
            result.name = address.name // 将调用访问器
            result.street = address.street
            return result
        }

        // 7 修改set/get可见性或对其注解
        // 如果你需要改变一个访问器的可见性或者对其注解，但是不需要改变默认的实现，你可以定义访问器而不定义其实现:
        var setterVisibility: String = "abc"
            private set // 此 setter 是私有的并且有默认实现, 默认情况下 属性get set是public的
        var setterWithAnnotation: Any? = null
            @Action set // ⽤ Action 注解此 setter

        // 8 幕后字段
        // 就是filed关键字
        // field 标识符只能用在属性的访问器内
        // filed 代表当前属性 Kotlin中属性默认自带get set方法 如果在set get方法中直接使用属性 则会出现循环调用的情况
        // 因此需要filed关键字
        var counter = 0 // 注意： 这个初始器直接为幕后字段赋值
            set(value) {
                if (value >= 0) field = value
            }
            get() = field

        // 9 幕后属性
        // 个人觉得这是一种非常奇怪的写法 用两个变量一起表示一个变量
        // 将以下划线开头的变量声明为私有的 在声明一个public的同名的去掉开头下划线的变量
        // public的变量内部get set操作的是private的变量
        private var _table: Map<String, Int>? = null
        public val table: Map<String, Int>
            get() {
                if (_table == null) {
                    _table = HashMap() // 类型参数已推断出
                }
                return _table ?: throw AssertionError("Set to null by another thread")
            }
    }

    // 11 变量延迟初始化
    // ⼀般地，属性声明为⾮空类型必须在构造函数中初始化。然⽽，这经常不方便。例如：属性可以通过依赖注⼊来初
    // 始化，或者在单元测试的 setup ⽅法中初始化。这种情况下，你不能在构造函数内提供⼀个⾮空初始器。但你
    // 仍然想在类体中引⽤该属性时避免空检测。为处理这种情况，你可以⽤ lateinit 修饰符标记该属性
    lateinit var lateInitString: String
    fun setup() {
        lateInitString = "late"
    }
}