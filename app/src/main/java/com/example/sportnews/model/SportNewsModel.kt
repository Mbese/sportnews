package com.example.sportnews.model

data class SportNewsModel(val Headline: String, val ID: Int, val Blurb: String, val SmallImageName: String, val SmallImageAlt: String,
                          val LargeImageName: String, val LargeImageAlt: String, val ExtraImageName: String, val ExtraImageAlt: String,
                          val ImageUrlLocal: String, val ImageUrlRemote: String, val DateCreated: String, val Category: String,
                          val CategoryDisplayName: String, val CategoryId: String, val SiteId: Int, val SiteName: String,
                          val Author: String, val Credit: String, val CreditImageUrl: String, val CreditUrl: String,
                          val UrlName: String, val LiveChat: Boolean, val WebOnly: String, val UrlFriendlyHeadline: String,
                          val UrlFriendlyDate: String, val IsMainStory: String, val UpdateDate: String, val Keyword: String,
                          val Active: Boolean, val ValidFrom: String, val ValidTo: String, val relatedArticles: String)