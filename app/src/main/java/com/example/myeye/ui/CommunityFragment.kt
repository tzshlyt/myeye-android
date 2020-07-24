package com.example.myeye.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myeye.R
import com.example.myeye.viewModel.FindViewModel

class CommunityFragment : Fragment() {

    companion object {
        fun newInstance() = CommunityFragment()
    }

    private lateinit var viewModel: FindViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_community, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FindViewModel::class.java)

    }

}