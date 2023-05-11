package com.example.android.politicalpreparedness.election.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.politicalpreparedness.R
import com.example.android.politicalpreparedness.database.UpcomingElections
import com.example.android.politicalpreparedness.databinding.FragmentElectionBinding
import com.example.android.politicalpreparedness.databinding.FragmentRecyclerviewItemBinding
//import com.example.android.politicalpreparedness.databinding.ViewholderElectionBinding // uncomment this
import com.example.android.politicalpreparedness.network.models.Election

class ElectionListAdapter: RecyclerView.Adapter<ElectionListAdapter.TextItemViewHolder>() {
//class ElectionListAdapter(private val clickListener: OnClickListener): ListAdapter<Election, ElectionListAdapter.ElectionViewHolder>(ElectionDiffCallback) {

    var data = listOf<UpcomingElections>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = 2

    //override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElectionViewHolder {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        //return ElectionViewHolder(FragmentRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context)))
        //return ElectionViewHolder.from(parent)

        val view = LayoutInflater.from(parent.context).inflate(R.layout.text_item_view,parent,false) as TextView
        return TextItemViewHolder(view)
        //return ElectionViewHolder.from(parent)
    }

    //Binded ViewHolder
    //override fun onBindViewHolder(holder: ElectionViewHolder, position: Int) {
    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        /*val election = getItem(position)
        holder.itemView.setOnClickListener {
            clickListener.onClick(election)
        }
        holder.bind(election)*/
        val item = data[position]
        holder.textView.text = item.name
    }


    //Created ElectionViewHolder
    /*class ElectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val date: TextView = itemView.findViewById(R.id.date)

        fun bind(election: Election) {
            title.text = election.name
            date.text = election.electionDay
        }

    }*/

    class TextItemViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)
    /*class ElectionViewHolder(private var binding: FragmentRecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(election: Election) {
            binding.upcomingElectionData = election
            binding.executePendingBindings()
        }

        //TODO: Add companion object to inflate ViewHolder (from)
        companion object{
            fun from(parent: ViewGroup): ElectionViewHolder{
                //val layoutInflater = LayoutInflater.from(parent.context)
                //val view = layoutInflater.inflate(R.layout.fragment_recyclerview_item,parent,false)
                return ElectionViewHolder(FragmentRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context)))
            }
        }
    }*/


//Created ElectionDiffCallback
    companion object ElectionDiffCallback : DiffUtil.ItemCallback<Election>(){
    override fun areItemsTheSame(oldItem: Election, newItem: Election): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Election, newItem: Election): Boolean {
        return oldItem.id == newItem.id
    }
}

    //Created ElectionListener
    class OnClickListener(val clickListener: (election: Election) -> Unit) {
        fun onClick(election: Election) = clickListener(election)
    }
}