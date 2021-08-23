package com.example.lib.d03classobject

import java.io.File

// 1.类的声明
// 类声明由类名、类头（指定其类型参数、主构造函数等）以及由花括号包围的类体构成。类头与类体都是可选的；
// 如果⼀个类没有类体，可以省略花括号。
// 其中constructor(parameter: String)属于类头 没有必要的时候可以省略
// {}里面的属于类体 类体中没有内容的时候{}也可以省略

// 2 构造函数
// 在 Kotlin 中的⼀个类可以有⼀个主构造函数以及⼀个或多个次构造函数。主构造函数是类头的⼀部分：它跟在
// 类名（与可选的类型参数）后
// 如果主构造函数没有任何注解或者可⻅性修饰符，可以省略这个 constructor 关键字。如下
// class D0301ClassExtends (parameter: String) {
//
// }
fun main() {
//    // 7 创建类的实例
//    // 注意 Kotlin 并没有 new 关键字
//    val newObj = D0301ClassExtends("param1", "param1")
//    val newObj1 = D0301ClassExtends("param1")
//    val myCircle = D0301ClassExtends.MyCircle()
//    println(myCircle.overrideVal)
//
//    // 测试派生类的初始化顺序
//    var derived = D0301ClassExtends.Derived("name", "lastName", "thirdName")
//    // 测试调⽤超类实现
//    val myCircle = D0301ClassExtends.FilledRectangle()
//    myCircle.draw()
//    println(myCircle.borderColor)

    val rectangle2 = D0301ClassExtends.FilledRectangle2().Filler()
    rectangle2.drawAndFill()

    // 测试伴生对象
    D0301ClassExtends.CompanionObject.load()
    D0301ClassExtends.CompanionObject.printStr()
}

/**
 * 8 类的组成部分
 * a构造函数与初始化块
 * b函数
 * c属性
 * d嵌套类与内部类
 * 对象声明 （暂不展开 后面会说）
 */
// 如果构造函数有注解或可⻅性修饰符，这个 constructor 关键字是必需的，并且这些修饰符在它前⾯
// Kotlin的超级父类"Object"
// 在 Kotlin没有超类Object 它的所有类都有⼀个共同的超类 Any ，这对于没有超类型声明的类是默认超类
class D0301ClassExtends public constructor(parameter: String) {
    // // 从 Any 隐式继承
    // 3 次构造函数
    // 其constructor不可以省略
    // 如果类有⼀个主构造函数，每个次构造函数需要委托给主构造函数，可以直接委托或者通过别的次构造函数间
    // 接委托。委托到同⼀个类的另⼀个构造函数⽤ this 关键字即可
    // 4 init 初始化模块
    // 主构造函数不能包含任何的代码。初始化的代码可以放到以 init 关键字作为前缀的初始化块（initializer
    // blocks）中。
    // 直接委托给主构造方法
    // 5 初始化顺序
    // 参见D0301ClassExtendsInitOrderDemo和D0301ClassExtendsInitOrderDemo2
    // 6 构造方法默认可见性
    // 如果⼀个⾮抽象类没有声明任何（主或次）构造函数，它会有⼀个⽣成的不带参数的主构造函数。构造函数的可
    // ⻅性是 public。如果你不希望你的类有⼀个公有构造函数，你需要声明⼀个带有⾮默认可⻅性的空的主构造函
    // 数
    constructor(parameter: String, parameter2: String) : this(parameter)

    // 间接委托给主构造方法
    constructor(parameter: String, parameter2: String, parameter3: String) : this(parameter, parameter2)

    // 声明一个类及其属性
    // 默认情况下，Kotlin 类是最终（final）的：它们不能被继承。要使⼀个类可继承，请⽤ open 关键字标记它
    open class Person(
            // 与普通属性⼀样，主构造函数中声明的属性可以是可变的（var）或只读的（val）
            val firstName: String,
            val lastName: String,
            var age: Int, // trailing comma
    ) { /*...*/ }

