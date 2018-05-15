package com.multiplatform.presentation

import com.multiplatform.api.ApiCallback
import com.multiplatform.api.AsyncApi

class AsyncCallPresenter(
        private val api: AsyncApi
) : BasePresenter<AsyncCallView>() {

    override fun onViewAttached() {
        super.onViewAttached()
        makeAsyncCall()
    }

    private fun makeAsyncCall() {
        api.asyncCall(object : ApiCallback {
            override fun result() {
                println("ApiCallback result")
            }
        })
    }
}
