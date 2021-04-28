package com.sung.doordash.model

import com.google.gson.annotations.SerializedName

data class Status(
    @field:SerializedName("asap_minutes_range") val asapMins: List<Int>,
    var isStoreOpen: Boolean
)