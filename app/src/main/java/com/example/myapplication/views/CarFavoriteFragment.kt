package com.example.myapplication.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.myapplication.R
import com.example.myapplication.databinding.FavoritesFragmentBinding
import com.example.myapplication.databinding.FavoritesItemBinding
import com.example.myapplication.viewmodels.CatViewModel
import com.example.myapplication.viewmodels.FavoritesCatListViewModel
import com.example.myapplication.views.adapters.*
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class CarFavoriteFragment: Fragment() {
    open val mViewModel: FavoritesCatListViewModel by viewModels()
    private lateinit var mBinding: FavoritesFragmentBinding

    private val mAdapter =
        SimpleListAdapter(
            HolderCreator(::createHolder),
            HolderBinder(::bindHolder),
            CatDiffCallback()
        )

    private fun bindHolder(viewModel: CatViewModel, holder: Holder<FavoritesItemBinding>){
        holder.binding.viewModel = viewModel
        holder.binding.mainViewModel = mViewModel
    }

    private fun createHolder(parent: ViewGroup): Holder<FavoritesItemBinding> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FavoritesItemBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.favorites_fragment,container, false )
        mBinding.viewModel = mViewModel
        mBinding.lifecycleOwner = viewLifecycleOwner
        mBinding.catList.adapter = mAdapter
        return mBinding.root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.catList.observe(viewLifecycleOwner, Observer {
            mAdapter.submitList(it)
        })
    }
}