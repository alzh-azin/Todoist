package com.example.todoist.project.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface ProjectDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProjectList(projects: List<ProjectEntity>)

    @Transaction
    @Query("SELECT * FROM ProjectEntity")
    suspend fun getProjectList(): List<ProjectEntity>

    @Query("DELETE FROM ProjectEntity")
    suspend fun deleteAllProjects()

}