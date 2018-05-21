package com.multiplatform.model.network

import com.multiplatform.model.entity.GithubBranch

data class NetGithubBranch(
        val name: String,
        val commit: NetGithubCommit
) {

    data class NetGithubCommit(
            val sha: String,
            val url: String
    )

    fun toGithubBranch(): GithubBranch = GithubBranch(name, commit.sha, commit.url)
}