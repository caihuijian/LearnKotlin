package com.example.lib.d05others

/**
 * 可变集合与不可变集合
 *
 * kotlin 严格区分可变集合和不可变集合
 * 重点是区分真正的不可变集合与可变集合的只读视图
 * 在kotlin中 mutable修饰的集合是可变的 没有mutable修饰的则是不可变的
 * 例如 MutableList创建的是可变集合 List创建的是不可变集合
 *
 * List继承自Collection Collection又继承自Iterable 因此它只有访问的方法
 * MutableList除了继承自 List 还继承自MutableCollection MutableCollection则提供了一系列add remove等修改list内容的方法
 * 只读视图虽然本身无法修改内容 但是如果它的原引用内容变化 视图里面的内容也发生变化
 */
fun main() {
    // 1 只读视图demo
    // 创建一个可变集合
    val stringList: MutableList<String> = mutableListOf("hello", "world", "hello world")
    // 创建一个可变集合的只读视图
    val readOnlyView: List<String> = stringList
    println(stringList)
    stringList.add("welcome")
    println(stringList)
    // 因为readOnlyView是stringList的只读视图 因此stringList发生了变化 readOnlyView内容自然变化
    // 但是readOnlyView自身无法调用修改内容的接口
    println(readOnlyView)
    // readOnlyView.clear//报错 无法调用修改的接口
    println("=========== 1 end ===============")

    // 2 set的创建与遍历
    val mySet: HashSet<String> = hashSetOf("a", "b", "c", "c")
    mySet.forEach { println(it) }
    println("=========== 2 end ===============")
    // 3 只读列表是协变的(out)
    // 原因是 它只用于从集合中取出数据 是生产者 而不会消费数据(更改内容)
    val myString: List<String> = listOf("a", "b")
    val s2: List<Any> = myString // List<String> 好像赋值给了 List<Any> 子类型赋值给父类型---协变
    println("=========== 3 end ===============")
    // 4 快照 snapshot
    val origin = mutableListOf("a", "b", "c")
    val snapshot = origin.toList()// toList 是一个Iterable的扩展方法 只是赋值集合中的元素 返回的是一个不可变的list
    origin.add("d")
    println(origin)
    println(snapshot)
    // snapshot.clear()// 报错
    println("=========== 4 end ===============")
}

class D0502Collection {
}