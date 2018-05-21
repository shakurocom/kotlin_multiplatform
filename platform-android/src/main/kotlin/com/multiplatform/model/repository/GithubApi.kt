package com.multiplatform.model.repository

import com.multiplatform.model.entity.GithubRepo
import com.multiplatform.model.network.NetGithubBranch
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

    @GET("users/{userName}/repos")
    fun getRepositories(
            @Path("userName") userName: String
    ): Deferred<List<GithubRepo>>

    @GET("repos/{userName}/{repoName}/branches")
    fun getBranches(
            @Path("userName") userName: String,
            @Path("repoName") repoName: String
    ): Deferred<List<NetGithubBranch>>
}