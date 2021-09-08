package com.mcbath.githubapimvvmexamplewithrxjavaretrofit.data.repository


import com.mcbath.githubapimvvmexamplewithrxjavaretrofit.data.GithubApi
import com.mcbath.githubapimvvmexamplewithrxjavaretrofit.data.model.GithubAccount
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GithubRepository(val githubApi: GithubApi) {

    fun fetchGithubAccount(name : String) : Observable<GithubAccount> {
        return Observable.create { emitter ->

            githubApi.getGithubAccount(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( {
                    if (it.body() != null) {
                        emitter.onNext(it.body()!!)
                    }
                }, {
                    it.printStackTrace()
                })

        }
    }

}