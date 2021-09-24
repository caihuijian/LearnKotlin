package com.example.lib.d06reflect

/**
 * 通过KClass获取泛型类型参数
 */

class MyTestClass<K, V> {
    val k: K? = null
    val v: V? = null
}

fun main() {
    // 获取KClass对象
    val myTestClassType = MyTestClass::class
    // 获取泛型的类型集合
    println(myTestClassType.typeParameters)
    println(myTestClassType.typeParameters.size)
    println("first property type " + myTestClassType.typeParameters[0])
    println("second property type " + myTestClassType.typeParameters[1])
}

class HelloKotlin12 {
}