package com.example.lib.d00testCode


open class Engine(open val type: String) {
    protected open fun engineType(): String {
        return "engine type is $type"
    }
}

