package com.multiplatform.coroutines

import kotlin.coroutines.experimental.Continuation
import kotlin.coroutines.experimental.CoroutineContext

expect fun <T> async(context: CoroutineContext, block: suspend () -> T): Deferred<T>

expect fun <T> launch(context: CoroutineContext, block: suspend () -> T)

expect suspend fun <T> withContext(context: CoroutineContext, block: suspend () -> T): T

expect class Deferred<out T> {
    suspend fun await(): T
}

open class EmptyContinuation(override val context: CoroutineContext) : Continuation<Any?> {

    companion object : EmptyContinuation(context)

    override fun resume(value: Any?) = Unit

    override fun resumeWithException(exception: Throwable) {
        throw exception
    }
}

open class WrappedContinuation<in T>(
        override val context: CoroutineContext,
        val continuation: Continuation<T>
) : Continuation<T> {

    companion object : WrappedContinuation<Any>(context, continuation)

    override fun resume(value: T) = continuation.resume(value)

    override fun resumeWithException(exception: Throwable) = continuation.resumeWithException(exception)
}