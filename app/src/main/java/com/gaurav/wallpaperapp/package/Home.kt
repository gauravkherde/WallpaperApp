package com.gaurav.wallpaperapp.`package`

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.gaurav.wallpaperapp.Adapter.ImageItemAdapter
import com.gaurav.wallpaperapp.R
import com.gaurav.wallpaperapp.Services.RetrofitInstance
import com.gaurav.wallpaperapp.databinding.FragmentHomeBinding
import com.gaurav.wallpaperapp.models.Photo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Home : Fragment() {
    private  lateinit var binding: FragmentHomeBinding
    private lateinit var adapter : ImageItemAdapter
    private var page : Int = 1
    private var photos :MutableList<Photo> =ArrayList()
    private var sort: String="popular"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home,container,false)
        binding = FragmentHomeBinding.bind(view)
        initRecyclerView()
        getImages()
        return view
    }
    private fun getImages() {
        page = 1
        val getPost = RetrofitInstance.api.getRecentPhotos(page,30,sort)
        getPost.enqueue(object : Callback<MutableList<Photo>> {
            override fun onResponse(
                call: Call<MutableList<Photo>>,
                response: Response<MutableList<Photo>>
            ) {
                if(response.isSuccessful)
                {
                    photos.clear()
                    Log.d("response",response.body().toString())
                    response.body()?.let { photos.addAll(it)}
                    adapter.notifyDataSetChanged()
                }
                else
                    Log.d("response",response.body().toString())
            }

            override fun onFailure(call: Call<MutableList<Photo>>, t: Throwable) {
                Log.d("Response","Failed")
            }

        })

    }

    private fun initRecyclerView() {
        binding.homeRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = ImageItemAdapter(photos,requireContext())
        binding.homeRecyclerView.adapter = adapter

    }
}
