package com.example.myapplication.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.example.myapplication.R
import com.example.myapplication.databinding.CatItemBinding
import com.example.myapplication.databinding.CatListLayoutBinding
import com.example.myapplication.databinding.SaveCatBreedBinding
import com.example.myapplication.viewmodels.CatListViewModel
import com.example.myapplication.viewmodels.CatViewModel
import com.example.myapplication.viewmodels.SaveCatBreedViewModel
import com.example.myapplication.views.adapters.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SaveCatBreedFragment: Fragment() {
    private val mViewModel: SaveCatBreedViewModel by viewModels()
    val mArguments: SaveCatBreedFragmentArgs by navArgs()

    private lateinit var mBinding: SaveCatBreedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(R.transition.fade_transition)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.save_cat_breed, container, false )
        mBinding.viewModel = mViewModel
        mBinding.catModel = mArguments.catViewModel
        mBinding.lifecycleOwner = viewLifecycleOwner
        return mBinding.root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.onBreedSaved.observe(viewLifecycleOwner, Observer {
            if (it) {
                findNavController().popBackStack()
            }
            else {
                Toast.makeText(requireContext(), "ERROR", Toast.LENGTH_SHORT).show()
            }
        })
    }
}