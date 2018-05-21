package com.multiplatform.model.repository

import com.multiplatform.model.entity.GithubRepo

actual class ReposRepository(
        private val userName: String,
        private val api: GithubApi
) {

    actual suspend fun getRepositories() =
            api.getRepositories(userName)
                    .await()

    actual suspend fun getBranches(repo: GithubRepo) =
            api.getBranches(userName, repo.name)
                    .await()
                    .map { branch -> branch.toGithubBranch() }
}

