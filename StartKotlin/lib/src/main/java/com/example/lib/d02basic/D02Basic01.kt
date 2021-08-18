package com.example.lib.d02basic

/**
 * 数据类型
 * Java中char可以与数字进行比较 但是Kotlin中不行
 * char a = 'a';
 * if (a>10) System.out.println(a);
 *
 *
 */

// 十进制
const val NUMBER1 = 123

// 16进制的17
const val NUMBER2 = 0x11

// 2进制的5
const val NUMBER3 = 0b0101
// Kotlin 不支持八进制


fun main() {
//    // 基本类型
//    basicType()
//    // 字面常量
//    valVariable()
//    // 显示转换
//    explicitConvert()
//    // 运算
//    calculate()
//    // 数字比较
//    numberCompare()
//    // 字符
//    char()
//    // 布尔
//    bool();
    // 数组
    array()
}

fun array() {
    TODO("Not yet implemented")
}

fun bool() {
    val a = true
    val b = false
    println(a || b)
    println(a && b)
    println(!a)
}

fun char() {
    /*val a:Char = 'a'
    if (a == 1){ //Operator '==' cannot be applied to 'Char' and 'Int'

    }*/
    val b: Char = '1'
    println(b)

    //\t转义制表符
    //\b转义退格键
    //\n转义换行
    //\r转义回车符 不过没看懂效果。。
    //\'转义单引号
    //\"转义双引号
    //\\转义斜杠
    //\$转义美元符号
    val c: String = "aa \t bb\b cc\n dddddd \r ee \' ff \" gg\\ hh\$ "
    println(c)

    val d: Char = '5'
    println(decimalDigitValue(d))

    val e: Char = 'e'
    println(isSmallChar(e))

    val f: Char = 'F'
    println(isBigChar(f))
}

fun decimalDigitValue(c: Char): Int {
    if (c !in '0'..'9') {
        throw IllegalArgumentException("Out of range")
    }
    return c.code - '0'.code // 利用数字在Unicode字符表里面的index位置 显式将char转换为数字
}

fun isSmallChar(c: Char): Boolean {
    return c in 'a'..'z'
}

fun isBigChar(c: Char): Boolean {
    return c in 'A'..'Z'
}

fun numberCompare() {
    val a = 1.0
    val b = 2.0
    val c = 3.0
    println(a == b)
    println(a + b == c)
    println(a != b)
    println(a < b)
    println(a > b)
    println(a <= b)
    println(a >= b)
    // 上述几个比较操作符是所有数字类型通用的
    val e = 5
    println(e in 1..10)
    println(e !in 1..10)
    // in !in是区间判断符号 只能用在Int或者char数值上
}

fun calculate() {
    val x = 5 / 2 // 与Java一样 整数相除还是整数 因此x是Int类型
    // println(x == 2.5) Operator '==' cannot be applied to 'Int' and 'Double'
    println(x == 2) // true

    val y = 5L / 2 // Long 与 Int运算 得到Long类型
    println(y == 2L)

    val u = 5 / 2.0 // 自动推断类型为Double
    println(u == 2.5)

    val w = 5 / 2.toDouble() // 显示转换为Double
    println(w == 2.5)

    val v = 5 / 2f // 显示转换为float
    println(v == 2.5f)

    // 位运算 对1 有符号左移2位 与上16进制的
    val b = 0b1100
    println(b)
    // 1 shl 2 = 0100
    // 1100 & 0100 == 0100
    val a = (1 shl 2) and b
    println(a)

    /**
    其他位运算
    shl(bits) – 有符号左移
    shr(bits) – 有符号右移
    ushr(bits) – ⽆符号右移
    and(bits) – 位与
    or(bits) – 位或
    xor(bits) – 位异或
    inv() – 位⾮
     */
}

