package com.pixelmatters.todolistapp.util.common

import io.reactivex.Observable
import io.reactivex.subjects.ReplaySubject

class ObservableList<T>(protected val list: MutableList<T>) {
    protected val subject: ReplaySubject<List<T>> = ReplaySubject.create()
    val observable: Observable<List<T>>
        get() = subject

    fun add(element: T) {
        list.add(element)
        subject.onNext(list)
    }

    fun size(): Int {
        return subject.values.size
    }

}