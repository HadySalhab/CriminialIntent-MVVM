package com.android.myapplication.criminialintent_refactored

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
    private val _crimeIdLiveData = MutableLiveData<String>()

    val crimeLiveData: LiveData<CrimeModel?> = Transformations.map(_crimeIdLiveData) { crimeId ->
        val crimeEntity = dao.getCrime(crimeId).value
        dataTransformer.transformToModel(crimeEntity)
    }

    fun loadCrime(crimeId: String) {
        _crimeIdLiveData.value = crimeId
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

    suspend fun update(crimeModel: CrimeModel) {
        withContext(Dispatchers.IO) {
            dao.updateCrime(dataTransformer.transformToEntity(crimeModel)!!)
        }
    }
}