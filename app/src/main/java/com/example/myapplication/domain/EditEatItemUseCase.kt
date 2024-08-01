package com.example.myapplication.domain

class EditEatItemUseCase(private val eatListRepository: EatListRepository) {

    fun editEatItem(eatItem: EatItem){
        eatListRepository.editEatItem(eatItem)
    }
}