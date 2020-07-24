package com.example.myeye.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.myeye.R
import com.example.myeye.viewModel.HotViewModel

class NotificationFragment : Fragment() {

    companion object {
        fun newInstance() = NotificationFragment()
    }

    private lateinit var viewModel: HotViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notification, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HotViewModel::class.java)
    }
}