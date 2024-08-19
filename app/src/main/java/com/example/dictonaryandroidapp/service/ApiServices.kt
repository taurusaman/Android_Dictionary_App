package com.example.dictonaryandroidapp.service

import com.example.dictonaryandroidapp.model.WordResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {

    @GET("en/{word}")
    suspend fun getMeaning(@Path("word") word: String) : Response<List<WordResult>>

 }