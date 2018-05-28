package com.multiplatform

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import com.example.mobiusapp.R
import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter
import com.multiplatform.model.entity.GithubRepo
import com.multiplatform.model.interactor.ReposInteractor
import com.multiplatform.model.repository.ReposRepository
import com.multiplatform.presentation.repos.ReposPresenter
import com.multiplatform.presentation.repos.ReposView
import kotlinx.android.synthetic.main.activity_repos.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI

class ReposActivity : AppCompatActivity(), ReposView {

    private val exchangeRepository = ReposRepository(
            baseUrl = "https://api.github.com/",
            userName = "shakurocom"
    )

    private val interactor = ReposInteractor(exchangeRepository, CommonPool)

    private val presenter = ReposPresenter(
            uiContext = UI,
            interactor = interactor
    )

    private val adapter by lazy {
        ListDelegationAdapter(AdapterDelegatesManager<List<Any>>().addDelegate(RepoItemDelegate()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repos)

        tickersRecyclerView.adapter = adapter
        tickersRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onStart() {
        super.onStart()
        presenter.attach(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detach()
    }

    override fun showRepoList(repoList: List<GithubRepo>) {
        adapter.items = repoList
        adapter.notifyDataSetChanged()
    }

    override fun showLoading(loading: Boolean) {
        loadingProgress.visibility = if (loading) VISIBLE else GONE
    }

    override fun showError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }
}
