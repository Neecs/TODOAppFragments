package com.neecs.todoappfragments.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.neecs.todoappfragments.R
import com.neecs.todoappfragments.adapter.TodoAdapter
import com.neecs.todoappfragments.entities.Task
import com.neecs.todoappfragments.viewmodel.TodoViewModel

class TodoListFragment : Fragment() {
    private val viewModel: TodoViewModel by activityViewModels()

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_todo_list, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = TodoAdapter { item ->
            viewModel.markItemAsCompleted(item)
            val action = TodoListFragmentDirections.actionTodoListFragmentToCompletedTasksFragment(item.id)
            findNavController().navigate(action)
        }
        recyclerView.adapter = adapter

        viewModel.todoList.observe(viewLifecycleOwner) { todoList ->
            adapter.submitList(todoList)
        }

        val editTextTaskTitle = view.findViewById<EditText>(R.id.editTextTaskTitle)
        val buttonAddTask = view.findViewById<Button>(R.id.buttonAddTask)
        buttonAddTask.setOnClickListener {
            val taskTitle = editTextTaskTitle.text.toString()
            if (taskTitle.isNotEmpty()) {
                val newItem = Task(title = taskTitle, description = "", isCompleted = false)
                viewModel.addTodoItem(newItem)
                editTextTaskTitle.text.clear()
            }
        }

        return view
    }
}