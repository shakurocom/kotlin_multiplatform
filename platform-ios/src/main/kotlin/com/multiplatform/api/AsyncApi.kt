package com.multiplatform.api

import platform.darwin.*

actual open class AsyncApi {

    actual fun asyncCall(callback: ApiCallback) {
        dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT.toLong(), 0)) {
            println("before callback invocation")
            callback.result()
            println("after callback invocation")
        }
    }
}
