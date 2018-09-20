package com.techanist.delivz.view.adapter

import android.content.Intent
import android.databinding.ViewDataBinding
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import com.techanist.delivz.BR
import com.techanist.delivz.data.dto.Delivery
import com.techanist.delivz.view.DetailsActivity

class DataBindingViewHolder<T>(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

    fun bind(item: T?) {
        if (item == null) return
        binding.setVariable(BR.item, item)
        binding.executePendingBindings()
        binding.root.setOnClickListener {
            val parentActivity: AppCompatActivity? = binding.root.getParentActivity()
            if (parentActivity != null && item is Delivery) {
                val intent = Intent(parentActivity, DetailsActivity::class.java)
                intent.putExtra("item", item)
                parentActivity.startActivity(intent)
            }
        }
    }
}