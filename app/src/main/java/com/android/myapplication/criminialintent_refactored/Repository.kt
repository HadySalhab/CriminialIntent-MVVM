package com.android.myapplication.criminialintent_refactored

import android.view.animation.Transformation
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.room.Dao
import com.android.myapplication.criminialintent_refactored.database.CrimeDao
import com.android.myapplication.criminialintent_refactored.database.CrimeEntity
import javax.xml.transform.Transformer

class Repository(private val dao: CrimeDao, private val dataTransformer: DataTransformer) {


    val crimesLiveData = Transformations.map(dao.allCrimes()) { listOfCrimesEntity ->
        dataTransformer.transformListToModel(listOfCrimesEntity)
    }
    private val _crimeIdLiveData = MutableLiveData<String>()

    val crimeLiveData: LiveData<CrimeModel?> = Transformations.map(_crimeIdLiveData) { crimeId ->
        val crimeEntity = dao.getCrime(crimeId).value
        dataTransformer.transformToModel(crimeEntity)
    }

    fun getCrime(crimeId: String) {
        _crimeIdLiveData.value = crimeId
    }


    suspend fun save(crimeModel: CrimeModel) {
        dao.addCrime(dataTransformer.transformToEntity(crimeModel)!!)
    }

    suspend fun delete(crimeModel: CrimeModel) {
        dao.deleteCrime(dataTransformer.transformToEntity(crimeModel)!!)

    }

    suspend fun update(crimeModel: CrimeModel) {
        dao.updateCrime(dataTransformer.transformToEntity(crimeModel)!!)
    }
}