package com.multiplatform.model.entity

data class GithubRepo(
        val id: Long,
        val name: String,
        var branches: List<GithubBranch> = emptyList()
)