package com.example.lib.d03classobject

fun main() {
    // 测试接口的覆盖
    val myChild = D0303Interface.Child()
    println(myChild.prop)
    println(myChild.propertyWithImplementation)
}

class D0303Interface {
    // 2 如何实现接口
    class Child : MyInterface {
        // 覆盖接口中的prop值
        override val prop: Int = 1

        // 覆盖接口中的propertyWithImplementation值
        // 如果不覆盖 则其值为foo
        override val propertyWithImplementation: String = "child string"


        // 该方法必须实现 因为MyInterface没有具体实现
        override fun bar() {
            // ⽅法体
        }

        // 该方法可以不覆盖 因为接口已经有实现
        override fun foo() {
            super.foo()
        }
    }

}

// 1 如何定义接口
interface MyInterface {
    // 3 接口中的属性
    // 你可以在接⼝中定义属性。在接⼝中声明的属性要么是抽象的，要么提供访问器的实现。在接⼝中声明的属性不
    // 能有幕后字段（backing Meld），因此接⼝中声明的访问器不能引⽤它们
    val prop: Int // 抽象属性 没有具体赋值 子类必须实现该属性(给他具体初始值)
    val propertyWithImplementation: String // 非抽象属性 默认值为foo
        get() = "foo"

    fun bar()
    fun foo() {
        // 可选的⽅法体
        // 与Java不同的是 Kotlin的接口可以有具体的实现
        println("foo")
    }
}

// 4 接口继承接口
// 一个接⼝可以从其他接⼝派⽣，从⽽既提供基类型成员的实现也声明新的函数与属性。很⾃然地，实现这样接⼝
// 的类只需定义所缺少的实现
interface Named {
    val name: String
}

interface Person : Named {
    //接口Person继承接口Named
    val firstName: String
    val lastName: String

    // 实现继承自Named的属性的get方法 返回firstName+lastName
    override val name: String get() = "$firstName $lastName"
}

// 数据类继承自Person 因为Person内部已经override了name 所以Employee不一定需要重写name属性
// 关于data class 后面再讲
data class Employee(
        // 不必实现“name”
        override val firstName: String,
        override val lastName: String,
) : Person


// 5 解决接口上的覆盖冲突
// 实现多个接⼝时，可能会遇到同⼀⽅法继承多个实现的问题
interface A {
    fun foo() {
        print("fooA")
    }

    fun bar()// 默认是抽象方法
}

interface B {
    fun foo() {
        print("fooB")
    }

    fun bar() {
        print("barB")
    }
}

class C : A {
    // 抽象方法必须重写
    override fun bar() {
        print("barC")
    }
}

class D : A, B {
    // 这里的继承规则在类与对象-类与继承第16点 中也讲过
    // 不管是继承接口 还是继承类 调用父类/父接口的方式不变
    override fun foo() {
        super<A>.foo()
        super<B>.foo()
    }

    override fun bar() {
        super<B>.bar()
    }
}