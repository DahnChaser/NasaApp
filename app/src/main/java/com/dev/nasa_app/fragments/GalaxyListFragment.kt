package com.dev.nasa_app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev.nasa_app.R
import com.dev.nasa_app.adapters.GalaxyListAdapter
import com.dev.nasa_app.databinding.FragmentGalaxyListBinding
import com.dev.nasa_app.interfaces.OnItemClickListener
import com.dev.nasa_app.models.GalaxyModelWrapper
import com.dev.nasa_app.utils.Utils
import com.dev.nasa_app.viewmodel.VModel


class GalaxyListFragment : Fragment(),OnItemClickListener {

    private lateinit var binding: FragmentGalaxyListBinding
    private lateinit var mGalaxyListAdapter: GalaxyListAdapter
    private var mGalaxyModelWrapperList: ArrayList<GalaxyModelWrapper> = ArrayList()
    lateinit var galaxyViewModel: VModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvGalaxyList.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View?
    {
        binding = FragmentGalaxyListBinding.inflate(inflater,container,false);
        galaxyViewModel = ViewModelProvider(requireActivity())[VModel::class.java]
        binding.shimmerViewContainer.duration = 800

        initData()

        return binding.root;
    }

    private fun initGalaxyListRV(grouplist: ArrayList<GalaxyModelWrapper>) {
        if (grouplist.isNotEmpty()) {
            mGalaxyModelWrapperList = grouplist
        }
        mGalaxyListAdapter = GalaxyListAdapter(mGalaxyModelWrapperList,this)
        binding.rvGalaxyList.adapter = mGalaxyListAdapter
        mGalaxyListAdapter.notifyDataSetChanged()

    }

    override fun onResume() {
        super.onResume()
        binding.shimmerViewContainer.startShimmerAnimation()
    }

    override fun onPause() {
        binding.shimmerViewContainer.stopShimmerAnimation()
        super.onPause()
    }


    private fun initData() {

        isLoading(true)

        val reqParamsObj = HashMap<String,String>()
        reqParamsObj["q"] = "milky way"
        reqParamsObj["media_type"] = "image"
        reqParamsObj["year_start"] = "2017"
        reqParamsObj["year_end"] = "2017"


        galaxyViewModel.getGalaxyList(reqParamsObj)

        galaxyViewModel.myGroupApiResponseLD.observe(viewLifecycleOwner) { myApiResponse ->

            isLoading(false)

            if (myApiResponse.code == 200) {
                if (myApiResponse.requestName.contentEquals(Utils.CONST_GETGALAXYREQ)) {
                    mGalaxyModelWrapperList = myApiResponse.responseObj as ArrayList<GalaxyModelWrapper>

                    if (mGalaxyModelWrapperList.isEmpty()) {
                        Toast.makeText(requireContext(),"",Toast.LENGTH_LONG).show()
                    } else {
                        initGalaxyListRV(mGalaxyModelWrapperList)

                    }
                }
            }
            else if (myApiResponse.code == 700) {
                Toast.makeText(requireContext(),myApiResponse.message,Toast.LENGTH_LONG).show()
            }
            else {
                if (myApiResponse.message.isNotEmpty()) {

                    Toast.makeText(requireContext(),myApiResponse.message,Toast.LENGTH_LONG).show()

                }
            }
        }

    }

    private fun isLoading(isLoading:Boolean)
    {
        if(isLoading)
        {
           // binding.loadingAnimator.visibility = View.VISIBLE
            binding.shimmerViewContainer.startShimmerAnimation();
            binding.shimmerViewContainer.setVisibility(View.VISIBLE);
        }
        else
        {
          //  binding.loadingAnimator.visibility = View.GONE
            binding.shimmerViewContainer.stopShimmerAnimation();
            binding.shimmerViewContainer.visibility = View.GONE;

        }
    }

    override fun onItemClick(mGalaxyModelWrapper: GalaxyModelWrapper?) {
        galaxyViewModel.selectedGalaxyItemObservable.postValue(mGalaxyModelWrapper)
        findNavController().navigate(R.id.action_galaxyListFragment_to_selectedGalaxyFragment)
    }
}