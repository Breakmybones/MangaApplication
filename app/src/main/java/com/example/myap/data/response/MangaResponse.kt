package com.example.myap.data.response

import com.google.gson.annotations.SerializedName

data class MangaResponse(
        @SerializedName("data")
        val data: Data?,
        @SerializedName("pagination")
        val pagination: Pagination?
)

data class Data(
        @SerializedName("mal_id")
        val malId: Int,
        @SerializedName("url")
        val url: String,
        @SerializedName("images")
        val images: Images?,
        @SerializedName("approved")
        val approved: Boolean,
        @SerializedName("titles")
        val titles: List<Title>,
        @SerializedName("title")
        val title: String,
        @SerializedName("title_english")
        val titleEnglish: String,
        @SerializedName("title_japanese")
        val titleJapanese: String,
        @SerializedName("type")
        val type: String,
        @SerializedName("chapters")
        val chapters: Int,
        @SerializedName("volumes")
        val volumes: Int,
        @SerializedName("status")
        val status: String,
        @SerializedName("publishing")
        val publishing: Boolean,
        @SerializedName("published")
        val published: Published,
        @SerializedName("score")
        val score: Double,
        @SerializedName("scored_by")
        val scoredBy: Int,
        @SerializedName("rank")
        val rank: Int,
        @SerializedName("popularity")
        val popularity: Int,
        @SerializedName("members")
        val members: Int,
        @SerializedName("favorites")
        val favorites: Int,
        @SerializedName("synopsis")
        val synopsis: String,
        @SerializedName("background")
        val background: String,
        @SerializedName("authors")
        val authors: List<Author>,
        @SerializedName("serializations")
        val serializations: List<Serialization>,
        @SerializedName("genres")
        val genres: List<Genre>,
        @SerializedName("explicit_genres")
        val explicitGenres: List<Genre>,
        @SerializedName("themes")
        val themes: List<Theme>,
        @SerializedName("demographics")
        val demographics: List<Any>
)

data class Images(
        @SerializedName("jpg")
        val jpg: ImageUrls?,
        @SerializedName("webp")
        val webp: ImageUrls?
)

data class ImageUrls(
        @SerializedName("image_url")
        val imageUrl: String,
        @SerializedName("small_image_url")
        val smallImageUrl: String,
        @SerializedName("large_image_url")
        val largeImageUrl: String
)

data class Title(
        @SerializedName("type")
        val type: String,
        @SerializedName("title")
        val title: String
)

data class Published(
        @SerializedName("from")
        val from: String,
        @SerializedName("to")
        val to: String,
        @SerializedName("prop")
        val prop: PublicationDate
)

data class PublicationDate(
        @SerializedName("from")
        val from: DateInfo,
        @SerializedName("to")
        val to: DateInfo,
        @SerializedName("string")
        val string: String
)

data class DateInfo(
        @SerializedName("day")
        val day: Int,
        @SerializedName("month")
        val month: Int,
        @SerializedName("year")
        val year: Int
)

data class Author(
        @SerializedName("mal_id")
        val malId: Int,
        @SerializedName("type")
        val type: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("url")
        val url: String
)

data class Serialization(
        @SerializedName("mal_id")
        val malId: Int,
)

data class Genre(
        @SerializedName("mal_id")
        val malId: Int?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("type")
        val type: String?,
        @SerializedName("url")
        val url: String?
)

data class Theme(
        @SerializedName("mal_id")
        val malId: Int?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("type")
        val type: String?,
        @SerializedName("url")
        val url: String?
)
