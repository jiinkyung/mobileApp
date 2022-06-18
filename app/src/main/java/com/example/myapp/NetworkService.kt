package com.example.myapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkService {
    @GET("{api_key}/json/ListPriceModelStoreService/1/10/")
    fun getList(
        @Path("api_key") apiKey: String?
        //@Query("KEY") apiKey:String?,
        //@Query("TYPE") type: String?,
        //@Query("pSize") pageSize: Int,
        //@Query("pIndex") page:Int
    ): Call<ItemModel>

}