fun explicitConvert() {
    /*
    val a: Int? = 1 // ⼀个装箱的 Int (java.lang.Integer)
    val b: Long? = a // 编译出错 无法将Int的对象赋值给Long

    val c: Long? = 1L // ⼀个装箱的 Long (java.lang.Long)
    val d: Int? = c // 编译出错 无法将Long的对象赋值给Int
    */
    /*
    以下为Java代码
    上面的Kotlin代码应该和当前的第一部分的Java Code类似
    Integer a = 1; // ⼀个装箱的 Int (java.lang.Integer)
    Long b = a; // 编译出错 无法将Int的对象赋值给Long

    Long c= 1L; // ⼀个装箱的 Long (java.lang.Long)
    Integer d= c; // 编译出错 无法将Long的对象赋值给Int

    int a = 1; // ⼀个装箱的 Int (java.lang.Integer)
    long b = a; // 可以编译

    long c= 1L; // ⼀个装箱的 Long (java.lang.Long)
    int d= c; // 编译出错 不能将long赋值给int
    */

    val b: Byte = 1 // OK, 字⾯值是静态检测的
    // val i: Int = b // 错误 较⼩的类型不能隐式转换为较⼤的类型
    val i: Int = b.toInt() // OK： 显式拓宽
    println(i)
    /**
    每个数字类型⽀持如下的转换:
    toByte(): Byte
    toShort(): Short
    toInt(): Int
    toLong(): Long
    toFloat(): Float
    toDouble(): Double
    toChar(): Char
     */
    // 虽然赋值和参数使用上没有隐式转换 但是算术运算会有重载做适当转换
    val l = 1L + 3 // Long + Int => Long 不会出错
    println(l)
}

fun valVariable() {
    // 数字字⾯值中的下划线（⾃ 1.1 起）
    val oneMillion = 1_000_000
    val creditCardNumber = 1234_5678_9012_3456L
    val socialSecurityNumber = 999_99_9999L
    val hexBytes = 0xFF_EC_DE_5E
    val bytes = 0b11010010_01101001_10010100_10010010

    // 定义一个Int值 为100
    val a: Int = -128
    // 定义一个可空的Int值 将a赋值给它
    val boxedA: Int? = a
    // 定义另一个可空的Int值 将a赋值给它
    val anotherBoxedA: Int? = a
    // 定义一个Int值 为10000
    val b: Int = 10000
    // 定义一个可空的Int值 将b赋值给它
    val boxedB: Int? = b
    // 定义另一个可空的Int值 将b赋值给它
    val anotherBoxedB: Int? = b
    // 这里涉及Kotlin中的==和===的差别
    // ==相当于java的equals
    // ===相当于java的== 是地址比较
    // 至于第一次输出是true 第二次是false 是因为Kotlin和Java一样 在内部保存了一些常量
    // 如果新创建的Integer 属于区间[-128,127] 则会从常量池取出数据赋值
    // 否则new一个新的Integer
    // 具体可以参照https://blog.csdn.net/jdsjlzx/article/details/106350713
    // 书里面的同⼀性应该指 地址比较相同
    // 相等性指他们的equals方法返回的结果
    println(boxedA === anotherBoxedA) // true
    println(boxedB === anotherBoxedB) // false

    println(boxedA == anotherBoxedA) // true
    println(boxedB == anotherBoxedB) // true
}

fun basicType() {
    // 未超出 Int 最⼤值的整型值初始化的变量都会推断为 Int 类型
    val one = 1 // Int
    // 如果初始值超过了其最⼤值，那么推断为 Long 类型
    val threeBillion = 3000000000 // Long
    // 显式指定 Long 型值
    val oneLong = 1L // Long
    // 显式指定 Byte 型值
    val oneByte: Byte = 1

    // 以⼩数初始化的变量，编译器会推断为 Double 类型
    val e = 2.7182818284 // Double
    // 显式指定为 Float 类型
    val eFloat = 2.7182818284f // Float

    val eee = 2.7182818284f// 实际是Float 不会转换为double //Java中这么写 实际值是double类型 double myD = 2.7182818284f;

    fun printDouble(d: Double) {
        print(d)
    }

    // Kotlin 中的数字没有隐式拓宽转换
    val i = 1 // Int 类型
    val d = 1.1 // double 类型
    val f = 1.1f // float 类型
    printDouble(d)
    // printDouble(i) // 错误： 类型不匹配
    // printDouble(f) // 错误： 类型不匹配
}

class D02Basic01 {

}


