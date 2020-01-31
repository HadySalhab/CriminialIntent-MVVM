package com.android.myapplication.criminialintent_refactored

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ListCrimesViewModel (val repository: Repository) : ViewModel() {

    val crimeListLiveData = repository.crimesLiveData


    val isEmpty = Transformations.map(crimeListLiveData) {
        it.isNullOrEmpty()
    }

    fun addCrime(crime: CrimeModel) {
        viewModelScope.launch {
            repository.save(crimeModel = crime)
        }
    }
}