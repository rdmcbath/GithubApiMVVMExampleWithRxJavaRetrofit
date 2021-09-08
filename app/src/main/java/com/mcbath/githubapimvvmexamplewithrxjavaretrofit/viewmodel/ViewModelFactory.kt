package com.mcbath.githubapimvvmexamplewithrxjavaretrofit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mcbath.githubapimvvmexamplewithrxjavaretrofit.data.repository.GithubRepository

class ViewModelFactory(private val githubRespository: GithubRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(githubRespository) as T
    }

}