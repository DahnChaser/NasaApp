package com.dev.nasa_app.viewmodel

import retrofit2.Call
import retrofit2.http.*


interface Webservice {

    //  @GET("search?q=milky%20way&media_type=image&year_start=2017& year_end=2017")
    //    suspend fun getMemberPermission(
    //        @HeaderMap headers: Map<String, String>,
    //        @Query("groupid") groupid: Int,
    //        @Query("phonenumber") phonenumber: String
    //    ): ResponseWrapper

//    @GET("mobile/approveloanpayment/{action}")
//    suspend fun approveDeclineGrpLoanPayment(
//        @HeaderMap headers: Map<String, String>,
//        @Path("action") action: String,
//        @Query("loanpaymentrecord") loanpaymentrecord: Int
//    ): ResponseWrapper

   // @GET("{action}?q={keywords}&media_type={media_type}&year_start={year_start}&year_end={year_end}")
    @GET("search")
    suspend fun getGalaxyListreq(
        @Query("q") keyword: String,
        @Query("media_type") type: String,
        @Query("year_start") start: String,
        @Query("year_end") end: String,
    ): ResponseWrapper
//https://images-api.nasa.gov/search?q=milky%20way&media_type=image&year_start=2017& year_end=2017

    @GET(".")
    fun getJsonData(
        @Query("q") text: String?,
        @Query("langpair") langpair: String?
    ): ResponseWrapper
}
