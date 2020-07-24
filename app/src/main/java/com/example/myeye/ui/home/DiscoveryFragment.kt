package com.example.myeye.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myeye.R
import com.example.myeye.adapter.DiscoveryAdapter
import kotlinx.android.synthetic.main.fragment_discovery.*

class DiscoveryFragment : Fragment() {

    companion object {
        fun newInstance() = DiscoveryFragment()
    }

    private lateinit var viewModel: DiscoveryViewModel
    private lateinit var adapter: DiscoveryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_discovery, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DiscoveryViewModel::class.java)

        adapter = DiscoveryAdapter(viewModel.dataList)
        rv_main.adapter = adapter

        viewModel.requestData()
        viewModel.dataListLiveData.observe(viewLifecycleOwner, Observer { result ->
            result.onSuccess {
                viewModel.dataList.clear()
                viewModel.dataList.addAll(it.itemList)
                adapter.notifyDataSetChanged()
            }
        })
    }
}