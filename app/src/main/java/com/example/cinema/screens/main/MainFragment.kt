package com.example.cinema.screens.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema.MAIN
import com.example.cinema.R
import com.example.cinema.models.MovieItemModel
import com.example.cinema.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var mBinding: FragmentMainBinding ?= null
    private val binding get() = mBinding!!
    lateinit var recyclerView: RecyclerView
    private val adapter by lazy { MainAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentMainBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this).get(MainFragmentViewModel::class.java)
        viewModel.initDatabase()
        recyclerView = binding.rvMain
        recyclerView.adapter = adapter
        try {
            viewModel.getMoviesRetrofit()
            viewModel.myMovies.observe(this, {list ->
                adapter.setList(list.body()!!.results)
            })
        }catch (e: Exception){
            Toast.makeText(MAIN, e.message, Toast.LENGTH_SHORT).show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_favorite -> {
                MAIN.navController.navigate(R.id.action_mainFragment_to_favoriteFragment2)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object{
        fun clickMovie(model: MovieItemModel){
            val bundle = Bundle()
            bundle.putSerializable("movie", model)
            MAIN.navController.navigate(R.id.action_mainFragment_to_detailFragment, bundle)
        }
    }

}