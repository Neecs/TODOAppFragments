package com.neecs.todoappfragments.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.neecs.todoappfragments.R
import com.neecs.todoappfragments.viewmodel.TodoViewModel

class TaskDetailsFragment : Fragment() {
    private val viewModel: TodoViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_task_details, container, false)
        val textViewTaskTitle = view.findViewById<TextView>(R.id.textViewTaskTitle)
        val checkBoxTaskCompleted = view.findViewById<CheckBox>(R.id.checkBoxTaskCompleted)

        val taskId = arguments?.getInt("taskId") ?: return view
        val task = viewModel.getTaskById(taskId)

        task?.let {
            textViewTaskTitle.text = it.task
            checkBoxTaskCompleted.isChecked = it.isCompleted
        }

        return view
    }
}