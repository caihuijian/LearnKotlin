package com.example.lib.d06reflect

import java.io.Serializable
import kotlin.reflect.full.superclasses

/**
 * 通过KClass获取一个类的父类信息
 */

class MySerializable : Serializable, MyInterface
interface MyInterface

fun main() {
    val mySerializable = MySerializable::class
    println(mySerializable.superclasses)
}

class HelloKotlin13 {
}