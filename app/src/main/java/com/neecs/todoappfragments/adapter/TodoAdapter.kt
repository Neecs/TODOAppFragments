// app/src/main/java/com/neecs/todoappfragments/adapter/TodoAdapter.kt
package com.neecs.todoappfragments.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.neecs.todoappfragments.R
import com.neecs.todoappfragments.viewmodel.TodoItem

class TodoAdapter(private val onItemChecked: (TodoItem) -> Unit) : ListAdapter<TodoItem, TodoAdapter.TodoViewHolder>(TodoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(view, onItemChecked)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class TodoViewHolder(itemView: View, private val onItemChecked: (TodoItem) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val taskTextView: TextView = itemView.findViewById(R.id.taskTextView)
        private val checkBox: CheckBox = itemView.findViewById(R.id.checkBox)

        fun bind(item: TodoItem) {
            taskTextView.text = item.task
            checkBox.isChecked = item.isCompleted
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    onItemChecked(item)
                }
            }
        }
    }

    class TodoDiffCallback : DiffUtil.ItemCallback<TodoItem>() {
        override fun areItemsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
            return oldItem == newItem
        }
    }
}