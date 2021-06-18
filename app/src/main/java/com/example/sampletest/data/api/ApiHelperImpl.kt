package com.example.sampletest.data.api

import com.example.sampletest.data.model.DeptResponse
import io.reactivex.Single
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

   // override suspend fun getUsers(): Response<List<User>> = apiService.getUsers()

    override fun getDeptUsers(): Single<DeptResponse> {
        return Single.create {
            apiService.getDeptUsers()
        }
    }

}