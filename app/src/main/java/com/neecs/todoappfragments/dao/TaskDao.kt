package com.neecs.todoappfragments.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.neecs.todoappfragments.entities.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks")
    suspend fun getAllTasks(): List<Task>

    @Insert
    suspend fun insertTask(task: Task)
}