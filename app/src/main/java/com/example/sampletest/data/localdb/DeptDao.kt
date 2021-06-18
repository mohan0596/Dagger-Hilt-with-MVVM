package com.example.sampletest.data.localdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sampletest.utils.Constant
import io.reactivex.Single

@Dao
interface DeptDao {

    @Query("Select * from ${Constant.DEPT_TABLE}")
    fun getDeptData() : Single<List<DeptTable>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<DeptTable>)

}