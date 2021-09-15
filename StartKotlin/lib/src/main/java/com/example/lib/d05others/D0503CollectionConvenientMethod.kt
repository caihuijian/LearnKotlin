package com.example.lib.d05others
// 集合的便利方法

fun main() {
    // 1 List的便利方法
    val item = listOf(1, 2, 3, 4)
    // 取出列表第一个元素
    println(item.first())
    // 取出列表最后一个元素
    println(item.last())
    // Returns the first element, or null if the list is empty.
    println(item.firstOrNull())
    println(item.lastOrNull())

    // 传入lambda表达式过滤列表
    item.filter { it % 2 == 0 }.forEach { println(it) }
    println("--------------")

    val myList = mutableListOf(1, 2, 3)
    // none方法传入一个lambda表达式 如果表达式成立 none返回true 否则返回false
    if (myList.none { it > 10 }) {
        println("没有大于十的元素")
    } else {
        println("存在>10的元素")
    }
    // requireNoNulls 如果myList中没有null 返回List 否则抛出IllegalArgumentException异常
    println(myList.requireNoNulls())
    println("======== 1 end ========")

    // 2 map demo
    val myMap = hashMapOf("hello" to 1, "word" to 2)
    // 通过下标访问Map
    println(myMap["hello"])

    val myMap2: Map<String, Int> = HashMap(myMap) // myMap2是myMap元素的拷贝 且无法修改
    println(myMap2)
    // 通过下标增加map的元素
    myMap["hi"] = 3
    println(myMap)
    println(myMap2)
    // myMap2["hi"] = 3 // myMap2是只读的
}

class D0503CollectionConvenientMethod {

}