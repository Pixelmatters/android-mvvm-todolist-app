package com.pixelmatters.todolistapp.ui.home

import android.util.Log
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
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.controller_home.view.*


class HomeController : BaseController(), TodoListAdapter.Listener {

    private var viewModel: HomeViewModel = HomeViewModel()
    private var disposable: Disposable

    init {
        disposable =
            viewModel.observableTodoList.observable.subscribe {
                view?.todo_list?.adapter = TodoListAdapter(it, this)
                view?.todo_list?.addItemDecoration(
                    DividerItemDecoration(
                        this.activity,
                        DividerItemDecoration.VERTICAL
                    )
                )
            }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.controller_home, container, false)

        view.add_todo.setOnClickListener { pushController(AddTodoController()) }

        view.todo_list.adapter =
            TodoListAdapter(viewModel.observableTodoList.getValue() ?: emptyList(), this)
        view.todo_list.addItemDecoration(
            DividerItemDecoration(
                this.activity,
                DividerItemDecoration.VERTICAL
            )
        )

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
    }

    companion object {
        private const val TAG = "HomeController"
    }

    override fun onCheckClick(todo: Todo) {
        todo.isDone = !todo.isDone
    }
}