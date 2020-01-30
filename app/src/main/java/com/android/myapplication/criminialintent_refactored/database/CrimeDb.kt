package com.android.myapplication.criminialintent_refactored.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

private const val  DB_NAME = "crime.db"
@Database(entities = [CrimeEntity::class],version = 1)
@TypeConverters(CrimeTypeConverters::class)
abstract class CrimeDb:RoomDatabase(){
    abstract fun crimeDao(): CrimeDao
    companion object{
        fun newInstance(context: Context)=Room.databaseBuilder(
            context,
            CrimeDb::class.java,
            DB_NAME
        ).build()
    }
}