package com.example.lib.d05others

// 注解的定义与使用

// 元注解 mata-annotation 用于描述注解
// 常见元注解
// 1 元注解 @Target 表示当前annotation使用在哪个位置
// 可以用在类 方法 值参数 表达式 构造方法上
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.EXPRESSION,
        AnnotationTarget.CONSTRUCTOR, AnnotationTarget.PROPERTY_SETTER)
// 2 元注解 @Retention 表示当前注解是运行时注解还是编译时注解等
// 注解在源码时  运行时  二进制(编译时)
@Retention(AnnotationRetention.SOURCE)
// 3 元注解 @Repeatable 表示当前注解是否可以在同一个元素重复使用
// 4 元注解 @MustBeDocumented 表示当前注解是API 文档的一部分
@MustBeDocumented
// annotation的定义
annotation class MyAnnotation

@MyAnnotation
class D0507Annotation {
    @MyAnnotation
    fun myMethod(@MyAnnotation a: Int/*值参数*/): Int {
        return (@MyAnnotation 10)/*表达式*/
    }
}

// 给主构造方法添加注解 constructor关键字不可省略
class MyClass2 @MyAnnotation constructor(a: Int) {

}

class MyClass3 {
    var a: Int? = null
        // 给set方法添加注解
        @MyAnnotation set
}

