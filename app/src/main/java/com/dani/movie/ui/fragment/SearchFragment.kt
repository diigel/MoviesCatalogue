package com.dani.movie.ui.fragment

import android.os.Bundle
import android.util.Log
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.dani.data.loaderDialog
import com.dani.data.toJson
import com.dani.data.watcher
import com.dani.movie.R
import com.dani.movie.databinding.FragmentSearchBinding
import com.dani.movie.ui.adapter.SearchAdapter
import com.dani.movie.viewmodel.SearchViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SearchFragment : Fragment(R.layout.fragment_search){

    private val viewModel : SearchViewModel by sharedViewModel()
    private val binding : FragmentSearchBinding by viewBinding()
    private val loader by lazy {
        context?.loaderDialog()
    }

    private val searchAdapter by lazy {
        SearchAdapter()
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        binding.run {
            rvSearch.layoutManager = GridLayoutManager(context,1)
            rvSearch.adapter = searchAdapter
            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
            searchAdapter.onClick {
                Log.d("Search Movie", "navigate detail is -> ${it.toJson()}")
                val movieId = it.id
                viewModel.navigateToDetailMovie(movieId)
            }
        }
        initObserver()
        setupSearch()
    }

    private fun initObserver(){
        viewModel.requestSearch.observe(viewLifecycleOwner,{
            loader?.show()
            lifecycleScope.launch {
                delay(1000)
                loader?.dismiss()
                if (it.isNotEmpty()){
                    searchAdapter.addList(it)
                }else{
                    Toast.makeText(context, "Movie not found", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setupSearch() =  binding.run {
        etSearch.watcher {
            viewModel.requestSearch(it)
        }
    }
}