    // 9 如何声明超类
    // 在类头中把超类型放到冒号之后
    class Student(firstName: String, lastName: String, age: Int) : Person(firstName, lastName, age)

    // Any 有三个⽅法：equals() 、hashCode() 与 toString() 。因此，为所有 Kotlin 类都定义了这些⽅法。
    // 从这里也可证明任何类的基类是Any
    // 默认情况下，Kotlin 类是最终（final）的：它们不能被继承。要使⼀个类可继承，请⽤ open 关键字标记它
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    // 10 父类与子类构造函数之间的关系
    // 参见D0301Extends D0301ExtendsChild1 D0301ExtendsChild2
    // 11 覆盖方法
    open class Shape {
        // 如果方法是open的 那么这个类必须是open的
        // 否则出现提示 'open' has no effect in a final class

        // 12 覆盖属性
        // 属性覆盖与⽅法覆盖类似；在超类中声明然后在派⽣类中重新声明的属性必须以 override 开头，并且它们必
        // 须具有兼容的类型。每个声明的属性可以由具有初始化器的属性或者具有 get ⽅法的属性覆盖。
        // 如果父类属性是var的 子类想要覆盖父类属性只能声明为var
        // 如果父类属性是val的 子类想要覆盖父类属性可以声明为var 或 val
        // 因为⼀个 val 属性本质上声明了⼀个 get ⽅法，⽽将其覆盖为 var 只是在⼦类中额外声明⼀个 set ⽅法
        // 因此 如果父类声明属性是val的意味着父类中无法修改该变量 但是如果在子类用var覆盖了该属性 则子类可以任意修改其值
        // 反过来 如果父类属性是var的 那么他的该属性有set和get方法
        // Kotlin属性覆盖和Java差别还是挺大的 需要注意

        // 属性覆盖必须open
        open val vertexCount: Int = 0
        open val overrideVal: Int = 0

        open fun draw() {
            /*……*/
        }

        fun fill() {
            /*……*/
        }
    }

    open class Circle() : Shape() {
        // 覆盖父类属性 override有两层含义 1 覆盖 2 open
        override val vertexCount = 4

        // 可以用var或者val覆盖父类里面的val属性
        override var overrideVal: Int = 3

        // 子类想要覆盖父类方法
        // 条件1 父类的该方法是open的
        // 条件2 加上override关键字
        // 标记为 override 的方法本⾝是开放的，也就是说，它可以在它的⼦类中覆盖。如果你想禁⽌再次覆盖，使⽤ final
        // 关键字 即override隐含的表示了open关键字
        final override fun draw() { /*……*/
        }
    }

    class MyCircle : Circle() {
        // 不能用val属性覆盖父类的var属性 报错：Val-property cannot override var-property
        override var overrideVal: Int = 100

        // 报错 'draw' in 'Circle' is final and cannot be overridden
        /*
        override fun draw() {
            super.draw()
        }*/
    }

    // 13 派生类的初始化顺序
    // 在构造派⽣类的新实例的过程中，第⼀步完成其基类的初始化（在之前只有对基类构造函数参数的求值），因此
    // 发⽣在派⽣类的初始化逻辑运⾏之前。
    // 具体调用顺序 父类构造方法-父类init块-父类属性初始化-子类初始化块-子类属性初始化
    // 这调用顺序意味着，基类构造函数执⾏时，派⽣类中声明或覆盖的属性都还没有初始化。如果在基类初始化逻辑中
    // （直接或通过另⼀个覆盖的 open 成员的实现间接）使⽤了任何⼀个这种属性，那么都可能导致不正确的⾏为或运⾏
    // 时故障。设计⼀个基类时，应该避免在构造函数、属性初始化器以及 init 块中使⽤ open 成员
    open class Base(name: String) {
        init {
            println("Initializing Base")
        }

        open val size: Int = name.length.also { println("Initializing size in Base: $it") }
    }

    class Derived(
            name: String,
            lastName: String,
    ) : Base(name.also { println("Argument for Base: $it") }) {

        constructor(name: String,
                    lastName: String, thirdName: String) : this(name, lastName) {
            println("second constructor")
        }

        init {
            println("Initializing Derived")
        }

        override val size: Int =
                (super.size + lastName.length).also { println("Initializing size in Derived: $it") }
    }

