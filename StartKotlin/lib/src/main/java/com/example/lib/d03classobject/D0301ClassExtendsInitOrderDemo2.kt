fun main() {
    val demo:D0301ClassExtendsInitOrderDemo2 = D0301ClassExtendsInitOrderDemo2("demo")
}
// 请注意，初始化块中的代码实际上会成为主构造函数的⼀部分。委托给主构造函数会作为次构造函数的第⼀条
// 语句，因此所有初始化块与属性初始化器中的代码都会在次构造函数体之前执⾏。即使该类没有主构造函数，这
// 种委托仍会隐式发⽣，并且仍会执⾏初始化块
// 没有主构造方法
class D0301ClassExtendsInitOrderDemo2 {
    init {
        // 该部分代码发生在次构造方法前
        println("Init block")
    }
    constructor(i: String) {
        println("Constructor $i")
    }
}