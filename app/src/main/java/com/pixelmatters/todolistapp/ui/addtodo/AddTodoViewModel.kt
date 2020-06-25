package com.pixelmatters.todolistapp.ui.addtodo

import androidx.lifecycle.ViewModel
import com.pixelmatters.todolistapp.data.model.Todo
import com.pixelmatters.todolistapp.util.messenger.Messenger
import com.pixelmatters.todolistapp.util.messenger.TodoAddMessage

class AddTodoViewModel: ViewModel() {
    var todoTitle: String = ""

    fun createTodo() {
        if (todoTitle.isEmpty()) return
        Messenger.post(TodoAddMessage(Todo(todoTitle)))
    }
}