package com.example.sportnews.model

data class FullArticleModel(
    val Body: String, val Links: String, val Facebook: String, val AuthorUrlName: String,
    val ID: Int, val Blurb: String, val SmallImageName: String, val SmallImageAlt: String,
    val LargeImageName: String, val LargeImageAlt: String, val ExtraImageName: String, val ExtraImageAlt: String,
    val ImageUrlLocal: String, val ImageUrlRemote: String, val DateCreated: String, val Category: String,
    val CategoryDisplay: String, val CategoryId: Int, val SiteId: Int, val SiteName: String,
    val Author: String, val Credit: String, val CreditImageUrl: String, val CreditUrl: String,
    val UrlName: String, val LiveChat: Boolean, val WebOnly: Boolean, val UrlFriendlyHeadline: String,
    val UrlFriendlyDate: String, val IsMainStory: String, val UpdatedDate: String, val Keywords: String,
    val Active: Boolean, val ValidFrom: String, val ValidTo: String, val relatedArticles: String
)