package com.example.todoist.task.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TaskDao {

    @Query("SELECT * FROM TaskEntity WHERE projectId = :projectId")
    suspend fun getTasksByProjectId(projectId: String): List<TaskEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTaskList(tasks: List<TaskEntity>)
}