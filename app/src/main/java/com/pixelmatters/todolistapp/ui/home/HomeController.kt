package com.pixelmatters.todolistapp.ui.home

import android.view.*
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

        view.todo_list.adapter =
            TodoListAdapter(viewModel.todoList, this)
        view.todo_list.addItemDecoration(
            DividerItemDecoration(
                this.activity,
                DividerItemDecoration.VERTICAL
            )
        )

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_add) {
            pushController(AddTodoController())
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val TAG = "HomeController"
    }

    override fun onCheckClick(todo: Todo) {
        todo.isDone = !todo.isDone
    }
}