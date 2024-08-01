package com.example.myapplication.domain

import androidx.lifecycle.LiveData

interface EatListRepository {
    fun addEatIten(eatItem: EatItem)

    fun deletEatItem(eatItem: EatItem)

    fun editEatItem(eatItem: EatItem)

    fun getEatIten(eatItemId : Int) : EatItem

    fun getEatList(): LiveData<List<EatItem>>
}