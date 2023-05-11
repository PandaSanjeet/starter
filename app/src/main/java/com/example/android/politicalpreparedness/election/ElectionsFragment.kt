package com.example.android.politicalpreparedness.election

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android.politicalpreparedness.databinding.FragmentElectionBinding
import com.example.android.politicalpreparedness.election.adapter.ElectionListAdapter

class ElectionsFragment: Fragment() {

    //Declare ViewModel
    private val viewModel:ElectionsViewModel by lazy {
        val activity = requireNotNull(this.activity){
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(this,ElectionsViewModelFactory(activity.application)).get(ElectionsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        //TODO: Add ViewModel values and create ViewModel

        //Added binding values
        val binding = FragmentElectionBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.electionViewModel = viewModel
        //TODO: Link elections to voter info

        //TODO: Initiate recycler adapters
        binding.upcomingElectionsRecyclerview.adapter = ElectionListAdapter()

        //TODO: Populate recycler adapters
        viewModel.upcomingElections.observe(viewLifecycleOwner, Observer {
            it?.let {
                ElectionListAdapter().data = it
            }
        })

        return binding.root
    }

    //TODO: Refresh adapters when fragment loads

}