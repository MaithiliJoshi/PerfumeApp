package com.example.perfumeapp.fragments

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.perfumeapp.database.PerfumeDao
import com.example.perfumeapp.repository.PerfumeRepository

class DashboardViewModelFactory(
    private val dataSource: PerfumeDao,
    private val repo: PerfumeRepository,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            return DashboardViewModel(dataSource, repo,application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}