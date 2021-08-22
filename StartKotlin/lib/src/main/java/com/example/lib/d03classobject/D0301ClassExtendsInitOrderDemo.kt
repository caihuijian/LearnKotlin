import java.util.*

fun main() {
    val demo:D0301ClassExtendsInitOrderDemo = D0301ClassExtendsInitOrderDemo("demo",12)
}
class D0301ClassExtendsInitOrderDemo constructor(name: String) {
    // .also(::println)目前还不理解 先想成一个输出吧
    // 主构造的参数可以在初始化块中使⽤。它们也可以在类体内声明的属性初始化器中使⽤
    val firstProperty = "First property: $name".also(::println)
    val customerKey = name.uppercase(Locale.getDefault())

    init {
        println("First initializer block that prints ${name}")
    }

    val secondProperty = "Second property: ${name.length}".also(::println)

    init {
        println("Second initializer block that prints ${name.length}")
    }

    // 初始化代码块发生在次构造方法前
    constructor(parameter:String,age:Int) : this(parameter){
        println("second constructor")
    }
}