package com.pixelmatters.todolistapp.ui.base

import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import com.bluelinelabs.conductor.archlifecycle.LifecycleController
import com.pixelmatters.todolistapp.ActionBarProvider

abstract class BaseController: LifecycleController() {

    private var toolBar: Toolbar? = null
    protected open val title: String = ""

    override fun onAttach(view: View) {
        super.onAttach(view)
        setActionBar()
        setTitle()
    }

    private fun setTitle() {
        toolBar?.title = title
    }

    protected val actionBar: ActionBar?
        get() {
            val actionBarProvider = activity as ActionBarProvider?
            return actionBarProvider?.getSupportActionBar()
        }

    private fun setActionBar() {
        toolBar?.let {
            (activity as? ActionBarProvider)?.setSupportActionBar(it)
        }
        actionBar?.setDisplayHomeAsUpEnabled(false)
    }

}