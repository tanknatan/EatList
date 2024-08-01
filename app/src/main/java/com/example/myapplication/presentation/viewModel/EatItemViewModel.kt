package com.example.myapplication.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.EatListRepositoryImpl
import com.example.myapplication.domain.AddEatItenUseCase
import com.example.myapplication.domain.EatItem
import com.example.myapplication.domain.EditEatItemUseCase
import com.example.myapplication.domain.GetEatItemUseCase

class EatItemViewModel : ViewModel() {
    private val repository = EatListRepositoryImpl

    private val getEatItemUseCase = GetEatItemUseCase(repository)
    private val editEatItemUseCase = EditEatItemUseCase(repository)
    private val addEatItemUseCase = AddEatItenUseCase(repository)

    private val _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName


    private val _isRedyToCloseScreen = MutableLiveData<Unit>()
    val isRedyToCloseScreen: LiveData<Unit>
        get() = _isRedyToCloseScreen

    private val _eatItem = MutableLiveData<EatItem>()
    val eatItem: LiveData<EatItem>
        get() = _eatItem

    fun getEatItem(id: Int) {
        val item = getEatItemUseCase.getEatItem(id)
        _eatItem.value = item
    }

    fun addEatItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val isValid = validateInput(name, count)
        if (isValid) {
            val item = EatItem(name = name, count = count, true)
            addEatItemUseCase.addEatItem(item)
            finishWork()
        }
    }



fun editEatItem(inputName: String?, inputCount: String?) {
    val name = parseName(inputName)
    val count = parseCount(inputCount)
    val isValid = validateInput(name, count)
    if (isValid) {
        _eatItem.value?.let {
            val item = it.copy(name = name, count = count)
            editEatItemUseCase.editEatItem(item)
            finishWork()
        }

    }
}

private fun parseName(inputString: String?): String {
    return inputString?.trim() ?: ""
}

private fun parseCount(inputString: String?): Int {
    return try {
        inputString?.trim()?.toInt() ?: 0
    } catch (e: NumberFormatException) {
        0
    }
}

private fun validateInput(name: String, count: Int): Boolean {
    var result = true
    if (name.isBlank()) {
        _errorInputName.value = true
        result = false
    }
    if (count <= 0) {
        result = false
    }
    return result
}

public fun resetErrorInputName() {
    _errorInputName.value = false
}

public fun resetErrorInputCount() {
    _errorInputCount.value = false
}

private fun finishWork() {
    _isRedyToCloseScreen.value = Unit
}
}