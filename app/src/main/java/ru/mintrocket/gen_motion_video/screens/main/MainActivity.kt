package ru.mintrocket.gen_motion_video.screens.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.navigation.NavigationBarView
import ru.mintrocket.gen_motion_video.*
import ru.mintrocket.gen_motion_video.databinding.ActivityMainBinding
import ru.mintrocket.gen_motion_video.screens.video_generated.VideoGeneratedFragment
import ru.mintrocket.gen_motion_video.screens.video_original.VideoOriginalFragment
import ru.mintrocket.gen_motion_video.screens.video_split_horizontal.VideoSplitHorizontalFragment
import ru.mintrocket.gen_motion_video.screens.video_vertical.VideoSplitVerticalFragment

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding<ActivityMainBinding>()

    private val listenerNavigationBar = NavigationBarView.OnItemSelectedListener {
        when (it.itemId) {
            R.id.nav_orig -> {
                binding.viewPager.setCurrentItem(0, false)
                return@OnItemSelectedListener true
            }
            R.id.nav_gen -> {
                binding.viewPager.setCurrentItem(1, false)
                return@OnItemSelectedListener true
            }
            R.id.nav_split_horizontal -> {
                binding.viewPager.setCurrentItem(2, false)
                return@OnItemSelectedListener true
            }
            R.id.nav_split_vertical ->{
                binding.viewPager.setCurrentItem(3, false)
                return@OnItemSelectedListener true
            }
        }
        false
    }

    private val listenerChangePage = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            //val menu = binding.bottomNavigation.menu
            //checkedMenuNavigationBar(menu)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding.apply {
            viewPager.apply {
                val listFragments = arrayListOf<Fragment>()
                listFragments.add(VideoOriginalFragment())
                listFragments.add(VideoGeneratedFragment())
                listFragments.add(VideoSplitHorizontalFragment())
                listFragments.add(VideoSplitVerticalFragment())

                adapter = ViewPagerAdapter(supportFragmentManager, lifecycle, listFragments)
                registerOnPageChangeCallback(listenerChangePage)
            }
            bottomNavigationView.setOnItemSelectedListener(listenerNavigationBar)

        }
    }
}