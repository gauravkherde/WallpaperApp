package com.gaurav.wallpaperapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gaurav.wallpaperapp.Adapter.ViewPagerAdapter
import com.gaurav.wallpaperapp.`package`.Category
import com.gaurav.wallpaperapp.`package`.Home
import com.gaurav.wallpaperapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tabLayoutId.tabRippleColor=null
        var adapter=ViewPagerAdapter(this)
        binding.viewpagerId.offscreenPageLimit=2
        val tablename= arrayOf("Home","Category")
        adapter.addFragment(Home(),tablename[0])
        adapter.addFragment(Category(),tablename[1])
        binding.viewpagerId.adapter=adapter

        TabLayoutMediator(binding.tabLayoutId,binding.viewpagerId){tab, position ->
            tab.text=tablename[position]
            binding.viewpagerId.setCurrentItem(tab.position,true)
        }.attach()

    }
}