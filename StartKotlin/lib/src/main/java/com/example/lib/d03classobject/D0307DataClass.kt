package com.example.lib.d03classobject


fun main() {
    // 1 数据类
    // 保存数据的类
    // 一些标准函数往往是从数据机械推导而来的。在 Kotlin 中，这叫做 数据类 并标记为 data
    // 测试数据类
    val user1 = D0307DataClass.User("zhangsan", 25)
    println(user1)
    val user2 = User("lisi")
    println(user2.name)
    user2.name = "AA"
    // 2 数据类的组成部分
    // equals()/hashCode() 对；
    // toString() 格式是 "User(name=John, age=42)"；
    // componentN() 函数 按声明顺序对应于所有属性；
    // copy() 函数

    // 3 为什么需要数据类
    // 数据类和普通类之间的区别
    // 主要就是数据类为我们提供了toSting equals等默认的方便方法
    // == vs ===
    // == 比较内容 类似于Java的equals实际调用的也是Kotlin的equals
    // === 比较引用地址 类似于Java的==
    // 这里数据类有自己的equals实现 因此输出相等
    println(Coordinate(1, 1) == Coordinate(1, 1))
    // 普通类的equals实际是继承自超类Any的equals 其默认实现是===
    println(Coordinate1(1, 1) == Coordinate1(1, 1))

    // 4 数据类的限定条件
    // 主构造函数需要至少有一个参数；
    // 主构造函数的所有参数需要标记为 val 或 var；(var 与val的区别在于能不能给属性重新赋值)
    // 数据类不能是抽象、开放、密封或者内部的；
    // （在1.1之前）数据类只能实现接口 接口我们还没有学习 后面再看
    // 相同空间内不能定义同名的数据类 即使参数不同 但是不同空间可以定义同名数据类
    // 不允许为 componentN() 以及 copy() 函数提供显式实现

    // 5 数据类的继承
    // 数据类无法继承数据类 但是数据类可以继承自普通类
    // 如果在数据类体中有显式实现 equals()、 hashCode() 或者 toString()，或者这些函数在父类中有 final 实现，
    // 那么不会生成这些函数，而会使用现有函数
    // 如果超类型具有 open 的 componentN() 函数并且返回兼容的类型， 那么会为数据类生成相应的函数，并覆盖超类的实现。
    // 如果超类型的这些函数由于签名不兼容或者是 final 而导致无法覆盖，那么会报错
    // 如果生成的类需要含有一个无参的构造函数，则所有的属性必须指定默认值
    var child = DataChild()

    // 6 在数据类中声明属性
    // 这样做可以是的该属性避免参与toString equals hashCode copy等实现
    // 这样在 toString()、 equals()、 hashCode() 以及 copy() 的实现中只会用到 name 属性，并且只有一个 component 函数 component1()。虽然两个 Person 对象可以有不同的年龄，但它们会视为相等。
    val person = Person1("wangwu")
    person.age = 10
    val person2 = Person1("wangwu")
    person.age = 15
    println(person == person2)

    // 7 数据类的copy
    // 数据类会生成copy方法 我们可以利用该方法进行数据类的拷贝 但是注意是浅拷贝
    val originData = CopyData(Coordinate(1, 1), 123)
    val newData = originData.copy()
    println(originData === newData)
    println(originData.coordinate === newData.coordinate)
    // copy 方法方便我们创建新对象 但是需要注意是浅拷贝 下面这样拷贝才是深拷贝
    val newData2 = originData.copy(coordinate = Coordinate(originData.coordinate.x, originData.coordinate.y))
    println(originData.coordinate === newData2.coordinate)

    // 8 数据类与解构声明 这里我们先看看即可 后面学习解构部分再细说
    val jane = D0307DataClass.User("Jane", 35)
    val (name, age) = jane
    println("$name, $age years of age") // 输出 "Jane, 35 years of age"

    // 9 kotlin 库中的标准数据类
    val pair = Pair(Person1("lisi"), Coordinate())
    val triple = Triple(Person1("lisi"), Coordinate(), 1)
}

data class CopyData(var coordinate: Coordinate, var id: Int) {

}


// 6 在数据类中声明属性
data class Person1(val name: String) {
    var age: Int = 0
}

open class Parent(open val name: String = "", open var age: Int = 0) {
    // 如果在数据类体中有显式实现 equals()、 hashCode() 或者 toString()，
    // 或者这些函数在父类中有 final 实现，那么不会生成这些函数，而会使用现有函数
    // 本例中是第二种

    final override fun toString(): String {
        return super.toString()
    }

    final override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    final override fun hashCode(): Int {
        return super.hashCode()
    }

    // 如果超类型具有 open 的 componentN() 函数并且返回兼容的类型， 那么会为数据类生成相应的函数，并覆盖超类的实现。
    // 如果超类型的这些函数由于签名不兼容或者是 final 而导致无法覆盖，那么会报错；
    open fun component1(): String {
        return this.name
    }

    open fun component2(): Int {
        return this.age
    }

}

// 数据类继承

data class DataChild(override val name: String = "  ", override var age: Int = 0) : Parent() {
}

// 定义在顶层的数据类
data /*不能是 open sealed abstract inner*/  class User(var name: String)

open class Coordinate(open var x: Int = 0, open var y: Int = 0) {
    val isValid = x > 0 && y > 0
}

val dataClassMethod =
        """
    public final int component1() {
        return this.x;
    }
    
    public final int component2() {
        return this.y;
    }
    
    @NotNull
    public final Coordinate copy(int x, int y) {
        return new Coordinate(x, y);
    }

   @NotNull
   public String toString() {
      return "Coordinate(x=" + this.x + ", y=" + this.y + ")";
   }

   public int hashCode() {
      return Integer.hashCode(this.x) * 31 + Integer.hashCode(this.y);
   }

   public boolean equals(@Nullable Object var1) {
      if (this != var1) {
         if (var1 instanceof Coordinate) {
            Coordinate var2 = (Coordinate)var1;
            if (this.x == var2.x && this.y == var2.y) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }    
"""

// 可以查看普通类的反编译结果 发现他没有数据类的那些equals copy等方法
class Coordinate1(var x: Int = 0, var y: Int = 0) {
    val isValid = x > 0 && y > 0
}


class D0307DataClass {
    // 定义在类中的数据类
    data class User(val name: String, val age: Int)
}