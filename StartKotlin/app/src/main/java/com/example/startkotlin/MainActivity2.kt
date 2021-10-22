package com.example.startkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.coroutines.*

// 使用MainScope需要添加依赖kotlinx-coroutines-android以及kotlinx-coroutines-core
// 否则报错 Module with the Main dispatcher is missing. Add dependency providing the Main dispatcher,
// e.g. 'kotlinx-coroutines-android' and ensure it has the same version as 'kotlinx-coroutines-core'
class MainActivity2 : AppCompatActivity(), CoroutineScope by MainScope() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        doSthCostTime()
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }

    fun close(view: View?) {
        this.finish()
    }

    private fun doSthCostTime() {
        repeat(100) { i ->
            launch {
                delay(i * 300L)
                println("coroutine $i is finished")
            }
        }
    }

    fun to3(view: View?) {
        val intent = Intent(this, MainActivity3::class.java)
        startActivity(intent)
    }
}