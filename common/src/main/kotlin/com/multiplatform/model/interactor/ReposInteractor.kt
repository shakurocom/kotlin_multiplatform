package com.multiplatform.model.interactor

import com.multiplatform.coroutines.async
import com.multiplatform.model.entity.GithubRepo
import com.multiplatform.model.repository.ReposRepository
import kotlin.coroutines.experimental.CoroutineContext

class ReposInteractor(
        private val repository: ReposRepository,
        private val context: CoroutineContext
) {

    suspend fun getRepos(): List<GithubRepo> {
        return async(context) { repository.getRepositories() }
                .await()
                .map { repo ->
                    repo to async(context) {
                        repository.getBranches(repo)
                    }
                }
                .map { (repo, task) ->
                    repo.branches = task.await()
                    repo
                }
    }
}