package com.multiplatform.model.api

import com.multiplatform.model.api.entity.ApiBranch
import com.multiplatform.model.api.entity.ApiRepo
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

    @GET("users/{userName}/repos")
    fun getRepositories(
            @Path("userName") userName: String
    ): Deferred<List<ApiRepo>>

    @GET("repos/{userName}/{repoName}/branches")
    fun getBranches(
            @Path("userName") userName: String,
            @Path("repoName") repoName: String
    ): Deferred<List<ApiBranch>>
}