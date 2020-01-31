package com.android.myapplication.criminialintent_refactored

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class ListCrimesViewModel (val repository: Repository) : ViewModel() {

    val crimeListLiveData = repository.crimesLiveData
    private val _navigate = MutableLiveData<Boolean>(false)
    val navigate:LiveData<Boolean>
    get() = _navigate


    val isEmpty = Transformations.map(crimeListLiveData) {
        it.isNullOrEmpty()
    }

    fun addCrime(crime: CrimeModel) {
        viewModelScope.launch {
            repository.save(crimeModel = crime)
        }
    }
    fun navigateToEdit(){
        _navigate.value = true
    }
    fun resetNavigation(){
        _navigate.value = false
    }
}