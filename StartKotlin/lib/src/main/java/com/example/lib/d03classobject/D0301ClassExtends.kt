package com.example.lib.d03classobject
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
    // 7 创建类的实例
    // 注意 Kotlin 并没有 new 关键字
    val newObj = D0301ClassExtends("param1", "param1")
    val newObj1 = D0301ClassExtends("param1")
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
class D0301ClassExtends public constructor(parameter: String) {// // 从 Any 隐式继承
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


    open class View(){
        constructor(ctx: String) : this()
        constructor(ctx: String,attrs:String) : this(ctx)
    }

    // 如果派⽣类有⼀个主构造函数，其基类可以（并且必须）⽤派⽣类主构造函数的参数就地初始化。
    class MyView1 : View {
        constructor(ctx: String) : super(ctx)
        constructor(ctx: String, attrs: String) : super(ctx, attrs)
    }

    class MyView2 : View {
        constructor(ctx: String) : super(ctx)
        constructor(ctx: String, attrs: String) : super(ctx, attrs)
    }
    // 如果派⽣类没有主构造函数，那么每个次构造函数必须使⽤ super 关键字初始化其基类型，或委托给另⼀个构
    // 造函数做到这⼀点。注意，在这种情况下，不同的次构造函数可以调⽤基类型的不同的构造函数：

    // Any 有三个⽅法：equals() 、hashCode() 与 toString() 。因此，为所有 Kotlin 类都定义了这些⽅法。
    // 从这里也可证明任何类的基类是Any
    // 默认情况下，Kotlin 类是最终（final）的：它们不能被继承。要使⼀个类可继承，请⽤ open 关键字标记它
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}
