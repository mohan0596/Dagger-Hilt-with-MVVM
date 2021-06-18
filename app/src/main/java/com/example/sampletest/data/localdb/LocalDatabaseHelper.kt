package com.example.sampletest.data.localdb

import com.example.sampletest.data.dto.DeptDTO
import com.example.sampletest.data.localdb.DeptTable
import io.reactivex.Completable
import io.reactivex.Single

interface LocalDatabaseHelper {

    fun saveUserDetail(deptResponse: List<DeptDTO>): Completable

    fun getDeptList() : Single<List<DeptTable>>

}