package ru.mintrocket.gen_motion_video.screens.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val listFragments: List<Fragment>
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun createFragment(position: Int): Fragment = getItem(position)

    override fun getItemCount(): Int = listFragments.size

    private fun getItem(position: Int): Fragment =
        listFragments[position]

}
