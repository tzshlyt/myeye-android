package com.example.myeye.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ViewSwitcher
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.myeye.R
import com.example.myeye.databinding.ActivityMainBinding
import com.example.myeye.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.vm = viewModel

        viewModel.requestData()
    }
}