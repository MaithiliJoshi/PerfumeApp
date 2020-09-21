package com.example.perfumeapp.fragments

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.perfumeapp.database.Perfume

class PerfumeDetailViewModel(perfume: Perfume,
                             application: Application) : ViewModel(){

    private val _selectedPerfume = MutableLiveData<Perfume>()

    val selectedPerfume: LiveData<Perfume>
        get() = _selectedPerfume

    init {
        _selectedPerfume.value = perfume
    }

}