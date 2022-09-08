package com.dev.nasa_app.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.nasa_app.models.GalaxyModelWrapper
//import com.ekenya.echama.repository.MetadataResponse
//import com.ekenya.echama.repository.MetadataResponse

/**
 * Group viewmodel
 * @author Oscar Muigai
 */
class VModel :ViewModel(){

    val mGalaxyListRepository : GalaxyListRepository =  GalaxyListRepository(viewModelScope )
    val myGroupApiResponseLD =  mGalaxyListRepository.myApiResponse
    val selectedGalaxyItemObservable = MutableLiveData<GalaxyModelWrapper>()

    fun getGalaxyList(jsonDetails: HashMap<String, String>) {
        mGalaxyListRepository.doGetGalaxyList(jsonDetails)
    }

}
