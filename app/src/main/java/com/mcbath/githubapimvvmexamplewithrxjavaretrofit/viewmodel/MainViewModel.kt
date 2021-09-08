package com.mcbath.githubapimvvmexamplewithrxjavaretrofit.viewmodel

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mcbath.githubapimvvmexamplewithrxjavaretrofit.data.GithubApi
import com.mcbath.githubapimvvmexamplewithrxjavaretrofit.data.api.ApiClient
import com.mcbath.githubapimvvmexamplewithrxjavaretrofit.data.model.GithubAccount
import com.mcbath.githubapimvvmexamplewithrxjavaretrofit.data.repository.GithubRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.android.schedulers.AndroidSchedulers.*
import io.reactivex.disposables.Disposable
import io.reactivex.internal.operators.flowable.FlowableReplay.observeOn
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

class MainViewModel(private val githubRespository: GithubRepository) : ViewModel() {

    /*LiveData has no public available methods to update the stored data. The MutableLiveData
    class exposes the setValue(T) and postValue(T) methods public and you must use these if you
    need to edit the value stored in a LiveData object. Usually MutableLiveData is used in the
    ViewModel and then the ViewModel only exposes immutable LiveData objects to the observers.*/

    private val _githubAccount: MutableLiveData<GithubAccount> = MutableLiveData()
    val githubAccount: LiveData<GithubAccount> = _githubAccount

    //for checking internet connection, we can inform the UI about the state of network connection
    private val _connection = MutableLiveData<Boolean>()
    val connection: LiveData<Boolean> = _connection

    fun getGithubAccount(name : String) {
        githubRespository
            .fetchGithubAccount(name)
            .subscribe {
                _githubAccount.postValue(it)
            }
    }

    // coroutine to check the network state
    fun checkInternetConnection() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Connect to Google DNS to check for connection
                val timeoutMs = 1500
                val socket = Socket()
                val socketAddress = InetSocketAddress("8.8.8.8", 53)

                socket.connect(socketAddress, timeoutMs)
                socket.close()

                _connection.postValue(true)
            }
            catch(ex: IOException) {
                _connection.postValue(false)
            }
        }
    }
}

