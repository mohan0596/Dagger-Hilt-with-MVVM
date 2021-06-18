package com.example.sampletest.data.api

import com.example.sampletest.data.model.DeptResponse
import io.reactivex.Single

interface ApiHelper {


    fun getDeptUsers() : Single<DeptResponse>

}