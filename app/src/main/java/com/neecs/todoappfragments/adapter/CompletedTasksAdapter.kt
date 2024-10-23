package com.neecs.todoappfragments.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.neecs.todoappfragments.R
import com.neecs.todoappfragments.viewmodel.TodoItem

class CompletedTasksAdapter :
    ListAdapter<TodoItem, CompletedTasksAdapter.CompletedTaskViewHolder>(
        CompletedTaskViewHolder.CompletedTaskDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompletedTaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_completed_task, parent, false)
        return CompletedTaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: CompletedTaskViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class CompletedTaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val taskTextView: TextView = itemView.findViewById(R.id.taskTextView)

        fun bind(item: TodoItem) {
            taskTextView.text = item.task
        }

        class CompletedTaskDiffCallback : DiffUtil.ItemCallback<TodoItem>() {
            override fun areItemsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}