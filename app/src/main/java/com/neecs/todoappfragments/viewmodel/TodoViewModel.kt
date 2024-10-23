// app/src/main/java/com/neecs/todoappfragments/viewmodel/TodoViewModel.kt
package com.neecs.todoappfragments.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class TodoItem(val id: Int, val task: String, val isCompleted: Boolean)

class TodoViewModel : ViewModel() {
    private val _todoList = MutableLiveData<List<TodoItem>>()
    val todoList: LiveData<List<TodoItem>> get() = _todoList

    init {
        _todoList.value = listOf()
    }

    fun addTodoItem(item: TodoItem) {
        _todoList.value = _todoList.value?.plus(item)
    }

    fun removeTodoItem(item: TodoItem) {
        _todoList.value = _todoList.value?.minus(item)
    }

    fun markItemAsCompleted(item: TodoItem) {
        _todoList.value = _todoList.value?.map {
            if (it.id == item.id) it.copy(isCompleted = true) else it
        }
    }

    fun getCompletedItems(): List<TodoItem> {
        return _todoList.value?.filter { it.isCompleted } ?: listOf()
    }
}