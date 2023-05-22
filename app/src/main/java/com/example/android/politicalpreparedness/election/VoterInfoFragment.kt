package com.example.android.politicalpreparedness.election

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.android.politicalpreparedness.databinding.FragmentElectionBinding
import com.example.android.politicalpreparedness.databinding.FragmentVoterInfoBinding

class VoterInfoFragment : Fragment() {

    //TODO: Add ViewModel values and create ViewModel
    /*private val viewModel:VoterInfoViewModel by lazy {
        val activity = requireNotNull(this.activity){
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(this,VoterInfoViewModelFactory(selectedElection,activity.application)).get(VoterInfoViewModel::class.java)
    }*/

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val args = VoterInfoFragmentArgs.fromBundle(requireArguments()).argElectionId

        val viewModel:VoterInfoViewModel by lazy {
            val activity = requireNotNull(this.activity){
                "You can only access the viewModel after onViewCreated()"
            }
            ViewModelProvider(this,VoterInfoViewModelFactory(args, activity.application)).get(VoterInfoViewModel::class.java)
        }

        //Added binding values
        val binding = FragmentVoterInfoBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.voterInfoViewModel = viewModel

        viewModel.voterInfo.observe(viewLifecycleOwner, Observer {
            it.let {
                println("VoterInfoFragment "+ (it.state?.get(0)?.electionAdministrationBody?.ballotInfoUrl))
            }
        })

        //TODO: Populate voter info -- hide views without provided data.
        /**
        Hint: You will need to ensure proper data is provided from previous fragment.
        */


        //TODO: Handle loading of URLs

        //TODO: Handle save button UI state
        //TODO: cont'd Handle save button clicks

        return binding.root
    }

    //TODO: Create method to load URL intents

}