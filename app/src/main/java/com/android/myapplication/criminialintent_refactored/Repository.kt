package com.android.myapplication.criminialintent_refactored

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.android.myapplication.criminialintent_refactored.database.CrimeDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(private val dao: CrimeDao, private val dataTransformer: DataTransformer) {

    val crimesLiveData = Transformations.map(dao.allCrimes()) { listOfCrimesEntity ->
        dataTransformer.transformListToModel(listOfCrimesEntity)
    }

    suspend fun loadCrime(crimeId: String):CrimeModel? {
       return withContext(Dispatchers.IO) {
            val crimeEntity = dao.getCrime(crimeId)
            dataTransformer.transformToModel(crimeEntity)
        }
    }


    suspend fun save(crimeModel: CrimeModel) {
        withContext(Dispatchers.IO) {
            dao.addCrime(dataTransformer.transformToEntity(crimeModel)!!)
        }

    }

    suspend fun delete(crimeModel: CrimeModel) {
        withContext(Dispatchers.IO) {
            dao.deleteCrime(dataTransformer.transformToEntity(crimeModel)!!)
        }

    }
}