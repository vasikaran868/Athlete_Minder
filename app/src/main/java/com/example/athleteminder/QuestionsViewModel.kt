package com.example.athleteminder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class QuestionsViewModel: ViewModel() {

    var poa : Int? = null
    var focus : Int? = null
    var intensity : Int? = null
    var eating : Int? = null
    var sleeping : Int? = null

}

class QuestionsViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuestionsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return QuestionsViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}