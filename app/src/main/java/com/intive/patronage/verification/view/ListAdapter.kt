package com.intive.patronage.verification.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.intive.patronage.verification.domain.Joke

class ListAdapter : RecyclerView.Adapter<ListItemViewHolder>() {

    var items: List<Joke> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        return ListItemViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.applyItem(items[position])
    }

    override fun getItemCount() = items.size
}
