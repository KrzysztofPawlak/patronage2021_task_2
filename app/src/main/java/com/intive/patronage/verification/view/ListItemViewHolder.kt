package com.intive.patronage.verification.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.intive.patronage.verification.R
import com.intive.patronage.verification.domain.Joke
import kotlinx.android.synthetic.main.list_item_twopart.view.*

class ListItemViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(createView(parent)) {

    fun applyItem(joke: Joke) {
        with (itemView) {
            setup_text.text = joke.setup
            delivery_text.text = joke.delivery
        }
    }
}

private fun createView(parent: ViewGroup): View {
    val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    return inflater.inflate(R.layout.list_item_twopart, parent, false)
}
