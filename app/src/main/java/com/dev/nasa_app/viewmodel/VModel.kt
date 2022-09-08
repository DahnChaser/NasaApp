package com.dev.nasa_app.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.nasa_app.models.GalaxyModelWrapper
//import com.ekenya.echama.repository.MetadataResponse
//import com.ekenya.echama.repository.MetadataResponse
import org.json.JSONObject

/**
 * Group viewmodel
 * @author Oscar Muigai
 */
class VModel :ViewModel(){

    val groupRepository : GroupRepository =  GroupRepository(viewModelScope )
    val myGroupApiResponseLD =  groupRepository.myApiResponse
    val selectedGalaxyItemObservable = MutableLiveData<GalaxyModelWrapper>()

    fun getGalaxyList(jsonDetails: HashMap<String, String>) {
        groupRepository.doGetGalaxyList(jsonDetails)
    }

}
