package com.vinay.kotlin_coroutine_examples.ui.SingleNetwork

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.vinay.kotlin_coroutine_examples.R
import com.vinay.kotlin_coroutine_examples.adapter.MoviePagingAdapter
import com.vinay.kotlin_coroutine_examples.adapter.UserAdapter
import com.vinay.kotlin_coroutine_examples.model.UserModel
import com.vinay.kotlin_coroutine_examples.network.ApiHelperInterface
import com.vinay.kotlin_coroutine_examples.network.RetrofitBuilder
import com.vinay.kotlin_coroutine_examples.room.DatabaseHelper
import com.vinay.kotlin_coroutine_examples.util.Status
import com.vinay.kotlin_coroutine_examples.util.ViewModelFactory
import kotlinx.android.synthetic.main.activity_single_network_call.*

class MovieSearchActivity : AppCompatActivity() {


    private lateinit var viewModel: SingleNetworkCallViewModel
    private lateinit var adapter: UserAdapter
    private lateinit var movieAdapter: MoviePagingAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_network_call)

//        setupUI()
        setupMovieUI()
        setupViewModel()
        setUpMovieAdapter()
//        setupObserver()


    }



    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter =
            UserAdapter(
                arrayListOf()
            )
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        viewModel.getUsers().observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { users -> renderList(users) }
                    recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }


    private fun setupMovieUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        movieAdapter =
            MoviePagingAdapter()
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = movieAdapter
    }


    private fun setUpMovieAdapter(){


        movie_search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.setQuery(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })


        viewModel.listSearchedData.observe(this) {

            progressBar.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE

            movieAdapter.submitData(lifecycle, it)
        }
    }

    private fun renderList(users: List<UserModel>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                ApiHelperInterface(RetrofitBuilder.apiInterface),
               null
            )
        ).get(SingleNetworkCallViewModel::class.java)
    }


}