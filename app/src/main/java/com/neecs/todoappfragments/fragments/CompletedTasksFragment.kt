// app/src/main/java/com/neecs/todoappfragments/fragments/CompletedTasksFragment.kt
package com.neecs.todoappfragments.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.neecs.todoappfragments.R
import com.neecs.todoappfragments.adapter.TodoAdapter
import com.neecs.todoappfragments.viewmodel.TodoViewModel

class CompletedTasksFragment : Fragment() {
    private val viewModel: TodoViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_completed_tasks, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = TodoAdapter { item -> viewModel.markItemAsCompleted(item) }
        recyclerView.adapter = adapter

        viewModel.todoList.observe(viewLifecycleOwner) { todoList ->
            adapter.submitList(viewModel.getCompletedItems())
        }

        return view
    }
}