package com.example.perfumeapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.perfumeapp.adapter.PerfumeAdapter
import com.example.perfumeapp.database.PerfumeDatabase
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.example.myperfumeapp.R
import com.example.myperfumeapp.databinding.FragmentPerfumeDashboardBinding
import com.example.perfumeapp.repository.PerfumeRepository
import com.example.perfumeapp.service.PerfumeApi
import com.example.perfumeapp.service.PerfumeService

class PerfumeDashboard : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentPerfumeDashboardBinding =  DataBindingUtil.inflate(
            inflater, R.layout.fragment_perfume_dashboard, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = PerfumeDatabase.getInstance(application).perfumeDao

        val repo = PerfumeRepository(PerfumeApi.retrofitService,dataSource)

        val viewModelFactory = DashboardViewModelFactory(dataSource,repo, application)

        val dashboardViewModel =
            ViewModelProviders.of(
                this, viewModelFactory).get(DashboardViewModel::class.java)

        binding.dashboardViewModel = dashboardViewModel

//        dashboardViewModel.getAllPerfumes()
        dashboardViewModel.getPerfumesData()

        var adapter = PerfumeAdapter(PerfumeAdapter.OnClickListener {
            dashboardViewModel.displayPerfumeDetails(it)

        })

        var dividerItemDecoration = DividerItemDecoration(binding.perfumeList.context,
            VERTICAL
        )
        binding.perfumeList.addItemDecoration(dividerItemDecoration)

        binding.perfumeList.adapter = adapter


        binding.setLifecycleOwner(this)

        dashboardViewModel.perfumeData.observe(viewLifecycleOwner, Observer {
            it?.let{
                Log.d("perfume dashboard","List of perfumes is - ${it.size}")
                adapter.data = it
            }
        })

        dashboardViewModel.navigateToSelectedPerfume.observe(this, Observer {
            if ( null != it ) {
                this.findNavController().navigate(PerfumeDashboardDirections.actionPerfumeDashboardToPerfumeDetail(it))
                dashboardViewModel.displayComplete()
            }
        })



        return binding.root
    }


}
