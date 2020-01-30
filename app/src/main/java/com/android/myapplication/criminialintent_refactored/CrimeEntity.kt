package com.android.myapplication.criminialintent_refactored

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class CrimeEntity(@PrimaryKey val id: String = UUID.randomUUID().toString(),
                 var title: String = "",
                 var date: Date = Date(),
                 var isSolved: Boolean = false,
                 var suspect: String = "")

