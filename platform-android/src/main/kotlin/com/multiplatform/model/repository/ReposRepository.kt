package com.multiplatform.model.repository

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.multiplatform.model.api.GithubApi
import com.multiplatform.model.entity.GithubRepo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

actual class ReposRepository(
        private val baseUrl: String,
        private val userName: String
) {
    private val api: GithubApi by lazy {
        Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .baseUrl(baseUrl)
                .build()
                .create(GithubApi::class.java)
    }

    actual suspend fun getRepositories() =
            api.getRepositories(userName)
                    .await()
                    .map { apiRepo -> apiRepo.toGithubRepo() }

    actual suspend fun getBranches(repo: GithubRepo) =
            api.getBranches(userName, repo.name)
                    .await()
                    .map { apiBranch -> apiBranch.toGithubBranch() }
}

