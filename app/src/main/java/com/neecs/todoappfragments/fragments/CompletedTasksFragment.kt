package com.neecs.todoappfragments.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.neecs.todoappfragments.R
import com.neecs.todoappfragments.adapter.CompletedTasksAdapter
import com.neecs.todoappfragments.db.AppDatabase
import com.neecs.todoappfragments.entities.Task
import com.neecs.todoappfragments.viewmodel.TodoViewModel
import kotlinx.coroutines.launch

class CompletedTasksFragment : Fragment() {
    private val viewModel: TodoViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_completed_tasks, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewCompletedTasks)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = CompletedTasksAdapter()
        recyclerView.adapter = adapter

        // Obtener la base de datos
        val database = AppDatabase.getDatabase(requireContext())
        val taskDao = database.taskDao()

        // Usar coroutine para obtener las tareas completadas desde la base de datos
        lifecycleScope.launch {
            val completedTasks: List<Task> = taskDao.getAllTasks().filter { it.isCompleted }
            adapter.submitList(completedTasks)
        }

        return view
    }
}