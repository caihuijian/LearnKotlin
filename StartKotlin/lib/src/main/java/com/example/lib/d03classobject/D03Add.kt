package com.example.lib.d03classobject

/**
 * 1 在Kotlin中如果有主构造函数 次构造函数必须通过this关键字调用主构造方法
 * 或者通过this关键字调用其他次构造方法再间接调用主构造方法
 */
class SingleClass(name: String) {
    constructor(name: String, age: Int) : this(name)

    constructor(name: String, age: Int, address: String) : this(name, age)
}

/**
 * 2 SingleClass2后面有括号代表有一个无参的主构造方法
 * 如果没有括号则代表没有主构造方法
 */
class SingleClass2() {
    constructor(name: String, age: Int) : this()

    constructor(name: String, age: Int, address: String) : this(name, age)
}

open class Parent2 {
    constructor(name: String)
}


class Child1(name: String) : Parent2(name) {
}

/**
 * 3 如果一个类继承了另外一个类 且没有主构造方法 那么这个类的次构造方法必须用super关键直接或通过其他次构造方法间接调用父类的构造方法
 */
class Child2 : Parent2 {
    // 直接调用
    constructor(name: String) : super(name)

    // 间接调用
    constructor(name: String, age: Int) : this(name)
}

fun main() {

}

class D03Add {
}