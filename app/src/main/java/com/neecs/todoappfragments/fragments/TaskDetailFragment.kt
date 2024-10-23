// app/src/main/java/com/neecs/todoappfragments/fragments/TaskDetailFragment.kt
package com.neecs.todoappfragments.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.neecs.todoappfragments.R
import com.neecs.todoappfragments.viewmodel.TodoViewModel

class TaskDetailFragment : Fragment() {
    private val viewModel: TodoViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_task_detail, container, false)
        val taskId = arguments?.getInt("taskId") ?: return view
        val task = viewModel.todoList.value?.find { it.id == taskId } ?: return view

        view.findViewById<TextView>(R.id.taskDetail).text = task.task

        return view
    }
}