package com.multiplatform.api

expect class AsyncApi {
    fun asyncCall(callback: ApiCallback)
}