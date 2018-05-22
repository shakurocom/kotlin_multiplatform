package com.multiplatform.model.api.entity

import com.multiplatform.model.entity.GithubRepo

data class ApiRepo(
        val id: Long,
        val name: String
) {
    fun toGithubRepo() = GithubRepo(id, name)
}