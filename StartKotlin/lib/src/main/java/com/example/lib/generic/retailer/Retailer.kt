package com.example.lib.generic.retailer

/**
 * Created by Cai Huijian on 2021/8/29.
 * 宠物店
 *
 * 想要将子类型Retailer<Cat>的值 赋值给父类型 Retailer<Pet>的对象
 * 就要使用协变 利用out关键字可以达成协变的目的
 * 何时使用协变?
 * 子类型替换父类型时
 * 当需要的泛型是输出对象时
 * 即当前泛型需要赋值或者充当返回值时
 */
interface Retailer<out T> {
    // 注意这里的out关键字
    fun sell(): T
}