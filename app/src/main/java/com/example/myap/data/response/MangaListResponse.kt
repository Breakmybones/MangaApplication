package com.example.myap.data.response

import com.google.gson.annotations.SerializedName

data class MangaListResponse (
    @SerializedName("data")
    val data: List<Data>,
    @SerializedName("pagination")
    val pagination: Pagination?,
)

data class Pagination(
    @SerializedName("current_page")
    val currentPage: Int?,
    @SerializedName("has_next_page")
    val hasNextPage: Boolean?,
    @SerializedName("items")
    val items: Items?,
    @SerializedName("last_visible_page")
    val lastVisiblePage: Int?
)

data class Items(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("per_page")
    val perPage: Int?,
    @SerializedName("total")
    val total: Int?
)
