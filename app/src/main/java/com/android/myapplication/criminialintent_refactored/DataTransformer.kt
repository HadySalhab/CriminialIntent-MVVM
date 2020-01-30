package com.android.myapplication.criminialintent_refactored

import com.android.myapplication.criminialintent_refactored.database.CrimeEntity

class DataTransformer {
    fun transformToEntity(crimeModel: CrimeModel?):CrimeEntity?{
        if(crimeModel==null){
            return null
        }
            return CrimeEntity(
                id = crimeModel.id,
                date = crimeModel.date,
                title = crimeModel.title,
                isSolved = crimeModel.isSolved,
                suspect = crimeModel.suspect
            )
    }

    fun transformToModel(crimeEntity: CrimeEntity?):CrimeModel?{
        if(crimeEntity==null){
            return null
        }
        return CrimeModel(
            id = crimeEntity.id,
            date = crimeEntity.date,
            title = crimeEntity.title,
            isSolved = crimeEntity.isSolved,
            suspect = crimeEntity.suspect
        )
    }

    fun transformListToModel(listOfCrimesEntity:List<CrimeEntity>?):List<CrimeModel>?{
        if(listOfCrimesEntity==null){
            return  null
        }
        val listOfCrimesModel = mutableListOf<CrimeModel>()
        listOfCrimesEntity.forEach { crimeEntity->
            val crimeModel = CrimeModel(
                id = crimeEntity.id,
                date = crimeEntity.date,
                title = crimeEntity.title,
                isSolved = crimeEntity.isSolved,
                suspect = crimeEntity.suspect
            )
            listOfCrimesModel.add(crimeModel)
        }
        return listOfCrimesModel
    }

}