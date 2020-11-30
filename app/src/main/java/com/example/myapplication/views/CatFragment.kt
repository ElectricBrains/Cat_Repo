package com.example.myapplication.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.CallSuper
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.CatItemBinding
import com.example.myapplication.databinding.CatListLayoutBinding
import com.example.myapplication.viewmodels.CatListViewModel
import com.example.myapplication.viewmodels.CatViewModel
import com.example.myapplication.views.adapters.*
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
open class CatFragment: Fragment() {
    open val mViewModel: CatListViewModel by viewModels()
    private val mAdapter = SimpleListAdapter(
        HolderCreator(::createHolder),
        HolderBinder(::bindHolder),
        CatDiffCallback()
    )

    private fun bindHolder(viewModel: CatViewModel, holder: Holder<CatItemBinding>){
        holder.binding.viewModel = viewModel
        holder.binding.mainViewModel = mViewModel
        holder.binding.catAvatar.setOnClickListener {

            val extras =
                FragmentNavigator.Extras.Builder()
                    .addSharedElement(holder.binding.catAvatar, "image")
                    .build()
            findNavController().navigate(CatFragmentDirections.actionCatFragmentToSaveCatBreed(viewModel), extras)
        }
    }

    private fun createHolder(parent: ViewGroup): Holder<CatItemBinding> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CatItemBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }

    private lateinit var mBinding: CatListLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.cat_list_layout,container, false )
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