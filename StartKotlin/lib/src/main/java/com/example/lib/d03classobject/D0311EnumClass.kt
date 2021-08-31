package com.example.lib.d03classobject

fun main() {
    // 1 枚举类的基本用法
    println(Direction.EAST)
    // 每种枚举类型都是一个枚举的类的类型
    println(Direction.EAST is Direction)

    // 2 构造函数带参数的枚举
    println(Color.BLUE)
    println(Color.BLUE.rgb)

    // 3 枚举类型可以定义成员变量与方法
    Color2.BLUE.test()
    Color2.BLUE.property = "property"
    println(Color2.BLUE.property)

    // 4 枚举类实现抽象方法
    println(ProtocolState.TALKING.signal())
    println(ProtocolState.WAITING)

    // 5 在枚举类中实现接口
    println(EnumImplInterface.PLUS)

    // 6 枚举的switch case
    val driver1 = Driver(LicenseStatus.UNQUALIFIED);
    println(driver1.checkLicense())
    val driver2 = Driver(LicenseStatus.LEARNING);
    println(driver2.checkLicense())
}

// 6 枚举的switch case
enum class LicenseStatus {
    UNQUALIFIED,
    LEARNING,
    QUALIFIED;
}

class Driver(var status: LicenseStatus) {
    fun checkLicense(): String {
        return when (status) {
            LicenseStatus.LEARNING -> "在学"
            LicenseStatus.QUALIFIED -> "有资格"
            LicenseStatus.UNQUALIFIED -> "没有资格"
        }
    }
}

// 5 在枚举类中实现接口
interface IAdd {
    fun add(a: Int, b: Int): Int
    fun sub(a: Int, b: Int): Int
}

enum class EnumImplInterface() : IAdd {
    PLUS,
    SUB;

    override fun add(a: Int, b: Int): Int {
        return a + b
    }

    override fun sub(a: Int, b: Int): Int {
        return a - b
    }
}

// 4 枚举类实现抽象方法
enum class ProtocolState {
    WAITING {
        override fun signal() = "WAITING"
    },

    TALKING {
        override fun signal() = "TALKING"
    };

    abstract fun signal(): String
}

// 3 枚举类型可以定义成员变量与方法
enum class Color2() {
    RED,
    GREEN,
    BLUE;

    fun test() {
        println("test")
    }

    var property: String = ""
}

// 2 构造函数带参数的枚举 带有一个属性 rgb
enum class Color(val rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF)
}

// 1 枚举类的基本用法
enum class Direction {
    // Kotlin 种的枚举也是一个类
    NORTH, SOUTH, WEST, EAST
}

class D0311EnumClass 