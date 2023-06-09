package com.example.android.politicalpreparedness.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.android.politicalpreparedness.database.ElectionDatabase
import com.example.android.politicalpreparedness.database.UpcomingElections
import com.example.android.politicalpreparedness.database.asDomainModel
import com.example.android.politicalpreparedness.network.CivicsApi
import com.example.android.politicalpreparedness.network.asDatabaseModel
import com.example.android.politicalpreparedness.network.models.Election
import com.example.android.politicalpreparedness.network.models.VoterInfoResponse

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ElectionsRepository(private val database: ElectionDatabase) {

    val allUpcomingElections: LiveData<List<Election>> = Transformations.map(database.electionDao.getAllElections()){
        it?.asDomainModel()
    }

    /*val allSavedElections: LiveData<List<Election>> = Transformations.map(database.electionDao.getSavedElections()){
        it?.asDomainModel()
    }*/

    suspend fun refreshElections(){
        withContext(Dispatchers.IO){
            val networkAllUpcomingElections = CivicsApi.retrofitService.getElections()
            val networkList = networkAllUpcomingElections.elections
            val databaseNetwork = networkList.asDatabaseModel()
            database.electionDao.insertAllElections(*databaseNetwork)
            /*try {
                val networkAllUpcomingElections = CivicsApi.retrofitService.getElections()
                val networkList = networkAllUpcomingElections.elections
                val databaseNetwork = networkList.asDatabaseModel()
                database.electionDao.insertAllElections(*databaseNetwork)
            } catch (e: Exception){
                Log.d("ExceptionInRepo ",e.toString())
            }*/
        }
    }

    suspend fun voterInfo(id: Long, address: String): VoterInfoResponse{
        /*var voterInfoResponse = withContext(Dispatchers.IO){ CivicsApi.retrofitService.getVoterInfo(id, address) }
        val a = 12
        withContext(Dispatchers.IO){
           voterInfoResponse = CivicsApi.retrofitService.getVoterInfo(id,address)

        }
        println("AMC "+voterInfoResponse.state + voterInfoResponse.election.name)*/
            return withContext(Dispatchers.IO) {
                CivicsApi.retrofitService.getVoterInfo(
                    id,
                    address
                )
            }
        //return voterInfoResponse
    }
}