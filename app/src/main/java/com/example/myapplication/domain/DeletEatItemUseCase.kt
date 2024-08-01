package com.example.myapplication.domain

class DeletEatItemUseCase(private val eatListRepository: EatListRepository) {
    fun deletEatItem(eatItem: EatItem){
        eatListRepository.deletEatItem(eatItem)
    }
}