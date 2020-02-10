package com.project.seungjun

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseAndroidViewModel(application: Application) : AndroidViewModel(application) {


    protected val baseDisposable: CompositeDisposable by lazy { CompositeDisposable() }


    override fun onCleared() {
        baseDisposable.clear()
        super.onCleared()
    }
}