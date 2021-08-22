package com.example.lib.d03classobject

// 如果派⽣类没有主构造函数，那么每个次构造函数必须使⽤ super 关键字初始化其基类型，或委托给另⼀个构
// 造函数做到这⼀点。注意，在这种情况下，不同的次构造函数可以调⽤基类型的不同的构造函数：
class D0301ExtendsChild2 : D0301Extends { //

    constructor(name: String) : super()

    constructor(name: String,age:Int) : super()
}