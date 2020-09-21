package com.example.perfumeapp.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.myperfumeapp.R
import com.example.myperfumeapp.databinding.FragmentPerfumeDetail2Binding




/**
 * A simple [Fragment] subclass.
 *
 */
class PerfumeDetail : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(activity).application

        // Inflate the layout for this fragment
        val binding: FragmentPerfumeDetail2Binding = DataBindingUtil.inflate( inflater,
            R.layout.fragment_perfume_detail2, container, false)
        binding.setLifecycleOwner(this)

        val perfume = PerfumeDetailArgs.fromBundle(arguments!!).perfume

        val viewModelFactory = PerfumeDetailViewModelFactory(perfume,application)

        binding.perfumeDetailViewModel = ViewModelProviders.of(
            this, viewModelFactory).get(PerfumeDetailViewModel::class.java)

        return binding.root
    }


}
