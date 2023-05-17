package com.example.android.politicalpreparedness.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.android.politicalpreparedness.network.models.Election

@Dao
interface ElectionDao {

    //Added insert query for upcoming elections
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllElections(vararg elections: UpcomingElections)

    //Added select all election query
    @Query("SELECT * FROM election_table")
    fun getAllElections(): LiveData<List<UpcomingElections>>

    //Added insert query for saved elections
    /*@Insert(onConflict = OnConflictStrategy.REPLACE) // Add properly
    suspend fun insertSavedElections(vararg elections: UpcomingElections)*/

    //Added select single election query
    /*@Query("SELECT * FROM election_table INNER JOIN saved_elections ON election_table.id = saved_elections.id")
    fun getSavedElections():LiveData<List<UpcomingElections>>*/

    //Added delete query
    /*@Query("DELETE FROM saved_elections WHERE saved_elections.id = :id")
    suspend fun deleteSavedElections(id: Int)*/

    //TODO: Add clear query

}