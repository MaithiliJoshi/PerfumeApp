package com.example.perfumeapp.service

import com.example.perfumeapp.database.Perfume
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.plugins.RxJavaPlugins
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


val BASE_URL = "http://10.0.2.2:3000/"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val mockInterceptor = MockInterceptor()


private val okHttpClient = OkHttpClient
    .Builder()
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .build()




interface PerfumeService{

    @GET("perfumes")
    fun getAllPerfumes() : Single<List<Perfume>>
}

object PerfumeApi {
    val retrofitService : PerfumeService by lazy {
        retrofit.create(PerfumeService::class.java)
    }
}