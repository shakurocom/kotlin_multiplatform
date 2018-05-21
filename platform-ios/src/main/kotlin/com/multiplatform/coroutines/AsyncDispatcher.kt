package com.multiplatform.coroutines

import kotlin.coroutines.experimental.*
import kotlin.coroutines.experimental.intrinsics.*

import konan.worker.*

class DataObject<T>(val value: T, val continuation: Continuation<T>)
class ErrorObject<T>(val exception: Throwable, val continuation: Continuation<T>)

class AsyncDispatcher : ContinuationDispatcher() {

    val worker = startWorker()

    override fun <T> dispatchResume(value: T, continuation: Continuation<T>): Boolean {
        worker.schedule(TransferMode.UNCHECKED, {DataObject(value, continuation)}) {
            it.continuation.resume(it.value)
        }
        return true
    }

    override fun dispatchResumeWithException(exception: Throwable, continuation: Continuation<*>): Boolean {
        worker.schedule(TransferMode.UNCHECKED, {ErrorObject(exception, continuation)}) {
            it.continuation.resumeWithException(it.exception)
        }
        return false
    }
}