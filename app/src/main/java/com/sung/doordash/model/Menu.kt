package com.sung.doordash.model

import com.google.gson.annotations.SerializedName

data class Menu(
    @field:SerializedName("status_type") val statusType : String
)