package com.dev.nasa_app.viewmodel

import androidx.lifecycle.MutableLiveData
import com.dev.nasa_app.utils.Utils.BASEURL
import com.dev.nasa_app.utils.Utils.CONST_GETGALAXYREQ
import com.dev.networking_library.RestClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException

class GroupRepository(val scope: CoroutineScope) {

    var myApiResponse: MutableLiveData<MyApiResponse> = MutableLiveData<MyApiResponse>()
    var myError = MyError()
    var message: String = ""

    fun doGetGalaxyList(reqParamsObj: java.util.HashMap<String, String>) {
        scope.launch {

            try {

                val apiService  = RestClient.mRetrofitInstance(BASEURL,false).create(Webservice::class.java)

                val apiRes = apiService.getGalaxyListreq(
                    reqParamsObj["q"].toString(),
                    reqParamsObj["media_type"].toString(),
                    reqParamsObj["year_start"].toString(),
                    reqParamsObj["year_end"].toString()
                )


                if (apiRes.collection == null) {
                    myError.message = "No data try again later"
                    myApiResponse.postValue(
                        MyApiResponse(
                            400,
                            CONST_GETGALAXYREQ,
                            myError.message
                        )
                    )
                }
                else
                {
                    var mGalaxyModelWrapperList = apiRes.collection!!.mGalaxyModelWrapper
                    myApiResponse.postValue(
                        MyApiResponse(
                            200,
                            CONST_GETGALAXYREQ,
                            "success",
                            mGalaxyModelWrapperList
                        )
                    )

                }

            }

            catch (e: Exception) {
                if (e is IOException || e is UnknownHostException) {
                    message = myError.getMessagebyCode()

                } else if (e is HttpException) {
                    val jerror: String = e.response()?.errorBody()?.string()!!
                    message = myError.getMessagebyCode(e.code())
                }
                myApiResponse.postValue(
                    MyApiResponse(
                        400,
                        "getGalaxyList",
                        message
                    )
                )
            }

        }

    }

}


