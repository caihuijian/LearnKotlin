package com.example.lib.d03classobject

/*
3 扩展作用域
a.扩展函数所定义的类的实例叫分发接收者(dispatch receiver)
b.扩展函数所扩展的那个类的实例叫做扩展接收者(extension receiver)
c.当两个名字冲突时 扩展接收者优先级更高
 */
// DD的实例代扩展接收者
// 扩展函数所扩展的那个类的实例叫做扩展接收者
class DD {
    fun method() {
        println("Do method")
    }
}

// EE的实例代表分发接收者
// 扩展函数所定义的类(EE)的实例 做扩展接收者(extension receiver)
class EE {
    fun method2() {

    }

    // DD 的扩展方法可以调用分发接收者的方法 也可以调用扩展接收者的方法
    fun DD.hello() {
        method()
    }

    fun DD.output1() {
        // 当扩展接收者和分发接收者 两个名字冲突时 扩展接收者优先级更高
        println(toString())
    }

    fun DD.output2() {
        // 当扩展接收者和分发接收者 两个名字冲突时 扩展接收者优先级更高 但是可以用this@EE 绕过这一限制
        println(this@EE.toString())
    }

    fun test() {
        val dd = DD()
        dd.output1()
        dd.output2()
    }
}

fun main() {
    var dd = DD()
    // dd.hello() // 调用失败 扩展分发如果在非顶层空间定义 其有效范围只在其定义的类中有效

    EE().test()
}

// 扩展可以很好解决Java类中各种辅助类过多的情况
class D03Add4 {
}
