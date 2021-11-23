package com.example.lib.d07scope.function

import kotlin.random.Random

data class Person(var name: String, var age: Int = 0, var city: String = "")

// let、run、with、apply 以及 also的主要作用是在对象的上下文中执行代码块
// 当对一个对象调用这样的函数并提供一个 lambda 表达式时，它会形成一个临时作用域。在此作用域中，可以访问该对象而无需其名称。这些函数称为作用域函数
fun main() {

    /**
    1.let、run、with、apply 以及 also的不同点 1
    let、run、with、apply 以及 also的第一个不同点：引用上下文对象的方式

    run、with 以及 apply 通过关键字 this 引用上下文对象,在它们的 lambda 表达式中可以像在普通的类函数中一样访问上下文对象.
    let 及 also 将上下文对象作为 lambda 表达式参数
     */

    val str = "Hello"
    // run demo
    str.run {
        println("The receiver string length: $length")
        println("The receiver string length: ${this.length}") // 和上句效果相同
        println("The receiver string length: ${str.length}") // 和上句效果相同
    }

    // apply demo
    val adam = Person("Adam")
    adam.apply {
        age = 20
        this.age = 20
        adam.age = 20
        city = "London"
    }
    println(adam)

    // with dome
    with(adam) {
        adam.age = 10
        this.age = 20
        city = "Paris"
        name = "Admin"
        println(adam)
    }

    println("---------------------------")

    // let demo
    str.let {
        println("str.let string's length is ${it.length}")
        println("str.let string's length is ${str.length}")
    }
    // it 的优势：可以随意取参数名
    str.let { abc ->
        println("str.let2 string's length is ${abc.length}")
    }
    println("string's length is ${str.length}")

    str.also {
        println("str.also string's length is ${it.length}")
        println("str.also string's length is ${str.length}")
    }
    str.also { abc ->
        println("str.also2 string's length is ${abc.length}")
    }
    println("=============================")
    /**
     * 2.let、run、with、apply 以及 also的不同点 2
     *
     * apply 及 also 返回上下文对象。
     * let、run 及 with 返回 lambda 表达式结果.
     */

    // 2.1 返回上下文对象
    // apply 及 also 的返回值是上下文对象本身。因此，它们可以作为辅助步骤包含在调用链中：你可以继续在同一个对象上进行链式函数调用。
    val numberList = mutableListOf<Double>()
    numberList.also { println("Populating the list") }
        .apply {
            add(2.71)
            add(3.14)
            add(1.0)
        }
        .also { println("Sorting the list") }
        .apply {
            add(2.0)
            add(3.45)
        }
        .also {
            println("End")
        }
    println(numberList)
    println("---------------")

    // 因为apply 及 also 的返回值是上下文对象本身 它们还可以用在返回上下文对象的函数的 return 语句中
    fun getRandomInt(): Int {
        return Random.nextInt(100).also {
            writeToLog("getRandomInt() generated value $it")
        }
    }

    val i = getRandomInt()
    println(i)
    println("--------------")

    // 2.2 Lambda 表达式结果
    // let、run 及 with 返回 lambda 表达式的结果。所以，在需要使用其结果给一个变量赋值，
    // 或者在需要对其结果进行链式操作等情况下，可以使用它们。
    val numbers = mutableListOf("one", "two", "three")
    val countEndsWithE = numbers.run {
        add("four")
        add("five")
        count { it.endsWith("e") } // lambda表达式最后一句表示其返回值
    }
    println("There are $countEndsWithE elements that end with e.")
    // 此外，还可以忽略返回值
    with(numbers) {
        val firstItem = first()
        val lastItem = last()
        println("First item: $firstItem, last item: $lastItem")
    }
    println("==================================")

    /**
     * 3 几个标准库函数的demo
     */
    //3.1 let 上下文对象作为 lambda 表达式的参数（it）来访问。返回值是 lambda 表达式的结果。
    val numbers2 = mutableListOf("one", "two", "three", "four", "five")
    val resultList = numbers2.map { it.length }.filter { it > 3 }
    println(resultList)

    val numbers3 = mutableListOf("one", "two", "three", "four", "five")
    numbers3.map { it.length }.filter { it > 3 }.let {
        println(it)
        // 如果需要可以调用更多函数
    }
    // 若代码块仅包含以 it 作为参数的单个函数，则可以使用方法引用(::)代替 lambda 表达式：
    val numbers4 = mutableListOf("one", "two", "three", "four", "five")
    numbers4.map { it.length }.filter { it > 3 }.let(::println)

    // let 可以用在可能为空的变量上
    val str2: String? = "Hello" // null
    //processNonNullString(str)       // 编译错误：str 可能为空
    val length = str2?.let {
        println("let() called on $it")
        processNonNullString(it)      // 编译通过：'it' 在 '?.let { }' 中必不为空
        it.length
    }
    println("---------------------")

    // 3.2 with
    // 我们建议使用 with 来调用上下文对象上的函数，而不使用 lambda 表达式结果。 在代码中，with 可以理解为“对于这个对象，执行以下操作。
    val numbers5 = mutableListOf("one", "two", "three")
    with(numbers5) {
        println("'with' is called with argument $this")
        println("It contains $size elements")
    }
    // with 的另一个使用场景是引入一个辅助对象，其属性或函数将用于计算一个值。

    val numbers6 = mutableListOf("one", "two", "three")
    val firstAndLast = with(numbers6) {
        "The first element is ${first()}," +
                " the last element is ${last()}"
    }
    println(firstAndLast)
    // 3.3 run

}

fun processNonNullString(str: String) {}

fun writeToLog(message: String) {
    println("INFO: $message")
}

class HelloKotlin1 {
}