package com.example.cinema.screens.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema.MAIN
import com.example.cinema.R
import com.example.cinema.databinding.FragmentFavoriteBinding
import com.example.cinema.databinding.FragmentMainBinding
import com.example.cinema.models.MovieItemModel
import com.example.cinema.screens.main.MainAdapter
import com.example.cinema.screens.main.MainFragmentViewModel

class FavoriteFragment : Fragment() {
    private var mBinding: FragmentFavoriteBinding? = null
    private val binding get() = mBinding!!
    lateinit var recyclerView: RecyclerView
    private val adapter by lazy { FavoriteAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this).get(FavoriteFragmentViewModel::class.java)
        recyclerView = binding.rvFavorite
        recyclerView.adapter = adapter
        viewModel.getAllMovies().observe(this, {list ->
            adapter.setList(list.asReversed())
        })


    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }

    companion object{
        fun clickMovie(model: MovieItemModel){
            val bundle = Bundle()
            bundle.putSerializable("movie", model)
            MAIN.navController.navigate(R.id.action_favoriteFragment_to_detailFragment, bundle)
        }
    }


}