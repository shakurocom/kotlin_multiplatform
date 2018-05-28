package com.multiplatform.model.repository

import com.multiplatform.model.entity.GithubBranch
import com.multiplatform.model.entity.GithubRepo

expect class ReposRepository {
    suspend fun getRepositories(): List<GithubRepo>
    suspend fun getBranches(repo: GithubRepo): List<GithubBranch>
}