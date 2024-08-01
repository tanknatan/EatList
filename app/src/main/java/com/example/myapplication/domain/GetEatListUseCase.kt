package com.example.myapplication.domain

import androidx.lifecycle.LiveData

class GetEatListUseCase(private val eatListRepository: EatListRepository) {
    fun getEatList(): LiveData<List<EatItem>> {
        return eatListRepository.getEatList()
    }
}