package com.example.lib.d03classobject
// 学习密封类之前建议了解一下枚举类 密封类从某种角度上看是枚举类的升级版

fun main() {
    // 1 密封类
    // 密封类用来表示受限的类继承结构：当一个值为有限几种的类型、而不能有任何其他类型时。
    // 在某种意义上，他们是枚举类的扩展：枚举类型的值集合也是受限的，但每个枚举常量只存在一个实例，而密封类的一个子类可以有可包含状态的多个实例。
    // 简而言之 枚举类里面的各种case是单例 但是密封类的各种case不一定是单例

    // 2 继承自密封类
    // val a = Expr() //密封类本身是抽象的 不能实例化
    val b = Const(12.0)
    val c = Sum(b, b)
    val d = NotANumber
    val e = NotANumber
    println(d === e)

    // 3 密封类中switch case的使用
    // 使用密封类的关键好处在于使用 when 表达式 的时候，如果能够验证语句覆盖了所有情况，就不需要为该语句再添加一个 else 子句了。
    // 当然，这只有当你用 when 作为表达式（使用结果）而不是作为语句时才有用。
    println(eval(Const(16.1)))
    println(eval(Sum(Const(16.1), Const(13.9))))
    println(eval(NotANumber))

    // 4 密封类 实战2 该案例参考自bilibili动脑学院P102密封类
    val status: LicenseStatus2 = LicenseStatus2.Learning
    val driver = Driver2(status)
    println(driver.checkLicense())
    val driver2 = Driver2(LicenseStatus2.Qualified("12345"))
    println(driver2.checkLicense())

    // 5 总结 密封类vs枚举类
    // 枚举类每个类型都是单例 密封类每个类型可以是单例也可以不是 相比于枚举类 密封类更加灵活
}

// 4 密封类 实战2
sealed class LicenseStatus2 {
    object UnQualified : LicenseStatus2()// 密封类子类 单例 无状态
    object Learning : LicenseStatus2()// 密封类子类 单例 无状态

    // 可以有多个实例 licenseId是一个状态 不同driver的licenseId可以不一样
    class Qualified(val licenseId: String) : LicenseStatus2()
}

class Driver2(var status: LicenseStatus2) {
    fun checkLicense(): String {
        return when (status) {
            is LicenseStatus2.Learning -> "在学"
            is LicenseStatus2.UnQualified -> "没有资格"
            is LicenseStatus2.Qualified -> "有驾驶证. 驾驶证编号:${(this.status as LicenseStatus2.Qualified).licenseId}"
        }
    }
}

// 3 密封类种switch case的使用
fun eval(expr: Expr): Double = when (expr) {
    is Const -> expr.number
    is Sum -> eval(expr.e1) + eval(expr.e2)
    NotANumber -> Double.NaN
    // else -> 0.0 // 'when' is exhaustive so 'else' is redundant here
    // 不再需要 `else` 子句，因为我们已经覆盖了所有的情况
}


// 密封类不允许有非-private 构造函数（其构造函数默认为 private）
sealed class Expr1(parameter: String) {
    // Constructor must be private or protected in sealed class
    // public constructor(parameter: String, parameter2: String) : this(parameter)
}

// 2 继承自密封类
// 密封类
// 虽然密封类也可以有子类，但是所有子类都必须在与密封类自身相同的文件中声明
// 注意，扩展密封类子类的类（间接继承者）可以放在任何位置，而无需在同一个文件中。
sealed class Expr

// 数据类继承密封类
data class Const(val number: Double) : Expr()

// 数据类继承密封类
data class Sum(val e1: Expr, val e2: Expr) : Expr()

// 单例继承密封类
object NotANumber : Expr()

class D0308SealedClass