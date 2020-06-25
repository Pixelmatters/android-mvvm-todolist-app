package com.pixelmatters.todolistapp.ui.addtodo

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pixelmatters.todolistapp.R
import com.pixelmatters.todolistapp.util.extension.hideKeyboard
import com.pixelmatters.todolistapp.util.extension.popCurrentController
import com.pixelmatters.todolistapp.util.messenger.Messenger
import com.pixelmatters.todolistapp.util.messenger.TodoAddMessage
import com.pixelmatters.todolistapp.ui.base.BaseBackButtonController
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.controller_add_todo.view.*


class AddTodoController: BaseBackButtonController() {

    private var viewModel: AddTodoViewModel = AddTodoViewModel()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.controller_add_todo,container,false)

        view.add_todo_button.setOnClickListener { viewModel.createTodo()}

        val textChangeStream = createTextChangeObservable(view)
            .toFlowable(BackpressureStrategy.BUFFER)

        val textDisposable = textChangeStream
            .observeOn(AndroidSchedulers.mainThread())
            .observeOn(Schedulers.io())
            .subscribe(){
                viewModel.todoTitle = it
            }

        val messageDisposable =
            Messenger.subscribe<TodoAddMessage>()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    hideKeyboard()
                    popCurrentController()
                }

        compositeDisposable.addAll(textDisposable,messageDisposable)
        return view
    }

    private fun createTextChangeObservable(view: View): Observable<String> {

        return Observable.create { emitter ->

            val textWatcher = object : TextWatcher {

                override fun afterTextChanged(s: Editable?) = Unit

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

                override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    s?.toString()?.let { emitter.onNext(it) }
                }

            }

            view.add_todo_edit_text.addTextChangedListener(textWatcher)

            emitter.setCancellable {
                view.add_todo_edit_text.removeTextChangedListener(textWatcher)
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    companion object {
        private const val TAG = "AddTodoController"
    }
}