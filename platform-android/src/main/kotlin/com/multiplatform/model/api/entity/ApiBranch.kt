package com.multiplatform.model.api.entity

import com.multiplatform.model.entity.GithubBranch

data class ApiBranch(
        val name: String,
        val commit: ApiCommit
) {
    fun toGithubBranch(): GithubBranch = GithubBranch(name, commit.sha, commit.url)
}