package com.pixelmatters.todolistapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import com.pixelmatters.todolistapp.R
import com.pixelmatters.todolistapp.adapter.TodoListAdapter
import com.pixelmatters.todolistapp.util.extension.pushController
import com.pixelmatters.todolistapp.data.model.Todo
import com.pixelmatters.todolistapp.ui.addtodo.AddTodoController
import com.pixelmatters.todolistapp.ui.base.BaseController
import kotlinx.android.synthetic.main.controller_home.view.*


class HomeController : BaseController(), TodoListAdapter.Listener {

    private var viewModel: HomeViewModel = HomeViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.controller_home, container, false)

        view.add_todo.setOnClickListener { pushController(AddTodoController()) }

        view.todo_list.adapter =
            TodoListAdapter(viewModel.todoList, this)
        view.todo_list.addItemDecoration(
            DividerItemDecoration(
                this.activity,
                DividerItemDecoration.VERTICAL
            )
        )

        return view
    }

    companion object {
        private const val TAG = "HomeController"
    }

    override fun onCheckClick(todo: Todo) {
        todo.isDone = !todo.isDone
    }
}