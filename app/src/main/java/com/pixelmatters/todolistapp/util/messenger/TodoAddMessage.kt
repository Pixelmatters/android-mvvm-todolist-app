package com.pixelmatters.todolistapp.util.messenger

import com.pixelmatters.todolistapp.data.model.Todo

class TodoAddMessage(newTodo: Todo) : Message {
    override var content = newTodo
}