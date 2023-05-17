package com.example.android.politicalpreparedness.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.politicalpreparedness.election.adapter.ElectionListAdapter
import com.example.android.politicalpreparedness.network.models.Election

@BindingAdapter("itemTitleData")
fun TextView.setItemName(item: Election?){
    item?.let {
        text = item.name
    }
}

@BindingAdapter("itemDateData")
fun TextView.setItemDate(item: Election?){
    item?.let {
        text = item.electionDay
    }
}