    // 14 调⽤超类实现
    // 派⽣类中的代码可以使⽤ super 关键字调⽤其超类的函数与属性访问器的实现
    open class Rectangle {
        open fun draw() {
            println("Rectangle Drawing a rectangle")
        }

        val borderColor: String get() = "black"
    }

    class FilledRectangle : Rectangle() {
        override fun draw() {
            // 调用父类draw方法
            super.draw()
            println("Filling the rectangle")
        }

        // 调用父类属性
        val fillColor: String get() = super.borderColor
    }

    // 15 内部类中访问外部类的父类的属性与方法
    // 在⼀个内部类中访问外部类的父类，可以通过由外部类名限定的 super 关键字来实现：super@Outer ：
    class FilledRectangle2 : Rectangle() {
        override fun draw() {
            println("FilledRectangle2 draw")
            val filler = Filler()
            filler.drawAndFill()
        }

        inner class Filler {
            private fun fill() {
                println("Filler Filling")
            }

            fun drawAndFill() {
                println("start Filler drawAndFill")
                super@FilledRectangle2.draw() // 调⽤ Rectangle 的 draw() 实现
                fill()
                println("Drawn a filled rectangle with color ${super@FilledRectangle2.borderColor}")
                // 使⽤ Rectangle 所实现的 borderColor 的 get()
            }
        }
    }

    // 16 覆盖规则
    // 在Kotlin 中，实现继承由下述规则规定：如果⼀个类从它的直接超类继承相同成员的多个实现，它必须覆盖这
    // 个成员并提供其⾃⼰的实现（也许⽤继承来的其中之⼀）。为了表⽰采⽤从哪个超类型继承的实现，我们使⽤由
    // 尖括号中超类型名限定的 super，如 super<Base> ：
    // 主要意思应该是 继承/实现父类/接口的同名同参的方法 在子类中可以调用具体的父类或接口的具体方法 这点和Java略有区别
    open class Rectangle2 {
        open fun draw() { /* …… */
        }
    }

    interface Polygon {
        fun draw() { /* …… */
        } // 接⼝成员默认就是“open”的
    }

    class Square() : Rectangle2(), Polygon {
        // 编译器要求覆盖 draw()：
        override fun draw() {
            // 调用指定父类的方法
            super<Rectangle2>.draw() // 调⽤ Rectangle2.draw()
            super<Polygon>.draw() // 调⽤ Polygon.draw()
        }
    }

    // 17 抽象类
    // 类以及其中的某些成员可以声明为 abstract。抽象成员在本类中可以不用实现。需要注意的是，我们并不需
    // 要⽤ open 标注一个抽象类或者函数——因为这不⾔⽽喻。
    // 我们可以⽤⼀个抽象成员覆盖⼀个⾮抽象的开放成员
    open class Polygon3 {
        open fun draw() {
            println("Polygon3 draw")
        }

        open fun draw1() {
            println("Polygon3 draw1")
        }
    }

    // 没有指明是open的抽象类
    abstract class Rectangle3 : Polygon3() {
        // 可以选择覆盖父类的具体方法
        override fun draw() {
            println("Rectangle3 draw")
        }

        // 也可选择将父类的具体方法改成抽象方法
        abstract override fun draw1()
    }

    // 18 对象
    // 类似于Java中的static 但是Kotlin中的伴生对象还是有所不同
    // 伴生对象每个类仅能有一个 且只会初始化一次
    // 伴生对象的主要作用就是为其所在的外部类模拟静态成员
    open class CompanionObject {
        companion object {
            // 相当于静态变量
            private const val PATH = "ABC"// 测试代码 不要运行 会找不到文件
            // 相当于静态方法
            fun load() = File(PATH).readBytes()
            fun printStr() = run {
                println("this is printStr")
            }
        }

        fun printStr() {
            println("this is normal printStr")
        }
    }

}
