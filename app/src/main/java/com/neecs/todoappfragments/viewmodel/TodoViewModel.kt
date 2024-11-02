package com.neecs.todoappfragments.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.neecs.todoappfragments.db.AppDatabase
import com.neecs.todoappfragments.entities.Task
import kotlinx.coroutines.launch

data class TodoItem(val id: Int, val task: String, val isCompleted: Boolean)

class TodoViewModel(application: Application) : AndroidViewModel(application) {
    private val taskDao = AppDatabase.getDatabase(application).taskDao()
    private val _todoList = MutableLiveData<List<TodoItem>>()
    val todoList: LiveData<List<TodoItem>> get() = _todoList

    init {
        viewModelScope.launch {
            _todoList.value = taskDao.getAllTasks().map { task ->
                TodoItem(id = task.id, task = task.title, isCompleted = task.isCompleted)
            }
        }
    }

    fun addTodoItem(task: Task) {
        viewModelScope.launch {
            taskDao.insertTask(task)
            _todoList.value = taskDao.getAllTasks().map { task ->
                TodoItem(id = task.id, task = task.title, isCompleted = task.isCompleted)
            }
        }
    }

    fun markItemAsCompleted(item: TodoItem) {
        viewModelScope.launch {
            val updatedTask = Task(id = item.id, title = item.task, description = "", isCompleted = true)
            taskDao.insertTask(updatedTask)
            _todoList.value = taskDao.getAllTasks().map { task ->
                TodoItem(id = task.id, task = task.title, isCompleted = task.isCompleted)
            }
        }
    }
}