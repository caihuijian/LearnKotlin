package com.example.lib.d03classobject

// 如果派⽣类有⼀个主构造函数，其基类可以（并且必须）直接或间接⽤派⽣类主构造函数的参数就地初始化。
// 即 必须调用父类的主构造方法
class D0301ExtendsChild1 public constructor(parameter: String, name: String, age: Int) : D0301Extends(name, age)