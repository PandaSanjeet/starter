package com.example.android.politicalpreparedness.election

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.politicalpreparedness.database.ElectionDao
import com.example.android.politicalpreparedness.database.ElectionDatabase
import com.example.android.politicalpreparedness.network.models.Election
import com.example.android.politicalpreparedness.network.models.VoterInfoResponse
import com.example.android.politicalpreparedness.repository.ElectionsRepository
import kotlinx.coroutines.launch

class VoterInfoViewModel(val election: Election, application: Application) : AndroidViewModel(application) { // Delete this line
//class VoterInfoViewModel(private val dataSource: ElectionDao) : ViewModel() { // Original

    private val _selectedElection = MutableLiveData<Election>()
    val selectedElection = _selectedElection
    private val database = ElectionDatabase.getInstance(application)
    private val electionsRepository = ElectionsRepository(database)

    //Added live data to hold voter info
    val voterInfo = MutableLiveData<VoterInfoResponse>()

    init {
        _selectedElection.value = election

        viewModelScope.launch {
            //voterInfo.postValue(electionsRepository.voterInfo(8020,"us/ca"))

            _selectedElection.value?.let {
                //voterInfo.postValue(electionsRepository.voterInfo(it.id,it.ocdDivisionId))
                fetchVoterInfo(it.id,it.ocdDivisionId)
            }
        }
    }

    //Added var and methods to populate voter info
    private suspend fun fetchVoterInfo(electionId: Long, address: String){
        //var country = address.removeRange(0,21)
        val country = address.subSequence(21,23)
        val state = if (address.length > 23) { address.subSequence(30,32) } else { null }

        try {
            voterInfo.postValue(electionsRepository.voterInfo(electionId,"$country/$state"))
            //voterInfo.postValue(electionsRepository.voterInfo(8020,"us/ca"))
        }catch (e:Exception){
            Log.d("ExceptionFromfetchVoterInfo", e.toString())
            Toast.makeText(getApplication(),"No election information found. ${e.message.toString()}",Toast.LENGTH_LONG).show()
        }
        println("VoterInfoViewModel "+voterInfo.value?.state?.get(0)?.electionAdministrationBody?.ballotInfoUrl)
        //println("CHINA "+electionId+" "+address+" Editted Country: "+country+" Editted State: "+state)
    }





    //TODO: Add var and methods to support loading URLs

    //TODO: Add var and methods to save and remove elections to local database
    //TODO: cont'd -- Populate initial state of save button to reflect proper action based on election saved status

    /**
     * Hint: The saved state can be accomplished in multiple ways. It is directly related to how elections are saved/removed from the database.
     */

}