package com.example.myeye.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myeye.R
import com.example.myeye.ui.home.RecommendFragment
import com.example.myeye.ui.home.DailyFragment
import com.example.myeye.ui.home.DiscoveryFragment
import com.example.myeye.viewModel.HomeViewModel
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    private lateinit var adapter: ViewPagerAdapter
    private val titles = arrayOf("发现", "推荐", "日报")

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        adapter = ViewPagerAdapter(this)
        pager.adapter = adapter

        TabLayoutMediator(
            tab_layout,
            pager,
            TabConfigurationStrategy { tab, position -> tab.text = titles[position] }
        ).attach()
    }

    class ViewPagerAdapter(fm: Fragment) : FragmentStateAdapter(fm) {
        override fun getItemCount(): Int {
            return 3
        }

        override fun createFragment(position: Int): Fragment {
           return when(position) {
                0 -> DiscoveryFragment.newInstance()
                1 -> RecommendFragment.newInstance()
                else -> DailyFragment.newInstance()
            }
        }
    }

}