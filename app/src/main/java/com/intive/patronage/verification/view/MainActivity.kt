package com.intive.patronage.verification.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.intive.patronage.verification.R
import com.intive.patronage.verification.domain.Joke
import com.intive.patronage.verification.presentation.MainViewModel
import com.intive.patronage.verification.shared.makeGone
import com.intive.patronage.verification.shared.makeVisible
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ListAdapter()
        adapter.items = viewModel.getJokes()

        recycler_view.adapter = adapter
        recycler_view.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))

        download_button.setOnClickListener { viewModel.queryNewJoke() }

        viewModel.model.observe(this, Observer {
            when (it) {
                MainActivityLoading -> applyLoading()
                MainActivityError -> applyError()
                is MainActivitySuccess -> applySuccess(adapter, it.items)
            }
        })
    }

    private fun applyLoading() {
        progress_bar.makeVisible()
    }

    private fun applyError() {
        progress_bar.makeGone()
        Snackbar.make(container, R.string.error, Snackbar.LENGTH_SHORT).show()
    }

    private fun applySuccess(adapter: ListAdapter, items: List<Joke>) {
        progress_bar.makeGone()
        adapter.items = items
    }
}
