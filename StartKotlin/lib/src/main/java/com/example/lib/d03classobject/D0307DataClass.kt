package com.example.lib.d03classobject

// 1 数据类
// 只保存数据的类。
// 一些标准函数往往是从数据机械推导而来的。在 Kotlin 中，这叫做 数据类 并标记为 data

// 2 数据类的组成部分
// equals()/hashCode() 对；
// toString() 格式是 "User(name=John, age=42)"；
// componentN() 函数 按声明顺序对应于所有属性；
// copy() 函数

// 3 数据类的限定条件
// 主构造函数需要至少有一个参数；
// 主构造函数的所有参数需要标记为 val 或 var；(var 与val的区别在于能不能给属性重新赋值)
// 数据类不能是抽象、开放、密封或者内部的；
// （在1.1之前）数据类只能实现接口。TODO
// 相同空间内不能定义同名的数据类 即使参数不同

fun main() {
    val user1 = D0307DataClass.User("zhangsan", 25)
    println(user1)
    val user2 = User("lisi")
    println(user2.name)
    user2.name = "AA"
}

// 定义在顶层的数据类
data /*不能是 open sealed abstract inner*/  class User(var name: String)

class D0307DataClass {
    // 定义在类中的数据类
    data class User(val name: String, val age: Int)
}