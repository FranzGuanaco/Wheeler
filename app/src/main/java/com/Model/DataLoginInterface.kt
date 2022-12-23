package com.Model

import androidx.room.*

@Dao

interface DataLoginInterface {

    @Query("select * from contact")
    fun getAll(): List<User>

    @Query("delete from contact")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg user: User)

}


// action de la base de donnée (suspend ?)