package com.example.sportnews.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.sportnews.model.SportNewsModel
import com.example.sportnews.repo.SportNewsRepository

class SportNewsViewModel : ViewModel() {
    val liveData = MutableLiveData <List<SportNewsModel>>()
    val repo = SportNewsRepository()

    fun getSportNews(){
        repo.getSportNews(liveData)
    }

}