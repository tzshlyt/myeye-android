package com.example.myeye.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.myeye.R
import com.example.myeye.databinding.ActivityMainBinding
import com.example.myeye.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    private var homeFragment: HomeFragment? = null
    private var communityFragment: CommunityFragment? = null
    private var notificationFragment: NotificationFragment? = null
    private var mineFragment: MineFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.vm = viewModel

        initFragment(savedInstanceState)

        viewModel.requestData()
    }

    private fun initFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            homeFragment = HomeFragment.newInstance()
            communityFragment = CommunityFragment.newInstance()
            mineFragment = MineFragment.newInstance()
            notificationFragment = NotificationFragment.newInstance()
            supportFragmentManager.beginTransaction().run {
                add(R.id.home_container, homeFragment!!)
                add(R.id.home_container, communityFragment!!)
                add(R.id.home_container, notificationFragment!!)
                add(R.id.home_container, mineFragment!!)

                show(homeFragment!!)
                hide(communityFragment!!)
                hide(notificationFragment!!)
                hide(mineFragment!!)
                commit()
            }
        } else {
            for (item in supportFragmentManager.fragments) {
                if (item is HomeFragment) {
                    homeFragment = item
                }
                if (item is CommunityFragment) {
                    communityFragment = item
                }
                if (item is NotificationFragment) {
                    notificationFragment = item
                }
                if (item is MineFragment) {
                    mineFragment = item
                }
            }
        }

        rg_root.setOnCheckedChangeListener { radioGroup, i ->
            when(i) {
                R.id.rb_home -> {
                    supportFragmentManager.beginTransaction().run {
                        show(homeFragment!!)
                        hide(communityFragment!!)
                        hide(notificationFragment!!)
                        hide(mineFragment!!)
                        commit()
                    }
                }

                R.id.rb_find -> {
                    supportFragmentManager.beginTransaction().run {
                        hide(homeFragment!!)
                        show(communityFragment!!)
                        hide(notificationFragment!!)
                        hide(mineFragment!!)
                        commit()
                    }
                }

                R.id.rb_hot -> {
                    supportFragmentManager.beginTransaction().run {
                        hide(homeFragment!!)
                        hide(communityFragment!!)
                        show(notificationFragment!!)
                        hide(mineFragment!!)
                        commit()
                    }
                }

                R.id.rb_mine -> {
                    supportFragmentManager.beginTransaction().run {
                        hide(homeFragment!!)
                        hide(communityFragment!!)
                        hide(notificationFragment!!)
                        show(mineFragment!!)
                        commit()
                    }
                }
            }
        }
    }
}