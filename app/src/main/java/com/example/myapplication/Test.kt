package com.example.myapplication

import kotlin.math.absoluteValue

class Test {
    private var b : String = "cock"
    private val a by lazy {
        2
    }
    data class Abc(
        private val _a: Int?,
        val b: Int,
    ) {
        val a get() = _a ?: throw Exception("_a is null!")
        var callback: () -> Unit = {
            (-2).abs
            Test()
        }
    }
    companion object {
        fun abs() {}
    }
}
fun Int.hello() {
    println("Hello")
}
val Int.abs get() = this.absoluteValue
