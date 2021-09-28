package com.example.lib.d05others

/**
 * 解构声明
 * 可以理解为批量赋值 常用于数据类和Map等拥有多个属性的类型
 * 目的是方便我们声明和获取其中的属性
 */

fun main() {
    // 1 解构声明在数据类中的应用
    // 普通Java类的获取方式
    val myPerson = generatePerson()
    println(myPerson.name)
    println(myPerson.age)
    println(myPerson.address)
    // 使用解构声明
    // 批量声明与赋值
    val (myName, myAge, myAddress) = generatePerson()
    println(myName)
    println(myAge)
    println(myAddress)
    println("======= 1 end ========")

    // 2 一个函数返回多个结果
    // --使用Pair或Triple
    val triple = generatePerson2()
    println(triple.first)
    println(triple.second)
    println(triple.third)
    // 想要一个return里面返回多个数据 一个是利用解构声明 一个是利用Pair或Triple
    // 但是两者区别还是挺大的 Pair或Triple只是存储了多个结果 但是并没有解构声明容易理解 因为Pair或Triple并没有固定的数据类型
    // 而结构声明返回的数据类型 是我们创建map或者数据类时就制定好了类型的
    // 所以更推荐解构声明
    println("======= 2 end ========")

    // 3 解构声明在集合中的应用
    // map 初始化   to关键字本身是一个中缀表达式 返回一个pair
    // 3.1 利用解构声明遍历map
    val map = mapOf("a" to "aa", "b" to "bb", "c" to "cc") // 类型可以推断出来 所以可以省略
    val map2 = mapOf<String, String>("a" to "aa", "b" to "bb", "c" to "cc")

    for ((myKey, myValue) in map) {
        println("key:$myKey value:$myValue")
    }
    println("----------------------")
    // 3.2 利用mapValues方法修改map的value
    // mapValues:map以<K,V>的类型输入 以<K,R>的形式输出
    // mapValues接受一个函数作为参数 该函数参数为Map.Entry<K,V> 返回R类型的数据
    // mapKeys和mapValues方法差不过 差异在于mapKeys修改的是key而不是value
    map.mapValues { entry ->
        "...value changed to:${entry.key} ${entry.value}"
    }.forEach { println(it) }
    println("----------------------")
    // 3.3 mapValues+解构声明 修改value同时遍历map
    map.mapValues { (_, value) ->
        "$value haha"// 给value加上haha
    }.forEach { println(it) }

    map.mapValues { (key, value) ->
        "$key  $value haha"// 给value加上haha
    }.forEach { println(it) }

    println("======= 3 end ========")

    // 4 解构声明的类型
    // kotlin 允许我们我们为解构声明的整体指定类型 也允许我们给每个元素指定类型
    // 给每个元素指定类型
    map.mapValues { (key: String, value: String) -> "$value haha" }.forEach { println(it) }
    // 给整体指定类型
    map.mapValues { (key, value): Map.Entry<String, String> -> "$value hihi" }
        .forEach { println(it) }
    // 也可以省略
    map.mapValues { (key, value) -> "$value enen" }.forEach { println(it) }

}

// 2 一个函数返回多个结果
fun generatePerson2(): Triple<String, Int, String> {
    return Triple("zhangsan", 25, "shanghai")
}


// 1 解构声明在数据类中的应用
data class Person(val name: String, val age: Int, val address: String)

fun generatePerson(): Person {
    return Person("zhangsan", 25, "shanghai")
}

class D0501DestructuringDeclaration {
}