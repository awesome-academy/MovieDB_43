package com.asterisk.tuandao.themoviedb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asterisk.tuandao.themoviedb.R
import com.asterisk.tuandao.themoviedb.data.source.remote.NetworkState
import com.asterisk.tuandao.themoviedb.data.source.remote.Status

class NetworkStateItemViewHolder(view: View,
                                 retryCallback: () -> Unit) : RecyclerView.ViewHolder(view) {
    private val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
    private val retry = view.findViewById<Button>(R.id.retryButton)
    private val errorMsg = view.findViewById<TextView>(R.id.errorMsg)
    init {
        retry.setOnClickListener {
            retryCallback()
        }
    }

    fun bindView(networkState: NetworkState?) {
        progressBar.visibility = toVisibility(networkState?.status == Status.RUNNING)
        retry.visibility = toVisibility(networkState?.status == Status.FAILED)
        errorMsg.visibility = toVisibility(networkState?.msg != null)
        errorMsg.text = networkState?.msg
    }

    companion object {
        fun toVisibility(constraint: Boolean): Int {
            return if (constraint) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }

        fun create(layoutInflater: LayoutInflater, parent: ViewGroup,
                   retryCallback: () -> Unit): NetworkStateItemViewHolder {
                   val view = LayoutInflater.from(parent.context)
                           .inflate(R.layout.network_state_item, parent, false)
                   return NetworkStateItemViewHolder(view, retryCallback)
        }
    }
}
