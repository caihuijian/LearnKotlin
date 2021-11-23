package com.example.lib.d07scope.function

/**
 * takeIf 内置函数
 * takeIf 简单使用
 *
 * name.takeIf {true/false}
 * true: 返回name本身
 * false: 返回null
 */

fun main() {
    // 调用简单的takeIf
    val result = checkPermission("hjcai", "123")
    if (result != null) {
        println("欢迎 $result 登录成功")
    } else {
        println("登录失败")
    }
    println("================")

    // 调用 takeIf 与空合并操作符?:结合
    println(checkPermission2("hjcai", "123"))
    // 真实情况下 takeIf经常和空合并操作符?: 联合使用
}

private fun checkPermission(name: String, pwd: String): String? {
    return name.takeIf {
        permissionSystem(name, pwd)
    }
}

/**
 * takeIf 与空合并操作符?:结合
 *
 * 如果takeIf内部为true 返回name 否则返回字符串"登录失败"
 */
private fun checkPermission2(name: String, pwd: String): String {
    return name.takeIf {
        permissionSystem(name, pwd)
    } ?: "登录失败"
}

private fun permissionSystem(userName: String, userPwd: String): Boolean {
    return userName == "hjcai" && userPwd == "123"
}

class HelloKotlin7 {
}