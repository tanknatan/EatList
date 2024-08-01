package com.example.myapplication.domain

public class GetEatItemUseCase(private val eatListRepository: EatListRepository) {

    fun getEatItem(eatItemId : Int) : EatItem{
        return eatListRepository.getEatIten(eatItemId)
    }
}