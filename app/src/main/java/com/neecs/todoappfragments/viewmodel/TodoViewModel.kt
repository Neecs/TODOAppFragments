package com.neecs.todoappfragments.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class TodoItem(val id: Int, val task: String, val isCompleted: Boolean)

class TodoViewModel : ViewModel() {
    private val _todoList = MutableLiveData<List<TodoItem>>()
    val todoList: LiveData<List<TodoItem>> get() = _todoList

    init {
        _todoList.value = listOf() // Initialize with an empty list or some default values
    }

    fun getTaskById(taskId: Int): TodoItem? {
        return _todoList.value?.find { it.id == taskId }
    }

    fun addTodoItem(item: TodoItem) {
        _todoList.value = _todoList.value?.toMutableList()?.apply { add(item) }
    }

    fun markItemAsCompleted(item: TodoItem) {
        _todoList.value = _todoList.value?.map {
            if (it.id == item.id) it.copy(isCompleted = true) else it
        }
    }

    fun getCompletedItems(): List<TodoItem> {
        return _todoList.value?.filter { it.isCompleted } ?: emptyList()
    }
}