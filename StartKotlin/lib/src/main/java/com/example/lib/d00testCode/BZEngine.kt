package com.example.lib.d00testCode


private open class BZEngine(type: String) : Engine(type) {
    override fun engineType(): String {
        // 子类访问父类方法
        return super.engineType()
    }
}