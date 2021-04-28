package com.sung.doordash.model

import com.google.gson.annotations.SerializedName

data class Restaurant(
    val id: Int,
    val name: String,
    val description: String,
    @field:SerializedName("cover_img_url") val coverImageUrl: String,
    val status: Status
)