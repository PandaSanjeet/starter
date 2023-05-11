package com.example.android.politicalpreparedness.database

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.android.politicalpreparedness.network.models.Division
import com.example.android.politicalpreparedness.network.models.Election
import com.squareup.moshi.Json
import java.util.Date

/*data class UpcomingElections(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val name: String,
    val electionDay: Date,
    @Embedded(prefix = "division")
    val division: Division
)*/
@Entity(tableName = "election_table")
data class UpcomingElections(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "name")val name: String,
    @ColumnInfo(name = "electionDay")val electionDay: String,
    @Json(name="ocdDivisionId") val division: String
    //@Embedded(prefix = "division_") @Json(name="ocdDivisionId") val division: Division
)/*{constructor() : this(0,"","","") }*/

/*@Entity(tableName = "saved_elections")
data class SavedElections(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L
)*/

fun List<UpcomingElections>.asDomainModel(): List<UpcomingElections>{
    return map {
        UpcomingElections(
            id = it.id,
            name = it.name,
            electionDay = it.electionDay,
            division = it.division
        )
    }
}
