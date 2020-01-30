package com.android.myapplication.criminialintent_refactored.database

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*

@Dao
interface CrimeDao {

    @Query("SELECT * FROM crimeentity")
    fun getCrimes(): LiveData<List<CrimeEntity>>

    @Query("SELECT * FROM crimeentity WHERE id=(:id)")
    fun getCrime(id: UUID): LiveData<CrimeEntity?>

    @Update
    fun updateCrime(crime: CrimeEntity)

    @Insert
    fun addCrime(crime: CrimeEntity)

    @Delete
    fun deleteCrime(crime:CrimeEntity)
}