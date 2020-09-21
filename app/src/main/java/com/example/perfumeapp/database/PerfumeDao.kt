package com.example.perfumeapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single


@Dao
interface PerfumeDao{

    @Query("SELECT * from perfume_table WHERE perfumeId = :key")
    fun get(key: Long): Single<Perfume>

    @Query("SELECT * FROM perfume_table ORDER BY perfumeId ASC")
    fun getAllPerfumes(): Single<List<Perfume>>

    @Insert
    fun insertAll(perfumes: List<Perfume>)

    @Query("Delete from perfume_table")
    fun deleteAll()
}