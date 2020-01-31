package com.android.myapplication.criminialintent_refactored

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class CrimeDetailViewModel(private val repository: Repository, private val crimeId: String?) :
    ViewModel() {
    companion object {
        private const val TAG = "CrimeDetailViewModel"
    }

    private lateinit var editCrime: CrimeModel




    private val _warningMessage = MutableLiveData<Boolean>()
    val warningMessage: LiveData<Boolean>
        get() = _warningMessage

    private val _navigateUp = MutableLiveData<Boolean>()
    val navigateUp: LiveData<Boolean>
        get() = _navigateUp

    val _suspect = MutableLiveData<String>()
    val _titleEditText = MutableLiveData<String>()

    init {
        initializeCrime()
    }

    fun initializeCrime() {
        viewModelScope.launch {
            if (crimeId != null) {
                editCrime = repository.loadCrime(crimeId)?:CrimeModel()
            }else{
                editCrime = CrimeModel()
            }
            updateUI()
        }
    }


    fun updateUI() {
        _titleEditText.value = editCrime.title
        _suspect.value = editCrime.suspect
    }

    fun saveCrime() {
        if (_titleEditText.value.isNullOrEmpty() || _suspect.value.isNullOrEmpty()) {
            _warningMessage.value = true
        } else {
            updateCrime()
            viewModelScope.launch {
                repository.save(editCrime)
                _navigateUp.value = true
            }
        }
    }

    fun updateCrime() {
        editCrime.apply {
            title = _titleEditText.value!!
            suspect = _suspect.value!!
        }
    }

    fun resetWarningMessage() {
        _warningMessage.value = false
    }

    fun resetNavigateUp() {
        _navigateUp.value = false
    }

    fun deleteCrime() {
        viewModelScope.launch {
            repository.delete(editCrime)
            _navigateUp.value = true
        }
    }
}

