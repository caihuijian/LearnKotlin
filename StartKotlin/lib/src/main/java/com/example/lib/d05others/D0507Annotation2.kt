package com.example.lib.d05others

import kotlin.reflect.KClass

/**
 * Created by Cai Huijian on 2021/9/16.
 */
// 注解part2
// Java注解与Kotlin注解 100% 兼容

/**
 * 1 注解也可以拥有自己的构造方法
 * 注解的构造方法的参数类型可以是：
 * Java里面定义的基本数据类型(Int,Long...)
 * 字符串类型
 * classes (MyClass::class)
 * 枚举
 * 其他注解
 * 上述类型的数组类型
 *
 * Kotlin 注解不允许为空 因为JVM不支持以null的形式存储注解属性
 * 如果某个注解是另外一个注解的参数 这个注解使用的时候不需要以@开头
 */
// 声明一个参数构造方法的注解
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.EXPRESSION,
        AnnotationTarget.CONSTRUCTOR, AnnotationTarget.PROPERTY_SETTER)
annotation class MyAnnotation2(val string: String)

// 使用该注解
@MyAnnotation2("one parameter annotation")
class MyClass4

// 声明2个参数构造方法的注解
annotation class MyAnnotation3(val str: String, val annotation: MyAnnotation2)

// 使用该注解
@MyAnnotation3("2 parameter annotation", MyAnnotation2("parameter"))

/**
 * 2 注解中带有class作为参数的注解
 *
 * 如果某个注解中包含class作为参数 那么需要使用Kotlin class(KClass)
 * Kotlin编译器会将其自动转换为Java class
 * 这样 Java代码才能正常看到注解和参数
 */
annotation class MyAnnotation4(val arg1: KClass<String>, val arg2: KClass<out Any>, val arg3: KClass<*>)

@MyAnnotation4(String::class, Int::class, Float::class)
class MyClass6


/**
 * 3 注解使用处目标 (use-site target)
 *
 * 指明注解是用在何处
 * 在对类的属性或是主构造方法的参数声明注解时 会存在多个Java元素可以通过对应的Kotlin元素生成出来,
 * 因此 在生成的Java字节码中 可能存在多个可能的位置用于生成注解 （即注解存在二义性或多义性 不知道这个注解是加在什么东西上的）
 * 如想确定注解是加到什么东西上 可以使用注解使用出目标对注解进行进一步修饰
 */

@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.EXPRESSION,
        AnnotationTarget.CONSTRUCTOR, AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.FIELD, AnnotationTarget.PROPERTY_GETTER)
annotation class MyCustomerAnnotation

// 不加 注解使用处目标 可能不知道该注解用在何处 比如用在属性上 get方法上还是构造方法的形参上 加上注解使用处目标可以清晰知道该注解用在何处
class MyClass7(@field:MyCustomerAnnotation val arg1: String,// 用在Java 属性值上
               @get:MyCustomerAnnotation val arg2: String,// 用在该属性的get方法
               @param:MyCustomerAnnotation val arg3: String)// 用在构造方法的参数上


fun main() {

}

class D0507Annotation2 {
}