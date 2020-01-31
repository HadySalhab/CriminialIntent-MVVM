package com.android.myapplication.criminialintent_refactored.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CrimeDao {

    @Query("SELECT * FROM crimeentity")
    fun allCrimes(): LiveData<List<CrimeEntity>>

    @Query("SELECT * FROM crimeentity WHERE id=(:id)")
    suspend fun getCrime(id: String): CrimeEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCrime(crime: CrimeEntity)

    @Delete
    suspend fun deleteCrime(crime: CrimeEntity)
}