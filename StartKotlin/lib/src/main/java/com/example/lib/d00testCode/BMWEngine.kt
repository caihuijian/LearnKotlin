package com.example.lib.d00testCode

class BMWEngine(val name: String) {
    private val bMWEngine = Engine("BMW")
    fun getEngine(): String {
        // 同一包内无法访问protected方法
        // return bMWEngine.engineType()//Cannot access 'engineType': it is protected in 'Engine'
        return ""
    }
}