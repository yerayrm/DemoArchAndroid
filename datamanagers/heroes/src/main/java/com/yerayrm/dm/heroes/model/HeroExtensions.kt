package com.yerayrm.dm.heroes.model

fun Hero.getThumbnailUrl(): String {
    return this.thumbnail.path + "/standard_xlarge." + this.thumbnail.extension
}
