package com.techanist.delivz.view.adapter

import android.support.v7.util.DiffUtil
import com.techanist.delivz.R
import com.techanist.delivz.data.dto.Delivery
import com.techanist.delivz.data.paging.NetworkState
import com.techanist.delivz.data.paging.Status

class DeliveryAdapter : DataBindingAdapter<Delivery>(DiffCallback()) {

//    private var networkState: NetworkState? = null


    class DiffCallback : DiffUtil.ItemCallback<Delivery>() {
        override fun areItemsTheSame(p0: Delivery, p1: Delivery): Boolean {
            return p0.id == p1.id
        }

        override fun areContentsTheSame(p0: Delivery, p1: Delivery): Boolean {
            return p0.equals(p1)
        }
    }

//    private fun hasExtraRow() = networkState != null && networkState != NetworkState.LOADED
//
//    fun setNetworkState(newNetworkState: NetworkState?) {
//        val previousState = this.networkState
//        val hadExtraRow = hasExtraRow()
//        this.networkState = newNetworkState
//        val hasExtraRow = hasExtraRow()
//        if (hadExtraRow != hasExtraRow) {
//            if (hadExtraRow) {
//                notifyItemRemoved(super.getItemCount())
//            } else {
//                notifyItemInserted(super.getItemCount())
//            }
//        } else if (hasExtraRow && previousState != newNetworkState) {
//            notifyItemChanged(itemCount - 1)
//        }
//    }

    override fun getItemViewType(position: Int) = R.layout.item_delivery
}