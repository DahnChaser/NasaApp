package com.dev.nasa_app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dev.nasa_app.R
import com.dev.nasa_app.databinding.FragmentSelectedGalaxyBinding
import com.dev.nasa_app.models.GalaxyModelWrapper
import com.dev.nasa_app.viewmodel.VModel


class SelectedGalaxyFragment : Fragment() {

    lateinit var groupViewModel: VModel
    private lateinit var binding: FragmentSelectedGalaxyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSelectedGalaxyBinding.inflate(inflater,container,false);

        initData()
        initUI()

        return binding.root;
    }

    private fun initUI() {
        binding.materialToolbar.setNavigationIcon(R.drawable.im_back)
        binding.materialToolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
    }

    private fun initData() {
        groupViewModel = ViewModelProvider(requireActivity())[VModel::class.java]
        groupViewModel.selectedGalaxyItemObservable.observe(viewLifecycleOwner) { mGalaxyModelWrapper ->

            refreshUI(mGalaxyModelWrapper)

        }
    }

    private fun refreshUI(mGalaxyModelWrapper: GalaxyModelWrapper) {
        val title = mGalaxyModelWrapper.data[0].title
        val dateCreated = mGalaxyModelWrapper.data[0].dateCreated
        val center = mGalaxyModelWrapper.data[0].center
        val description = mGalaxyModelWrapper.data[0].description
        val imageUrl = mGalaxyModelWrapper.links[0].href

        binding.tvTitle.text = title
        binding.tvDateCreatedVal.text = dateCreated
        binding.tvCenterVal.text = center
        binding.tvDescription.text = description

        Glide
            .with(requireContext())
            .load(imageUrl)
            .placeholder(R.drawable.no_image)
            .into(binding.imBackground);
    }


}