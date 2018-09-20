package com.techanist.delivz.view.adapter

import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.techanist.delivz.BR
import com.techanist.delivz.R
import com.techanist.delivz.data.dto.Delivery
import com.techanist.delivz.view.DetailsActivity

class DelivzViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Delivery?) {
        if (item == null) return
        binding.setVariable(BR.item, item)
        binding.executePendingBindings()
        binding.root.setOnClickListener {
            val parentActivity: AppCompatActivity? = binding.root.getParentActivity()
            if (parentActivity != null) {
                val intent = Intent(parentActivity, DetailsActivity::class.java)
                intent.putExtra("item", item)
                parentActivity.startActivity(intent)
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): DelivzViewHolder {
            val binding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context),
                    R.layout.item_delivery, parent, false)
            return DelivzViewHolder(binding)
        }
    }
}