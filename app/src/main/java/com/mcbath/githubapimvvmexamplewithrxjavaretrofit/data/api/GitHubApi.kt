package com.mcbath.githubapimvvmexamplewithrxjavaretrofit.data

import com.mcbath.githubapimvvmexamplewithrxjavaretrofit.data.model.GithubAccount
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    @GET("/users/{username}")
    fun getGithubAccount(@Path("username") username: String): Single<Response<GithubAccount>>
}