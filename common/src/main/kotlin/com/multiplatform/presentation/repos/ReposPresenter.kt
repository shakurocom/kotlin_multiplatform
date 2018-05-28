package com.multiplatform.presentation.repos

import com.multiplatform.model.interactor.ReposInteractor
import kotlin.coroutines.experimental.CoroutineContext
import com.multiplatform.presentation.base.BasePresenter
import com.multiplatform.coroutines.launch

class ReposPresenter(
        private val uiContext: CoroutineContext,
        private val interactor: ReposInteractor
) : BasePresenter<ReposView>() {

    override fun onViewAttached() {
        super.onViewAttached()
        refresh()
    }

    fun refresh() {
        launch(uiContext) {
            view?.showLoading(true)
            try {
                val repoList = interactor.getRepos()
                view?.showRepoList(repoList)
            } catch (e: Throwable) {
                view?.showError(e.message ?: "Can't load repositories")
            }
            view?.showLoading(false)
        }
    }
}
