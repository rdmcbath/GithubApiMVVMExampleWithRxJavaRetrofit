package com.mcbath.githubapimvvmexamplewithrxjavaretrofit.data.model

import com.google.gson.annotations.SerializedName

data class GithubAccount(
    @SerializedName("login") var login: String = "",
    @SerializedName("id") var id: Int = 0,
    @SerializedName("created_at") var createdAt: String = "",
    @SerializedName("updated_at") var updatedAt: String = "",
    @SerializedName("avatar_url") var imageUrl: String = ""
)
