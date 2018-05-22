package com.multiplatform

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.mobiusapp.R
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate
import com.multiplatform.model.entity.GithubRepo
import kotlinx.android.synthetic.main.repo_item_layout.view.*

class RepoItemDelegate : AdapterDelegate<List<Any>>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup?): RecyclerView.ViewHolder
            = ViewHolder(LayoutInflater.from(viewGroup?.context).inflate(R.layout.repo_item_layout, viewGroup, false))

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(list: List<Any>, position: Int, holder: RecyclerView.ViewHolder, payloads: MutableList<Any>) {
        val repo = list[position] as GithubRepo
        (holder as? ViewHolder)?.apply {
            repoTextView.text = repo.name
            branchesTextView.text = "Branches: ${repo.branches.size}"
        }
    }

    override fun isForViewType(list: List<Any>, position: Int): Boolean = list[position] is GithubRepo

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val repoTextView: TextView = view.repoTextView
        val branchesTextView: TextView = view.branchesTextView
    }
}
