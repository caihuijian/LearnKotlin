package com.example.lib.d03classobject

import kotlin.reflect.KProperty

// 属性委托

class Example1 {
    // 委托属性语法 TODO
    // val/var <属性名>: <类型> by <表达式>
    val p: String by Delegate2()
}

class Delegate2 {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

//    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
//        println("$value has been assigned to '${property.name}' in $thisRef.")
//    }
}


class D0315DelegateField