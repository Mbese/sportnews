package com.example.sportnews.repo

import android.arch.lifecycle.MutableLiveData
import com.example.sportnews.model.FullArticleModel
import com.example.sportnews.model.SportNewsModel

interface ISportNewsRepository {
    fun getSportNews(liveData: MutableLiveData<List<SportNewsModel>>)
    fun getFullArticle(liveData: MutableLiveData<FullArticleModel>, siteName: String?, urlName: String?,
                       urlFriendlyDate: String?, urlFriendlyHeadline: String?)
}