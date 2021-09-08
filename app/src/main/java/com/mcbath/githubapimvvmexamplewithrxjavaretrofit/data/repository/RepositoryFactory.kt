package com.mcbath.githubapimvvmexamplewithrxjavaretrofit.data.repository

import com.mcbath.githubapimvvmexamplewithrxjavaretrofit.data.GithubApi
import com.mcbath.githubapimvvmexamplewithrxjavaretrofit.data.api.ApiClient

object RepositoryFactory {
    fun createGithubRepository() : GithubRepository {
        val githubApi = ApiClient.instance.retrofit.create(GithubApi::class.java)
        return GithubRepository(githubApi)
    }
}