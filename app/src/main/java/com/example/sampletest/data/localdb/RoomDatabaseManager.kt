package com.example.sampletest.data.localdb

import com.example.sampletest.data.dto.DeptDTO
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class RoomDatabaseManager @Inject constructor(private val localRoomDatabase: LocalRoomDatabase) :
    LocalDatabaseHelper {

    override fun saveUserDetail(deptResponse: List<DeptDTO>): Completable {
        return Completable.create { emitter ->
            val userList : ArrayList<DeptTable> = ArrayList()
            deptResponse.forEach {
                val userDetailTable = DeptTable()
                userDetailTable.dept_id = it.dept_id!!
                userDetailTable.name = it.name
                userDetailTable.dept_name = it.dept_name
                userList.add(userDetailTable)
            }

            localRoomDatabase.getDeptDao().insertAll(userList)
            emitter.onComplete()
        }
    }

    override fun getDeptList(): Single<List<DeptTable>> {

          return  localRoomDatabase.getDeptDao().getDeptData()

    }
}