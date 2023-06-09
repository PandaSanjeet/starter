package com.example.android.politicalpreparedness.election

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

//Created Factory to generate ElectionViewModel with provided election datasource
class ElectionsViewModelFactory(private val app: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ElectionsViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return ElectionsViewModel(app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}