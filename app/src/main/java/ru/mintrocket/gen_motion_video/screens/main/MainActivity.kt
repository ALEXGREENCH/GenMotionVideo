package ru.mintrocket.gen_motion_video.screens.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.navigation.NavigationBarView
import okhttp3.internal.Internal.instance
import ru.mintrocket.gen_motion_video.*
import ru.mintrocket.gen_motion_video.databinding.ActivityMainBinding
import ru.mintrocket.gen_motion_video.screens.video_generated.VideoGeneratedFragment
import ru.mintrocket.gen_motion_video.screens.video_original.VideoOriginalFragment
import ru.mintrocket.gen_motion_video.screens.video_split_horizontal.VideoSplitHorizontalFragment
import ru.mintrocket.gen_motion_video.screens.video_vertical.VideoSplitVerticalFragment
import java.lang.IllegalArgumentException

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
            val menu = binding.bottomNavigationView.menu
            menu.findItem(when(position){
                0 -> {
                    R.id.nav_orig
                }
                1 -> {
                    R.id.nav_gen
                }
                2 -> {
                    R.id.nav_split_horizontal
                }
                3 -> {
                    R.id.nav_split_vertical
                }
                else -> throw IllegalArgumentException("?")
            }).isChecked = true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding.apply {
            viewPager.apply {
                adapter = ViewPagerAdapter(supportFragmentManager, lifecycle, arrayListOf<Fragment>().apply {
                    add(VideoOriginalFragment.instance)
                    add(VideoGeneratedFragment.instance)
                    add(VideoSplitHorizontalFragment.instance)
                    add(VideoSplitVerticalFragment.instance)
                })
                registerOnPageChangeCallback(listenerChangePage)
                offscreenPageLimit = 1
            }
            bottomNavigationView.setOnItemSelectedListener(listenerNavigationBar)
        }
    }
}