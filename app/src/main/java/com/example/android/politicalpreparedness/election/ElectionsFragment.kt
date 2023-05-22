package com.example.android.politicalpreparedness.election

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.android.politicalpreparedness.databinding.FragmentElectionBinding
import com.example.android.politicalpreparedness.election.adapter.ElectionListAdapter
import com.example.android.politicalpreparedness.election.adapter.UpcomingElectionItemClickListener

class ElectionsFragment: Fragment() {


    //Declare ViewModel
    private val viewModel:ElectionsViewModel by lazy {
        val activity = requireNotNull(this.activity){
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(this,ElectionsViewModelFactory(activity.application)).get(ElectionsViewModel::class.java)
    }

    //Initiate recycler adapters
    val adapter = ElectionListAdapter(UpcomingElectionItemClickListener {
        //Toast.makeText(context,"${it}",Toast.LENGTH_SHORT).show()
        viewModel.onUpcomingElectionsItemClicked(it)
    })

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        //TODO: Add ViewModel values and create ViewModel

        //Added binding values
        val binding = FragmentElectionBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.electionViewModel = viewModel

        //Linked elections to voter info
        viewModel.navigateToSelectedUpcomingElection.observe(viewLifecycleOwner, Observer {
            it?.let {
                this.findNavController().navigate(ElectionsFragmentDirections.actionElectionsFragmentToVoterInfoFragment(it))
                viewModel.onUpcomingElectionsItemNavigated()
            }
        })

        //Populate recycler adapters
        binding.upcomingElectionsRecyclerview.adapter = adapter

        //Refresh adapters when fragment loads
        viewModel.upcomingElections.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        return binding.root
    }

}