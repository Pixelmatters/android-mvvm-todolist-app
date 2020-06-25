package com.pixelmatters.todolistapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pixelmatters.todolistapp.R
import com.pixelmatters.todolistapp.data.model.Todo
import kotlinx.android.synthetic.main.row_todo_item.view.*

class TodoListAdapter(private val dataList: List<Todo>, private val listener: Listener) : RecyclerView.Adapter<TodoListAdapter.ViewHolder>() {

    interface Listener {

        fun onCheckClick(todo: Todo)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position], listener)
    }

    override fun getItemCount(): Int = dataList.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_todo_item, parent, false)

        return ViewHolder(view)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(todo: Todo, listener: Listener) {

            itemView.row_todo_item_title.text = todo.title
            itemView.row_todo_item_check.isChecked = todo.isDone

            itemView.row_todo_item_check.setOnClickListener { listener.onCheckClick(todo) }
        }
    }
}