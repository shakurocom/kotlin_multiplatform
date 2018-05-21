package com.multiplatform.presentation.base

abstract class BasePresenter<V : BaseView> {

    protected var view: V? = null
        private set

    fun attach(view: V) {
        this.view = view
        onViewAttached()
    }

    fun detach() {
        this.view = null
        onViewDetached()
    }

    open fun onViewAttached() {

    }

    open fun onViewDetached() {

    }
}


