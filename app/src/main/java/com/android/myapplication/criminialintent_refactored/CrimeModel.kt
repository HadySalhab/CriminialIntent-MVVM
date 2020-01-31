package com.android.myapplication.criminialintent_refactored

import java.util.*

data class CrimeModel( val id: String = UUID.randomUUID().toString(),
                       var title: String = "",
                       var date: Date = Date(),
                       var isSolved: Boolean = false,
                       var suspect: String = "") {

}