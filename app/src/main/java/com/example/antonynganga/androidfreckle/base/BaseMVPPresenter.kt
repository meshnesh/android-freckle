package com.example.antonynganga.androidfreckle.base

interface BaseMVPPresenter<in V : BaseMVPView> {
    fun attachView(view: V)
    fun detachView()
}