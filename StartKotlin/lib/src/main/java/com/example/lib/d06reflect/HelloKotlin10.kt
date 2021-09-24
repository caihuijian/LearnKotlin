package com.example.lib.d06reflect

/**
 * 获取特定对象实例的属性和方法
 */

fun main() {
    // 通过特定对象实例 获取该实例的实例方法
    val str = "abc"
    val getReference = str::get
    println(getReference(1))

    // 通过特定对象实例 获取该实例的属性
    val myProp = "test"::length
    println(myProp.get())

    // 通过Class（而非实例） 获取该类型的属性 但是使用时还是需要传入特定的实例对象
    val myProp2 = String::length
    println(myProp2.get("test"))
    // 通过Class（而非实例） 获取该类型的方法 但是使用时还是需要传入特定的实例对象
    val getReference2 = String::get
    // 第一个参数是实例对象 第二个参数是实例方法的参数
    println(getReference2("abc", 1))
}

class HelloKotlin10 {
}