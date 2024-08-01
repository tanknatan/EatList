package com.example.myapplication.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.domain.EatItem
import com.example.myapplication.domain.EatListRepository

object EatListRepositoryImpl : EatListRepository{

    private val eatListLD =  MutableLiveData<List<EatItem>>()

    private val eatList = sortedSetOf<EatItem>( {
        o1, o2 -> o1.id.compareTo(o2.id)
    })

    private var autoIncrementId = 0

    override fun addEatIten(eatItem: EatItem) {
        if (eatItem.id ==EatItem.UNDEFINED_ID) {
            eatItem.id = autoIncrementId++
        }
        eatList.add(eatItem)
        updateList()
    }

    override fun deletEatItem(eatItem: EatItem) {
        eatList.remove(eatItem)
        updateList()
    }

    override fun editEatItem(eatItem: EatItem) {
        val oldElement = getEatIten(eatItem.id)
        eatList.remove(oldElement)
        addEatIten(eatItem)
    }

    override fun getEatIten(eatItemId: Int): EatItem {
        return eatList.find { it.id == eatItemId
        }?: throw java.lang.RuntimeException("Элемент с id $eatItemId не найден")
    }

    override fun getEatList():LiveData<List<EatItem>>  {
        return eatListLD
    }

    private fun updateList() {
        eatListLD.value = eatList.toList()
    }

}