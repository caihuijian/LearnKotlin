package com.example.lib.d03classobject

fun main() {
    // 1 Kotlin中的泛型的基本用法
    // 可以装下任何类型的Box
    val box1 = MagicBox("aaa")
    println(box1.item)
    val box2: MagicBox<Dog> = MagicBox(Dog("xiaohei"))
    println(box2.item.name)
    val box3: MagicBox<Boy> = MagicBox(Boy("zhangsan", 15))
    println(box3.item.name + box3.item.age)

    // 2 泛型函数
    box2.avaliable = true
    // 如果fetch返回值不是空 则执行run方法体
    box2.fetch()?.run {
        println("returned data ${this.name}")
    }
    box2.avaliable = false
    val data = box2.fetch()?.run {
        println("returned data ${this.name}")
    }
    println("data is $data")


    // 3 多泛型参数
    val box4: MagicBox<Boy> = MagicBox(Boy("zhangsan", 15))
    box4.avaliable = true
    // change 方法入参是Boy 返回类型是Man
    // 为什么入参是Boy subjectModFun(subject)中 subject就是Boy类型的
    val man: Man? = box4.change {
        Man(it.name, it.age.plus(10))
    }
    man?.run { println("${this.name},${this.age}") }

    // 4 泛型约束
    // val box6: MagicBox1<Dog1> = MagicBox1(Dog1("xiaohei"))// 报错 Dog不是Human1类型
    val box7: MagicBox1<Boy1> = MagicBox1(Boy1("xiaohei", 12))
    val box8: MagicBox1<Man1> = MagicBox1(Man1("xiaohei", 22))

    // 5 泛型中的类型推断
    // 类型参数可以推断出来，例如从构造函数的参数或者从其他途径，允许省略类型参数
    // 以下写法都是等价的
    val box9: MagicBox<Int> = MagicBox<Int>(1)
    val box10: MagicBox<Int> = MagicBox(1)
    val box11 = MagicBox(1)// // 1 具有类型 Int，所以编译器知道我们说的是 Box<Int>。

    // 6 Kotlin中的型变
    // 型变分为两种协变和逆变
    // 协变: 父类可以使用的地方子类也可以使用
    // 父类引用指向子类对象
    // 假设FastFood是Food的子类 如果有Box<FastFood>是Box<Food>的子类 即可以用Box<FastFood>替换Box<Food>
    // 我们称Box的泛型是协变的 (我们的例子中Box是Producer)

    // 逆变: 子类可以使用的地方父类也可以使用
    // 子类引用指向父类对象
    // 假设FoodStore是FastFoodStore的父类 如果有Box<FoodStore>是Box<FastFoodStore>的子类 即可以用Box<FoodStore>替换Box<FastFoodStore>
    // 我们称Box的泛型是逆变的 (我们的例子中Box是Consumer)

    // 7 协变demo
    val foodProducer1: Producer<Food> = FoodStore()
    foodProducer1.produce()

    val fastFoodProducer1: Producer<FastFood> = FastFoodStore()
    fastFoodProducer1.produce()
    val fastFoodProducer2: Producer<Food> = FastFoodStore()
    fastFoodProducer2.produce()

    // BurgerStore里面泛型为Burger 但是赋值的时候可以使用Producer<Burger> Producer<FastFood> Producer<Food>
    // 看起来Producer<Burger> Producer<FastFood> Producer<Food> 都可以指向 子类泛型Burger
    // 原因是Producer使用了out修饰符
    val burgerProducer1: Producer<Burger> = BurgerStore()
    burgerProducer1.produce()
    val burgerProducer2: Producer<FastFood> = BurgerStore()
    burgerProducer2.produce()
    val burgerProducer3: Producer<Food> = BurgerStore()
    burgerProducer3.produce()

    // 8 逆变demo
    // People里泛型为Food 然而 Consumer<Food> Consumer<FastFood> Consumer<Burger>等Food子类的泛型也能指向父类Food的泛型
    // 是因为Consumer使用了逆变 in 操作符
    val foodConsumer: Consumer<Food> = People()
    // 输出的都是 People eat Food
    foodConsumer.consume(Food())
    foodConsumer.consume(FastFood())
    foodConsumer.consume(Burger())
    val foodConsumer2: Consumer<FastFood> = People()
    foodConsumer2.consume(FastFood())
    foodConsumer2.consume(Burger())
    val foodConsumer3: Consumer<Burger> = People()
    foodConsumer3.consume(Burger())

    val fastFoodConsumer: Consumer<FastFood> = ModernPeople()
    fastFoodConsumer.consume(FastFood())
    fastFoodConsumer.consume(Burger())
    val fastFoodConsumer2: Consumer<Burger> = ModernPeople()
    fastFoodConsumer2.consume(Burger())

    val burgerConsumer: Consumer<Burger> = American()
    burgerConsumer.consume(Burger())

    // 9 协变与逆变示例在Java中的写法 参见 D0309GenericJava
}

// out 协变 这里用在返回值上
interface Producer<out T> {
    fun produce(): T
}

// in 逆变 这里用在输入参数上
interface Consumer<in T> {
    fun consume(item: T)
}

// 不变
interface ProduceConsumer<T> {
    fun produce(): T
    fun consume(item: T)
}

open class Food
open class FastFood : Food()
class Burger : FastFood()

// 生产者 结合协变 out使用 协变 用在参数
class FoodStore : Producer<Food> {
    override fun produce(): Food {
        println("produce food")
        return Food()
    }
}

class FastFoodStore : Producer<FastFood> {
    override fun produce(): FastFood {
        println("produce fast food")
        return FastFood()
    }
}

class BurgerStore : Producer<Burger> {
    override fun produce(): Burger {
        println("produce burger")
        return Burger()
    }
}

// 消费者 结合in使用 逆变 用在返回值
class People : Consumer<Food> {
    override fun consume(item: Food) {
        println("People eat Food")
    }
}

class ModernPeople : Consumer<FastFood> {
    override fun consume(item: FastFood) {
        println("ModernPeople eat FastFood")
    }
}

class American : Consumer<Burger> {
    override fun consume(item: Burger) {
        println("American eat Burger")
    }
}


// 4 泛型约束
// 利用继承约束泛型类型 和Java的extends有点像
abstract class Human1(val age: Int)
class Man1(val name: String, age: Int) : Human1(age)
class Boy1(val name: String, age: Int) : Human1(age)
class Dog1(val name: String)
class MagicBox1<T : Human1>(var item: T) {
    var avaliable: Boolean = false
    private var subject: T = item
    fun fetch(): T? {
        // 如果avaliable是true 返回 subject 否则返回null
        return subject.takeIf { avaliable }
    }
}

// 1 Kotlin中的泛型的基本用法
class Man(val name: String, val age: Int)
class Boy(val name: String, val age: Int)
class Dog(val name: String)
class MagicBox<T>(var item: T) {
    var avaliable: Boolean = false
    private var subject: T = item

    // 2 泛型函数 返回一个T类型的对象
    fun fetch(): T? {
        // 如果avaliable是true 返回 subject 否则返回null
        return subject.takeIf { avaliable }
    }

    // 3 多泛型参数
    // change方法 输入类型T 返回类型R
    fun <R> change(subjectModFun: (T) -> R): R? {
        return subjectModFun(subject).takeIf { avaliable }
        /* 等价下面的内容
        return if (avaliable){
            subjectModFun(subject)
        }else{
            null
        }*/
    }
}

class D0309Generic
