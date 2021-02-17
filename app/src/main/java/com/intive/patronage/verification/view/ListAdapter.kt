package com.intive.patronage.verification.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.intive.patronage.verification.domain.Joke

class ListAdapter : RecyclerView.Adapter<SealedAdapterViewHolder>() {

    var items: List<Joke> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SealedAdapterViewHolder {
        return when (viewType) {
            TYPE_TWO_PART -> SealedAdapterViewHolder.ListItemViewHolder(
                SealedAdapterViewHolder.ListItemViewHolder.createView(parent)
            )
            TYPE_SINGLE -> SealedAdapterViewHolder.SingleItemViewHolder(
                SealedAdapterViewHolder.SingleItemViewHolder.createView(parent)
            )
            else -> throw UnsupportedOperationException()
        }
    }

    override fun onBindViewHolder(holder: SealedAdapterViewHolder, position: Int) {
        when (holder) {
            is SealedAdapterViewHolder.ListItemViewHolder ->
                holder.applyItem(items[position] as Joke.TwoPartJoke)
            is SealedAdapterViewHolder.SingleItemViewHolder ->
                holder.applyItem(items[position] as Joke.SingleJoke)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is Joke.SingleJoke -> TYPE_SINGLE
            is Joke.TwoPartJoke -> TYPE_TWO_PART
        }
    }

    override fun getItemCount() = items.size

    companion object {
        private const val TYPE_SINGLE = 0
        private const val TYPE_TWO_PART = 1
    }
}
