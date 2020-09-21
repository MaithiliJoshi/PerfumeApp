package com.example.perfumeapp.fragments

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.perfumeapp.database.Perfume
import com.example.perfumeapp.database.PerfumeDao
import com.example.perfumeapp.repository.PerfumeRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DashboardViewModel(
    val perfumeDao: PerfumeDao,
    val perfumeRepository: PerfumeRepository,
    application: Application
) : AndroidViewModel(application) {

    private val disposable = CompositeDisposable()

    private val _perfumeData = MutableLiveData<List<Perfume>>()

    private val _navigateToSelectedPerfume = MutableLiveData<Perfume>()

    val navigateToSelectedPerfume: LiveData<Perfume>
            get() = _navigateToSelectedPerfume

    val perfumeData : LiveData<List<Perfume>>
            get() = _perfumeData

    fun getAllPerfumes() {
        disposable.add(perfumeDao.getAllPerfumes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                perfumeList ->
                _perfumeData.value = perfumeList
            },{
                Log.d("PerfumeViewModel","error in getting perfumes $it")}))
    }

    fun getPerfumesData() {
        disposable.add(perfumeRepository.getPerfumes()
            .subscribe({
                    perfumeList ->
                _perfumeData.value = perfumeList
            },{
                Log.d("PerfumeViewModel","error in getting perfumes $it ")}))
    }

    fun displayPerfumeDetails(perfume: Perfume){
        _navigateToSelectedPerfume.value = perfume
    }

    fun displayComplete(){
        _navigateToSelectedPerfume.value = null
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()

    }
}
