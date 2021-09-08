package com.mcbath.githubapimvvmexamplewithrxjavaretrofit.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.mcbath.githubapimvvmexamplewithrxjavaretrofit.R
import com.mcbath.githubapimvvmexamplewithrxjavaretrofit.data.repository.RepositoryFactory
import com.mcbath.githubapimvvmexamplewithrxjavaretrofit.viewmodel.MainViewModel
import com.mcbath.githubapimvvmexamplewithrxjavaretrofit.viewmodel.ViewModelFactory
import io.reactivex.disposables.CompositeDisposable

class MainFragment: Fragment() {

    private var disposable: CompositeDisposable? = null
    private lateinit var mainViewModel : MainViewModel
    private var textView: TextView? = null
    private var imageView: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        disposable = CompositeDisposable()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        mainViewModel = ViewModelProvider(this, ViewModelFactory(RepositoryFactory.createGithubRepository())).get(MainViewModel::class.java)

        textView = view.findViewById(R.id.tv_content)
        imageView = view.findViewById(R.id.iv_content)

        checkInternetConnection()

        return view
    }

    private fun populateUI() {
        mainViewModel.getGithubAccount("G")
        mainViewModel.githubAccount.observe(viewLifecycleOwner, Observer {
            textView?.text = it.toString()

            val imageUrl = it.imageUrl
            imageView?.let { it -> Glide.with(requireActivity()).load(imageUrl).into(it) }
        })
    }

    private fun checkInternetConnection() {
        mainViewModel.checkInternetConnection()
        mainViewModel.connection.observe(viewLifecycleOwner) { hasInternet ->
            if(!hasInternet) {
                //hasn't connection
                textView?.text = getString(R.string.network_error)
            }
            else {
                //has connection
               populateUI()
            }
        }
    }
}