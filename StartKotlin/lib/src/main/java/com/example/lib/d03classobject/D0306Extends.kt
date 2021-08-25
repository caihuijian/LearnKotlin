package com.example.lib.d03classobject

import java.util.ArrayList

// 1 扩展函数与扩展方法
// 你可以为⼀个你不能修改的、来⾃第三⽅库中的类编写⼀个新的函数。这个新增的函数就像那
// 个原始类本来就有的函数⼀样，可以⽤普通的⽅法调⽤。这种机制称为 扩展函数 。此外，也有 扩展属性 ，允许
// 你为⼀个已经存在的类添加新的属性。

fun main() {
    // 2 测试扩展函数
//    val demo = D0306Extends<Int>()
//    demo.add(1)
//    demo.add(10)
//    demo.printItems()

    printClassName(Rectangle())

}

// 2 扩展函数
fun D0306Extends<Int>.add(number: Int) {
    this.arr.add(number)
}

class D0306Extends<T>() {
    var arr: MutableList<T> = ArrayList()

    fun printItems() {
        for (item in arr) {
            println(item)
        }
    }
}

// 3 扩展是静态解析的
// 扩展不能真正的修改他们所扩展的类。通过定义⼀个扩展，你并没有在⼀个类中插⼊新成员，仅仅是可以通过该
// 类型的变量⽤点表达式去调⽤这个新函数


open class Shape
class Rectangle : Shape()

fun Shape.getName() = "Shape"
fun Rectangle.getName() = "Rectangle"
fun printClassName(s: Shape) {
    println(s.getName())
}


