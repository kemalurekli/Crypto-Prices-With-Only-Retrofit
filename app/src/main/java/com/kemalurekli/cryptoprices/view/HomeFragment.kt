package com.kemalurekli.cryptoprices.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.kemalurekli.cryptoprices.adapter.RecyclerViewAdapter
import com.kemalurekli.cryptoprices.databinding.FragmentHomeBinding
import com.kemalurekli.cryptoprices.viewModel.HomeFragmentViewModel


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel : HomeFragmentViewModel
    private val priceAdapter = RecyclerViewAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[HomeFragmentViewModel::class.java]
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.refreshData()
        observeLiveData()
        binding.rV.adapter = priceAdapter
        binding.rV.layoutManager = GridLayoutManager(context, 2)
        binding.rV.setHasFixedSize(true)

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            viewModel.refreshData()
        }
    }
    private fun observeLiveData() {
        viewModel.prices.observe(viewLifecycleOwner, Observer { cryptoData ->
            cryptoData?.let {
                priceAdapter.updatePriceList(it)
                binding.rV.visibility = View.VISIBLE
                binding.pbLoading.visibility = View.GONE
                binding.tvError.visibility = View.GONE
            }
        })
        viewModel.error.observe(viewLifecycleOwner, Observer { cryptoError ->
            cryptoError?.let {
                if (it){
                    binding.rV.visibility = View.GONE
                    binding.pbLoading.visibility = View.GONE
                    binding.tvError.visibility = View.VISIBLE

                }else{
                    binding.tvError.visibility = View.GONE

                }
            }
        })
        viewModel.loading.observe(viewLifecycleOwner, Observer { cryptoLoading ->
            cryptoLoading?.let {
                if (it){
                    binding.rV.visibility = View.GONE
                    binding.tvError.visibility = View.GONE
                    binding.pbLoading.visibility = View.VISIBLE
                }else{
                    binding.pbLoading.visibility = View.GONE

                }
            }
        })
    }
}