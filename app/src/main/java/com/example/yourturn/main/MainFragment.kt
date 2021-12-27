package com.example.yourturn.main

import android.accounts.NetworkErrorException
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yourturn.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.NullPointerException

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var recyclerView: RecyclerView
    private val adapter = MainRecyclerViewAdapter(listOf()) { name ->
        viewModel.removeData(name)
    }
    private val viewModel = MainViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        lifecycleScope.launch {
            viewModel.state.collect { state ->
                val restrictionList = state.restrictionList
                adapter.changeData(restrictionList)

                val error = state.error ?: return@collect
                when (error) {
                    is NullPointerException -> {

                    }
                    is NetworkErrorException -> {
                        Snackbar.make(view, getString(R.string.network_error), 2000).show()
                    }
                }
                viewModel.wipeError()
            }
        }

        lifecycleScope.launch {

        }
    }
}