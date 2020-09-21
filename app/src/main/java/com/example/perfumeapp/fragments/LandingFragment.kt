package com.example.perfumeapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.myperfumeapp.R
import com.example.myperfumeapp.databinding.FragmentLanding2Binding

class LandingFragment: Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        super.onCreateView(inflater, container, savedInstanceState)
        val binding: FragmentLanding2Binding = DataBindingUtil.inflate( inflater,
            R.layout.fragment_landing2, container, false)
        binding.myPerfumes.setOnClickListener({
            it.findNavController().navigate(R.id.action_landingFragment_to_perfumeDashboard)
        })
        return binding.root

    }
}