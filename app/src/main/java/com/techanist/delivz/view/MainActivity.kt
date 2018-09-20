package com.techanist.delivz.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.techanist.delivz.R
import com.techanist.delivz.data.paging.NetworkState
import com.techanist.delivz.data.paging.Status
import com.techanist.delivz.databinding.ActivityMainBinding
import com.techanist.delivz.view.adapter.DeliveryAdapter
import com.techanist.delivz.viewmodel.DeliveryViewModel

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main) as ActivityMainBinding
    }

    private val viewModel: DeliveryViewModel by lazy {
        ViewModelProviders.of(this).get(DeliveryViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val adapter = DeliveryAdapter()
        binding.list.adapter = adapter
        binding.refresh.setOnRefreshListener {
            //            adapter.submitList(null)
            viewModel.getDeliveries()
        }

        viewModel.delivz.observe(this, Observer {
            it.let(adapter::submitList)
            binding.emptyList.visibility = View.GONE
            Log.d("paging_delivz", "Got items : ${it?.size}")
        })

        viewModel.networkStates.observe(this, Observer {
            Log.d("paging_delivz", "Got state : ${it.toString()}")
            if (it != NetworkState.LOADING) {
                binding.progress.visibility = View.GONE
                if (binding.refresh.isRefreshing)
                    binding.refresh.isRefreshing = false
            }
            if (it?.status == Status.FAILED && adapter.itemCount == 0)
                binding.emptyList.visibility = View.VISIBLE
        })

        viewModel.getDeliveries()
    }
}
