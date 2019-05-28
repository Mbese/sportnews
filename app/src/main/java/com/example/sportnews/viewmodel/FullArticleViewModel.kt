package com.example.sportnews.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.sportnews.model.FullArticleModel
import com.example.sportnews.repo.SportNewsRepository

class FullArticleViewModel : ViewModel() {
    val liveData = MutableLiveData <FullArticleModel>()
    val repo = SportNewsRepository()

    fun getFullArticle(siteName: String?, urlName: String?, urlFriendlyDate: String?, urlFriendlyHeadine: String?) {
        repo.getFullArticle(liveData, siteName, urlName, urlFriendlyDate, urlFriendlyHeadine)
    }
}