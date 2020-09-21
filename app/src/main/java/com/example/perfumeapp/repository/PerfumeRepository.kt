package com.example.perfumeapp.repository

import android.util.Log
import com.example.perfumeapp.database.Perfume
import com.example.perfumeapp.database.PerfumeDao
import com.example.perfumeapp.service.PerfumeService
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PerfumeRepository (val perfumeService: PerfumeService, val perfumeDao: PerfumeDao){

    fun getPerfumes() : Single<List<Perfume>> {
        Log.d("PerfumeRepo","just entering")
        return perfumeService.getAllPerfumes()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .flatMap {
                Log.d("PerfumeRepo","got data ${it}")
                perfumeDao.deleteAll()
             Single.just(it)}
            .flatMap{
                perfumeDao.insertAll(it)
                Single.just(it)
            }.flatMap { getPerfumesFromDb() }.observeOn(AndroidSchedulers.mainThread())

    }

    private fun getPerfumesFromDb() : Single<List<Perfume>> {
        Log.d("PerfumeRepo","gettingDbData")
        return perfumeDao.getAllPerfumes()


    }
}