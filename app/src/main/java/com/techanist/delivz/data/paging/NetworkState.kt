package com.techanist.delivz.data.paging

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED
}

data class NetworkState private constructor(
        val status: Status,
        val msg: String? = null) {
    companion object {
        val LOADED = NetworkState(Status.SUCCESS)
        val LOADING = NetworkState(Status.RUNNING)
        fun FAILED(msg: String?) = NetworkState(Status.FAILED, msg)
    }

    fun equals(other: NetworkState?): Boolean {
        return status == other?.status
    }

}