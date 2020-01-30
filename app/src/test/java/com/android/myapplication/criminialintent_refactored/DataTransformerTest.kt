package com.android.myapplication.criminialintent_refactored

import com.android.myapplication.criminialintent_refactored.database.CrimeEntity
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.*
import org.hamcrest.core.Is.`is`
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.w3c.dom.Entity

class DataTransformerTest {

    private lateinit var SUT:DataTransformer
    private val CRIMEMODEL= CrimeModel()
    private val CRIMEENTITY= CrimeEntity()

    @Before
    fun setUp() {
        SUT = DataTransformer()
    }

    @Test
    fun transformListToModel_emptyListCrimeEntity_shouldReturnEmptyListCrimeModule() {
        val crimes = listOf<CrimeEntity>()
        val result = SUT.transformListToModel(crimes)
        assertEquals(0,result!!.size)
    }

    @Test
    fun transformListToModel_null_shouldReturnNull() {
        val result = SUT.transformListToModel(null)
        assertNull(result)
    }

    @Test
    fun transformListToModel_listOfCrimeEntity_shouldReturnListOfCrimeModule(){
        val listOfCrimeEntity = listOf<CrimeEntity>(CRIMEENTITY)
        val result = SUT.transformListToModel(listOfCrimeEntity)
        assertEquals(1,result!!.size)
        assertEquals(CRIMEENTITY.id,result[0].id)
        assertEquals(CRIMEENTITY.title,result[0].title)
        assertEquals(CRIMEENTITY.date,result[0].date)
        assertEquals(CRIMEENTITY.isSolved,result[0].isSolved)
        assertEquals(CRIMEENTITY.suspect,result[0].suspect)
        assertThat(result[0], CoreMatchers.`is`(instanceOf(CrimeModel::class.java)))
    }

    @Test
    fun transformToModel_null_shouldReturnNull(){
        val result = SUT.transformToModel(null)
        assertNull(result)
    }

    @Test
    fun transformToModel_crimeEntity_shouldReturnCrimeModel(){
        val result = SUT.transformToModel(CRIMEENTITY)!!
        assertEquals(CRIMEENTITY.id,result.id)
        assertEquals(CRIMEENTITY.title,result.title)
        assertEquals(CRIMEENTITY.date,result.date)
        assertEquals(CRIMEENTITY.isSolved,result.isSolved)
        assertEquals(CRIMEENTITY.suspect,result.suspect)
        assertThat(result, CoreMatchers.`is`(instanceOf(CrimeModel::class.java)))
    }


    @Test
    fun transformToEntity_null_shouldReturnNull(){
        val result = SUT.transformToEntity(null)
        assertNull(result)
    }

    @Test
    fun transformToEntity_crimeModel_shouldReturnCrimeEntity(){
        val result = SUT.transformToEntity(CRIMEMODEL)!!
        assertEquals(CRIMEMODEL.id,result.id)
        assertEquals(CRIMEMODEL.title,result.title)
        assertEquals(CRIMEMODEL.date,result.date)
        assertEquals(CRIMEMODEL.isSolved,result.isSolved)
        assertEquals(CRIMEMODEL.suspect,result.suspect)
        assertThat(result, CoreMatchers.`is`(instanceOf(CRIMEENTITY::class.java)))
    }

}

