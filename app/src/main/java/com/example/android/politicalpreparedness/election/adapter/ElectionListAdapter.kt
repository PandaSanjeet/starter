package com.example.android.politicalpreparedness.election.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.politicalpreparedness.databinding.FragmentRecyclerviewItemBinding
//import com.example.android.politicalpreparedness.databinding.ViewholderElectionBinding // uncomment this
import com.example.android.politicalpreparedness.network.models.Election
//class ElectionListAdapter: RecyclerView.Adapter<ElectionListAdapter.ElectionViewHolder>() {
//class ElectionListAdapter: RecyclerView.Adapter<ElectionListAdapter.TextItemViewHolder>() {
class ElectionListAdapter(val clickListener: UpcomingElectionItemClickListener): ListAdapter<Election, ElectionViewHolder>(ElectionDiffCallback()) {

    /*var data = listOf<Election>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = data.size*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElectionViewHolder {
        //override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        //return ElectionViewHolder(FragmentRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context)))
        //return ElectionViewHolder.from(parent)


        //val withDataBinding: FragmentRecyclerviewItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.fragment_recyclerview_item,parent,false)//13-05-2023
        //LayoutInflater.from(parent.context).inflate(R.layout.fragment_recyclerview_item,parent,false)
        //return ElectionViewHolder(withDataBinding)//13-05-2023
        //val view = LayoutInflater.from(parent.context).inflate(R.layout.text_item_view,parent,false) as TextView
        //return TextItemViewHolder(view)
        return ElectionViewHolder.from(parent)
    }

    //Binded ViewHolder
    //override fun onBindViewHolder(holder: ElectionViewHolder, position: Int) {
    override fun onBindViewHolder(holder: ElectionViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
        /*holder.binding.also { //13-05-2023
            it.upcomingElectionData = getItem(position)
            //it.upcomingElectionData = data[position]
        }*/
        /*val item = data[position]
        holder.textView.text = item.name*/
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
}
//class TextItemViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)
class ElectionViewHolder(val binding: FragmentRecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(election: Election, clickListener: UpcomingElectionItemClickListener) {
        binding.upcomingElectionData = election
        binding.itemClickListener = clickListener
        binding.executePendingBindings()
    }

    //Added companion object to inflate ViewHolder (from)
    companion object{
        fun from(parent: ViewGroup): ElectionViewHolder{
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = FragmentRecyclerviewItemBinding.inflate(layoutInflater,parent,false)
            return ElectionViewHolder(binding)
        }
    }
}


//Created ElectionDiffCallback
class ElectionDiffCallback : DiffUtil.ItemCallback<Election>(){
    override fun areItemsTheSame(oldItem: Election, newItem: Election): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Election, newItem: Election): Boolean {
        return oldItem == newItem
    }
}

//Created ElectionListener
class UpcomingElectionItemClickListener(val clickListener: (electionItem: Election) -> Unit) {
    fun onClick(election: Election) = clickListener(election)
}
