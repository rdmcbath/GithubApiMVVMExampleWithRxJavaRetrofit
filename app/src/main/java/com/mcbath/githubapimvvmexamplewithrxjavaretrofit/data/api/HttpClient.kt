package com.mcbath.githubapimvvmexamplewithrxjavaretrofit.data.api

import okhttp3.OkHttpClient

class HttpClient {
    var okHttpClient : OkHttpClient = OkHttpClient().newBuilder().build()
    private object Instance {
        val instanceClient : OkHttpClient = HttpClient().okHttpClient
    }
    companion object {
        val client : OkHttpClient by lazy { Instance.instanceClient }
    }
}