package com.example.android.politicalpreparedness.network.models
//THIS FILE NEEDS TO BE DELETED. NO USE. ANOTHER FILE CREATED IN database PACKAGE(ElectionEntities)


import android.os.Parcelable
import androidx.room.*
import com.example.android.politicalpreparedness.database.UpcomingElections
import com.squareup.moshi.*
import kotlinx.android.parcel.Parcelize
import java.util.*
import kotlin.collections.List

@Parcelize
data class Election(
        val id: Long,
        val name: String,
        val electionDay: String,
        val division: String
        //val division: Division
) : Parcelable

/*
fun List<Election>.asDatabaseModel(): Array<UpcomingElections>{
        return this.map {
                UpcomingElections(
                        id = it.id,
                        name = it.name,
                        electionDay = it.electionDay,
                        division = it.division
                )
        }.toTypedArray()
}*/
