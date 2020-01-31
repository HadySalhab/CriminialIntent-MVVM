package com.android.myapplication.criminialintent_refactored

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class CrimeDetailViewModel(private val repository: Repository,private val crimeId:String?) : ViewModel() {

     val crime = repository.crimeLiveData

    val _suspect = MediatorLiveData<String>()
    val _titleEditText = MediatorLiveData<String>()
    init {
        _suspect.value = ""
        _titleEditText.value = ""
        initializeCrime()
    }

    fun initializeCrime(){
        viewModelScope.launch {
            if(crimeId!=null){
                repository.loadCrime(crimeId)
                updateMediators()
            }
        }
    }
    fun updateMediators(){
        _titleEditText.addSource(crime){ crime->
            _titleEditText.value = crime!!.title
        }
        _suspect.addSource(crime){crime->
            _suspect.value = crime!!.suspect
        }
        _titleEditText.removeSource(crime)
        _suspect.removeSource(crime)
    }

}