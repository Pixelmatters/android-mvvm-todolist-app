package com.pixelmatters.todolistapp.util.common

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class ObservableList<T>(protected val list: MutableList<T>) {
    protected val subject: BehaviorSubject<List<T>> = BehaviorSubject.create()
    val observable: Observable<List<T>>
        get() = subject

    fun add(element: T) {
        list.add(element)
        subject.onNext(list)
    }

    fun size(): Int? {
        return subject.value?.size
    }

    fun getValue(): List<T>? {
        return subject.value
    }

}