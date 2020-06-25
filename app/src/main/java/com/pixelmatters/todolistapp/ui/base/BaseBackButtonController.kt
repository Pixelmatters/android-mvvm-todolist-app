package com.pixelmatters.todolistapp.ui.base

import android.R.id.home
import android.view.MenuItem
import android.view.View
import com.pixelmatters.todolistapp.util.extension.popCurrentController

abstract class BaseBackButtonController : BaseController {

    protected open val isSetBackButton = true

    constructor()

    init {
        retainViewMode = RetainViewMode.RETAIN_DETACH
        setHasOptionsMenu(true)
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        if(isSetBackButton) setBackButton()
    }

    protected fun setBackButton() {
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            home -> {
                goBack()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    protected open fun goBack() {
        popCurrentController()
    }
}
