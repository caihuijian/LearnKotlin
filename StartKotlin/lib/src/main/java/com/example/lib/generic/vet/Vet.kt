package com.example.lib.generic.vet

import com.example.lib.generic.pet.Pet

/**
 * Created by Cai Huijian on 2021/8/29.
 * 兽医类
 *
 * 何时使用逆变?
 * 父类型替换子类型时
 * 当需要的泛型是输入对象时(类的方法使用泛型作为参数类型)
 */
// 将in关键字从该类移动到Contest的构造方法上 将变为局部逆变
class Vet</*in*/ T : Pet> {
    // 泛型为任意Pet或Pet的子类
    fun treat(t: T) {
        println("Treat pet ${t.name}")
    }
}