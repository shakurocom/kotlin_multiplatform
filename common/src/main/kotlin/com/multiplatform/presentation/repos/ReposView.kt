package com.multiplatform.presentation.repos

import com.multiplatform.model.entity.GithubRepo
import com.multiplatform.presentation.base.BaseView

interface ReposView: BaseView {
    fun showRepoList(repoList: List<GithubRepo>)
    fun showLoading(loading: Boolean)
    fun showError(errorMessage: String)
}