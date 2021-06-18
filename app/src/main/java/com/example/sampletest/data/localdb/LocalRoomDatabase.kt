package com.example.sampletest.data.localdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sampletest.data.localdb.DeptDao
import com.example.sampletest.data.localdb.DeptTable

@Database(
    entities = [DeptTable::class],
    version = 1,
    exportSchema = false)
abstract class LocalRoomDatabase() : RoomDatabase() {

    abstract fun getDeptDao(): DeptDao

}