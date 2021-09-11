package com.example.lib.d04functions

fun main() {
    // 1 中缀符号 (infix notation)
    // 具体作用主要是 忽略该调用的点与圆括号
    // 函数定义成中缀函数有3个条件
    // a 是成员函数或扩展函数 (依赖于某一个类)
    // b 只有一个参数 且没有默认值(不可以是可变参数)
    // c 使用infix修饰
    val infixTest = InfixTest(2)
    println(infixTest.add(5))
    println(infixTest add 5) //中缀表达式的写法

    /**
     * 2 内联函数
     * 2.1 内联函数的目的
     * 以下为个人理解
     * 函数之间的调用会有函数堆栈 进入下一个函数前 当前函数的信息会以存入堆栈 知道从下一个函数返回才会取出
     * 因此 函数调用层级越高 会导致调用堆栈异常庞大 在递归函数中如果发生无法终止递归的情况 会一直运行直到堆栈溢出
     * 内联函数的目的是为了避免函数堆栈过大设计出来的
     * 调用到内联函数时 会将该函数复制到调用处 这样 在同一个函数里 就不会创建创建函数调用堆栈存放数据了
     * 2.2 内联函数的缺点
     * 以下为个人理解
     * 虽然内联函数一定程度上降低了调用堆栈的高度 但是 如果内联函数被调用的次数太多 那么就需要不断的拷贝
     * 这种操作消耗的资源也不见得很小 所以 内联函数的作用其实有限
     * 2.3 内联函数的示例
     * 通过反编译kotlin为Java 我们可以轻易看出区别
     * 或者通过 javap xx.D0401Functions2  javap -c xx.D0401Functions2 反编译出字节码 也可以看出区别
     */
    println(myInlineFun(1, 2))
    println(myNormalFun(1, 2))
    println("=====2 end======")

    /**
     * 3 高阶函数(high-order function)和 Lambda 表达式
     * 一个函数 如果它接受一个函数作为参数或者返回一个函数 那么这就是高阶函数
     * lambda 表达式的要求
     * a lambda表达式被花括号包围
     * b 参数位于 —> 之前 (参数类型可以省略)
     * c 执行体位于 -> 之后
     * 在kotlin中 如果参数的最后一个参数是一个函数 那么可以将lambda表达式作为实参传递 并且 放在圆括号之外的一对花括号中
     */
    println(oneParameter(2))
    println("===== 3 end======")
}

// lambda 表达式示例
val multiply: (Int, Int) -> Int = { a: Int, b: Int -> a * b } // 最完整写法
val multiply2: (Int, Int) -> Int = { a, b -> a * b } // 简写1
val multiply3 = { a: Int, b: Int -> a * b } // 简写2
val myAction = { println("hello world") } // 无参的lambda表达式
val myAction2: () -> Unit = { println("hello world") } // 完整写法
val myReturnNull: (Int, Int) -> Int? = { a, b ->
    if (a + b > 0) {
        a + b
    } else {
        null
    }
}

// 下划线 变量占位符 如果变量在函数中没有使用 可以使用变量占位符代替
val myReturnNull2: (Int, Int) -> Int? = { _, _ -> null }

// 当只有一个参数时 可以用it代替
val oneParameter: (Int) -> Int = { it -> it + 1 }

// 整个函数可能为空
val functionMayNull: ((Int, Int) -> Int)? = null


// 2 内联函数对比普通函数
inline fun myInlineFun(a: Int, b: Int): Int = a + b
fun myNormalFun(a: Int, b: Int): Int = a + b


// 1 中缀函数demo
class InfixTest(val a: Int) {
    infix fun add(b: Int) = this.a + b
}

class D0401Functions2