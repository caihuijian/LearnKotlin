package com.example.lib.d05others

/**
 * Java与Kotlin在数组 上的区别
 * Kotlin中的数组是不变的(相对于协变和逆变) 这一点和Java不同(Java是协变的)
 * 因此 Java的数组设计是有问题的
 * 这意味着 我们无法将Array<String> 赋值给Array<Any> 可以避免运行时异常
 *
 * Kotlin提供了原生类型数组来比卖你自动装修拆箱带来的成本:IntArray,DoubleArray,CharArray...
 */
fun main() {
    // 1 对比Java与Kotlin的数组
//    val objects:Array<Any> = Array<String>(4,{_-> "AA"})//Type mismatch.
    val objects: Array<Any> = Array<Any>(4, { _ -> "AA" })//Type mismatch.
    objects.forEach { println(it) }

    // Array相当于Java里面的ArrayList<xx>
    // 而IntArray DoubleArray CharArray等相当于Java里面的int [], double[], char[]等等
    // An array of ints. When targeting the JVM, instances of this class are represented as int[].
    val intArr = IntArray(4) { 0 }
    println("-------1 end-------")

    // 2 调用Java的方法
    val intArr2 = intArrayOf(1, 2, 3, 4)
    val myArray = MyArray()
    // 注意这里的需求类型是平台参数类型 args : IntArray!
    myArray.myArrayMethod(intArr2)
    println("-------2 end-------")

    // 3 使用下标访问数组更快捷
    val array = arrayOf(1, 2, 3, 4, 5)
    array[0] = array[0] * 2 // 使用下标访问数组 不会调用数组的get set方法 使得访问更快捷
    array.forEach { println(it) }
    println("-------3 end-------")

    // 4 调用Java的可变参数方法
    val myVarargs = MyArray()
    val stringArray = arrayOf("hello", "world", "hello world")
    myVarargs.myArrayMethod2(*stringArray)
}

class D0508KotlinCallJava2 {

}