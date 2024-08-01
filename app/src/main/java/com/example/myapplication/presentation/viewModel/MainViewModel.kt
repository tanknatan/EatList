package com.example.myapplication.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.example.myapplication.data.EatListRepositoryImpl
import com.example.myapplication.domain.DeletEatItemUseCase
import com.example.myapplication.domain.EatItem
import com.example.myapplication.domain.EditEatItemUseCase
import com.example.myapplication.domain.GetEatListUseCase

class MainViewModel : ViewModel() {
    private val repository = EatListRepositoryImpl
    private val editEatItemUseCase = EditEatItemUseCase(repository)
    private val getEatListUseCase = GetEatListUseCase(repository)
    private val deletEatItemUseCase = DeletEatItemUseCase(repository)

    val eatList = getEatListUseCase.getEatList()



    fun deletEatItem(eatItem: EatItem){
        deletEatItemUseCase.deletEatItem(eatItem)
    }

    fun changeEnableState(eatItem: EatItem){
        val nexItem = eatItem.copy(enabled =  !eatItem.enabled)
        editEatItemUseCase.editEatItem(nexItem)
    }

}