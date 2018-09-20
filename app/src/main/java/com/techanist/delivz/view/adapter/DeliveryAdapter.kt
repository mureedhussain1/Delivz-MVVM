package com.techanist.delivz.view.adapter

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.techanist.delivz.R
import com.techanist.delivz.data.dto.Delivery
import com.techanist.delivz.data.paging.NetworkState
import java.lang.IllegalArgumentException

class DeliveryAdapter(val retryCallback: () -> Unit) : PagedListAdapter<Delivery, RecyclerView.ViewHolder>(DiffCallback) {

    private var networkState: NetworkState? = null

    companion object {
        object DiffCallback : DiffUtil.ItemCallback<Delivery>() {
            override fun areItemsTheSame(p0: Delivery, p1: Delivery): Boolean {
                return p0.id == p1.id
            }

            override fun areContentsTheSame(p0: Delivery, p1: Delivery): Boolean {
                return p0.equals(p1)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_delivery -> DelivzViewHolder.create(parent)
            R.layout.network_state_item -> NetworkStateItemViewHolder.create(parent) {
                retryCallback()
            }
            else
            -> throw IllegalArgumentException("Invalid view $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is DelivzViewHolder)
            holder.bind(getItem(position))
        else (holder as? NetworkStateItemViewHolder)?.bindTo(networkState)
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            R.layout.network_state_item
        } else {
            R.layout.item_delivery
        }
    }

    private fun hasExtraRow() = networkState != null && networkState != NetworkState.LOADED

    fun setNetworkState(newNetworkState: NetworkState?) {
        val previousState = this.networkState
        val hadExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val hasExtraRow = hasExtraRow()
        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && previousState != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }

}