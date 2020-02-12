package com.project.seungjun

import androidx.lifecycle.ViewModel
import com.project.network.NetworkBinder
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {


    protected val baseDisposable: CompositeDisposable by lazy { CompositeDisposable() }


    override fun onCleared() {
        baseDisposable.clear()
        super.onCleared()
    }
}