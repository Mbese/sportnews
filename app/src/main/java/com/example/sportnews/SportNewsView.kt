package com.example.sportnews

import com.example.sportnews.model.SportNewsModel

interface SportNewsView {
    fun onGetSportNewsSuccess(sportList: List<SportNewsModel>)
    fun onGetSportNewFailure()
}