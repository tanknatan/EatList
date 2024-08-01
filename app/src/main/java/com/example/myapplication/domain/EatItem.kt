package com.example.myapplication.domain

data class EatItem(
    val name : String,
    val count : Int,
    val enabled : Boolean,
    var id : Int = UNDEFINED_ID,

){
    companion object{
        const val UNDEFINED_ID = -1
    }
}
