package com.example.myapplication.domain

class AddEatItenUseCase(private val eatListRepository: EatListRepository) {
    fun addEatItem(eatItem: EatItem){
        eatListRepository.addEatIten(eatItem)
    }

}