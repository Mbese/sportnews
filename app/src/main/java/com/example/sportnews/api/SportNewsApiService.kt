package com.example.sportnews.api

import com.example.sportnews.model.FullArticleModel
import com.example.sportnews.model.SportNewsModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SportNewsApiService {
    @GET("news?format=json")
    fun getSportNews() : Call<List<SportNewsModel>>

    @GET("{SiteName}/{UrlName}/news/{UrlFriendlyDate}/{UrlFriendlyHeadline}?format=json")
    fun getFullArticle(@Path("SiteName") siteName: String?, @Path("UrlName") urlName: String?,
                       @Path("UrlFriendlyDate") urlFriendlyDate: String?, @Path("UrlFriendlyHeadline") urlFriendlyHeadline: String?) : Call<FullArticleModel>

}