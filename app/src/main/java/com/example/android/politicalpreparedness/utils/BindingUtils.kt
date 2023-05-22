package com.example.android.politicalpreparedness.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
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

@BindingAdapter("urlOnClick")
fun bindOnTextClickListener(textView: TextView, url:String){
    textView.setOnClickListener {
        if (url != null && url != ""){
            val context: Context = textView.context
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            context.startActivity(intent)
        }
    }
}