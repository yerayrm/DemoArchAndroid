package com.yerayrm.dm.heroes.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class HeroesResponse(
    val code: Int,
    val status: String,
    val data: DataResponse?
)

data class DataResponse(
    val offset: Int,
    val limit: Int,
    val results: List<Hero>?
)

@Parcelize
data class Hero(
    val id: Long,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail,
    val comics: Amount,
    val series: Amount,
    val stories: Amount,
    val events: Amount
): Parcelable

@Parcelize
data class Thumbnail(
    val path: String,
    val extension: String
): Parcelable

@Parcelize
data class Amount(
    val available: Int,
    val returned: Int
): Parcelable
