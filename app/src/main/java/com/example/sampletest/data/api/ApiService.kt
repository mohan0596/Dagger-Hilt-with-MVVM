package com.example.sampletest.data.api

import com.example.sampletest.data.model.DeptResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET("getManager")
    fun getDeptUsers() : Single<DeptResponse>

}