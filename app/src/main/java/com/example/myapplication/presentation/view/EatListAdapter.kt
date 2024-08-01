package com.example.myapplication.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.myapplication.R
import com.example.myapplication.domain.EatItem

class EatListAdapter : ListAdapter<EatItem, EatItemViewHolder>(EatItemDiffCallback()) {


    var onEatItemLongClickListener: ((EatItem) -> Unit)? = null
    var onEatItemClickListener: ((EatItem) -> Unit)? = null


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EatItemViewHolder {
        val view = if (viewType == ENABLED) {
            LayoutInflater.from(parent.context).inflate(R.layout.item_eat_enabled, parent, false)
        } else {
            LayoutInflater.from(parent.context).inflate(R.layout.item_eat_disabled, parent, false)
        }
        return EatItemViewHolder(view)
    }


    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.enabled) ENABLED else DISABLED
    }

    override fun onBindViewHolder(viewHolder: EatItemViewHolder, position: Int) {
        val eatItem = getItem(position)
        viewHolder.tvName.text = eatItem.name
        viewHolder.tvCount.text = eatItem.count.toString()
        viewHolder.view.setOnLongClickListener {
            onEatItemLongClickListener?.invoke(eatItem)
            true
        }
        viewHolder.view.setOnClickListener {
            onEatItemClickListener?.invoke(eatItem)
            true
        }

    }

    companion object {
        const val ENABLED = 0
        const val DISABLED = 1
        const val MAX_POOL_SIZE = 15
    }


}
