package com.example.myapplication.presentation.view

import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.domain.EatItem

class EatItemDiffCallback : DiffUtil.ItemCallback<EatItem>() {
    override fun areItemsTheSame(oldItem: EatItem, newItem: EatItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: EatItem, newItem: EatItem): Boolean {
        return oldItem == newItem
    }
}