package com.pixelmatters.todolistapp.ui.home

import androidx.lifecycle.ViewModel
import com.pixelmatters.todolistapp.util.common.ObservableList
import com.pixelmatters.todolistapp.data.model.Todo
import com.pixelmatters.todolistapp.util.messenger.Messenger
import com.pixelmatters.todolistapp.util.messenger.TodoAddMessage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class HomeViewModel : ViewModel() {

    var observableTodoList =
        ObservableList<Todo>(ArrayList())
    private var disposable: Disposable

    init {
        disposable =
            Messenger.subscribe<TodoAddMessage>()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    addTodo(it.content)
                }
    }

    fun addTodo(todo: Todo) {
        observableTodoList.add(todo)
    }

    override fun onCleared() {
        super.onCleared()
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
    }

}