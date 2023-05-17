package com.example.android.politicalpreparedness.election

import android.app.Application
import androidx.lifecycle.*
import com.example.android.politicalpreparedness.database.ElectionDatabase.Companion.getInstance
import com.example.android.politicalpreparedness.network.models.Election
import com.example.android.politicalpreparedness.repository.ElectionsRepository
import kotlinx.coroutines.launch

//TODO: Construct ViewModel and provide election datasource
class ElectionsViewModel(application: Application): AndroidViewModel(application) {

    private val database = getInstance(application)
    private val electionsRepository = ElectionsRepository(database)

    init {
        viewModelScope.launch {
            electionsRepository.refreshElections()
        }
    }

    //Created val for upcoming elections
    val upcomingElections = electionsRepository.allUpcomingElections

    //Created val for saved elections
    //val savedElections = electionsRepository.allSavedElections

    //Created val and functions to populate live data for upcoming elections from the API and saved elections from local database
    private val _navigateToSelectedUpcomingElection = MutableLiveData<Election>()
    val navigateToSelectedUpcomingElection = _navigateToSelectedUpcomingElection

    fun onUpcomingElectionsItemClicked(upcomingElectionItem: Election) {
        _navigateToSelectedUpcomingElection.value = upcomingElectionItem
    }

    fun onUpcomingElectionsItemNavigated(){
        _navigateToSelectedUpcomingElection.value = null
    }

    //TODO: Create functions to navigate to saved or upcoming election voter info

}