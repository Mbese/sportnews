package com.example.sportnews.repo

import android.arch.lifecycle.MutableLiveData
import com.example.sportnews.api.SportNewsApiClient
import com.example.sportnews.model.FullArticleModel
import com.example.sportnews.model.SportNewsModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SportNewsRepository : ISportNewsRepository {

    private val apiService = SportNewsApiClient.getService()

    override fun getSportNews(liveData: MutableLiveData<List<SportNewsModel>>) {
        apiService.getSportNews().enqueue(object : Callback<List<SportNewsModel>> {
            override fun onResponse(call: Call<List<SportNewsModel>>, response: Response<List<SportNewsModel>>) {
                if (response.isSuccessful && response.body() != null) {
                    liveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<SportNewsModel>>, t: Throwable) {
               liveData.value = null
            }
        })
    }

    override fun getFullArticle(liveData: MutableLiveData<FullArticleModel>, siteName: String?,
                                urlName: String?, urlFriendlyDate: String?, urlFriendlyHeadline: String?) {
        apiService.getFullArticle(siteName, urlName, urlFriendlyDate, urlFriendlyHeadline)

        apiService.getFullArticle(siteName, urlName, urlFriendlyDate, urlFriendlyHeadline).enqueue(object : Callback<FullArticleModel> {
            override fun onResponse(call: Call<FullArticleModel>, response: Response<FullArticleModel>) {
                if (response.isSuccessful && response.body() != null) {
                    liveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<FullArticleModel>, t: Throwable) {
                liveData.value = null
            }
        })
    }

}