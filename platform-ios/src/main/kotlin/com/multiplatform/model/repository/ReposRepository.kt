package com.multiplatform.model.repository

import com.multiplatform.model.entity.GithubBranch
import com.multiplatform.model.entity.GithubRepo

import kotlin.coroutines.experimental.*
import kotlin.coroutines.experimental.intrinsics.*

actual open class ReposRepository {

    actual suspend fun getRepositories(): List<GithubRepo> {
        return suspendCoroutineOrReturn { continuation ->
            getRepositories(continuation)
            COROUTINE_SUSPENDED
        }
    }

    actual suspend fun getBranches(repo: GithubRepo): List<GithubBranch> {
        return suspendCoroutineOrReturn { continuation ->
            getBranches(repo, continuation)
            COROUTINE_SUSPENDED
        }
    }

    open fun getRepositories(callback: Continuation<List<GithubRepo>>) {
        throw NotImplementedError("iOS project should implement this")
    }

    open fun getBranches(repo: GithubRepo, callback: Continuation<List<GithubBranch>>) {
        throw NotImplementedError("iOS project should implement this")
    }
}