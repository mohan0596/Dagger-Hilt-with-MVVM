package com.example.sampletest.data.repository

import com.example.sampletest.data.api.ApiHelper
import com.example.sampletest.data.dto.DeptDTO
import com.example.sampletest.data.localdb.LocalDatabaseHelper
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper, private val localDatabaseHelper: LocalDatabaseHelper) {

     fun getUsers()  : Single<List<DeptDTO>> {
        return apiHelper.getDeptUsers().map {
            val deptList : ArrayList<DeptDTO> = ArrayList()
            it.DATA.forEach {data ->
                val dept = DeptDTO()
                dept.dept_id = data.dept_id
                dept.name = data.name
                dept.dept_name = data.dept_name
                deptList.add(dept)
            }
            return@map deptList
        }
     }

    fun saveUser(deptResponse: List<DeptDTO>) : Completable {
        return  localDatabaseHelper.saveUserDetail(deptResponse)
    }

    fun getUsersFromLocal() : Single<List<DeptDTO>>{
        return localDatabaseHelper.getDeptList().map {
            val deptList : ArrayList<DeptDTO> = ArrayList()
            it.forEach {data ->
                val dept = DeptDTO()
                dept.dept_id = data.dept_id
                dept.name = data.name
                dept.dept_name = data.dept_name
                deptList.add(dept)
            }
            return@map deptList
        }
    }


}