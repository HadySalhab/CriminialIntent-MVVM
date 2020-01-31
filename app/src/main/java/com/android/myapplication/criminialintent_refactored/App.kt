package com.android.myapplication.criminialintent_refactored

import android.app.Application
import com.android.myapplication.criminialintent_refactored.database.CrimeDb
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {
    private val koinModule = module {

        single {
            //Database
            CrimeDb.newInstance(androidContext())
        }
        single {
            val db: CrimeDb = get()
            Repository(db.crimeDao(), DataTransformer())
        }
        viewModel {
            ListCrimesViewModel(get())
        }
        viewModel {
            CrimeDetailViewModel(get())
        }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(koinModule)
        }
    }
}