package com.example.sampletest.data.localdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sampletest.utils.Constant


@Entity(tableName = Constant.DEPT_TABLE)
class DeptTable {

    @ColumnInfo
    @PrimaryKey
    var dept_id: String = ""

    @ColumnInfo
    var dept_name: String? = null

    @ColumnInfo
    var name: String? = null
}