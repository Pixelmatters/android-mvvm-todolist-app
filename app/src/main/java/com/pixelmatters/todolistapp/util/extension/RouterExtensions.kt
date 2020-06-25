package com.pixelmatters.todolistapp.util.extension

import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler


fun <T : Controller> Router?.pushController(
    controller: T,
    pushChangeHandler: HorizontalChangeHandler = HorizontalChangeHandler(),
    popChangeHandler: HorizontalChangeHandler = HorizontalChangeHandler()
) {
    this?.pushController(
        RouterTransaction.with(controller)
            .pushChangeHandler(pushChangeHandler).popChangeHandler(popChangeHandler)
    )
}

fun <T : Controller> Controller.pushController(
    controller: T,
    pushChangeHandler: HorizontalChangeHandler = HorizontalChangeHandler(),
    popChangeHandler: HorizontalChangeHandler = HorizontalChangeHandler()
) {
    this.router?.pushController(controller, pushChangeHandler, popChangeHandler)
}

fun Controller.popCurrentController() {
    this.router?.popCurrentController()
}

fun Controller.popToRoot() {
    this.router?.popToRoot()
}