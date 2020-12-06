package com.example.a18620127_praktiktabbednavigation.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a18620127_praktiktabbednavigation.Movie
import com.example.a18620127_praktiktabbednavigation.MovieAdapter
import com.example.a18620127_praktiktabbednavigation.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PlayingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlayingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view:View
        view = inflater.inflate(R.layout.fragment_playing, container, false)

        var rvMovie : RecyclerView
        rvMovie = view.findViewById(R.id.rv_movie)

        rvMovie.setHasFixedSize(true)
        rvMovie.layoutManager = LinearLayoutManager(context)

        val service = ApiClient.create()
        service.getPlayingMovie("d58845c9d53da3e216a0a21300e1e90a").enqueue(object : Callback<MovieResponse>{
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                val movieList:ArrayList<Movie>
                if (response.isSuccessful){
                    movieList = response.body()?.results!!
                    val movieAdapter = MovieAdapter(movieList)
                    rvMovie.adapter = movieAdapter

                }

            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("tag","errornya ${t.message}")
            }
        })


        return view
    }
}