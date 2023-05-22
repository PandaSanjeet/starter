package com.example.android.politicalpreparedness.network

import com.example.android.politicalpreparedness.database.UpcomingElections
import com.example.android.politicalpreparedness.network.models.AdministrationBody
import com.example.android.politicalpreparedness.network.models.Division
import com.example.android.politicalpreparedness.network.models.Election
import com.squareup.moshi.Json
import java.util.*

data class NetworkElectionContainer(val elections: List<NetworkElection>, val kind: String)

data class NetworkElection(val id: Long, val name: String, val electionDay: String, @Json(name = "ocdDivisionId") val division: String)
//data class NetworkElection(val id: Long, val name: String, val electionDay: String, @Json(name = "ocdDivisionId") val division: Division)

data class NetworkDivision(val id: String, val country: String, val state: String)

/*data class NetworkVoterInfoContainer(val election: NetworkElection, val state: kotlin.collections.List<NetworkState>)

data class NetworkState(val name: String, val electionAdministrationBody: NetworkAdministrationBody)

data class NetworkAdministrationBody(val votingLocationFinderUrl: String? = null, val ballotInfoUrl: String? = null)*/

fun NetworkDivision.asDomainModal():Division{
    return Division(
        id = this.id,
        country = this.country,
        state = this.state
    )
}

fun NetworkDivision.asDatabaseModal():Division{
    return Division(
        id = this.id,
        country = this.country,
        state = this.state
    )
}

fun kotlin.collections.List<NetworkElection>.asDomainModel(): kotlin.collections.List<Election>{
    return this.map {
        Election(
            id = it.id,
            name = it.name,
            electionDay = it.electionDay,
            ocdDivisionId = it.division
            //division = it.division.asDomainModal()
        )
    }
}

fun kotlin.collections.List<NetworkElection>.asDatabaseModel(): Array<UpcomingElections>{
    return this.map {
        UpcomingElections(
            id = it.id,
            name = it.name,
            electionDay = it.electionDay,
            division = it.division
            //division = it.division.asDatabaseModal()
        )
    }.toTypedArray()
}