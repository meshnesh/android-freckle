package com.example.antonynganga.androidfreckle.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseMVPActivity<in V : BaseMVPView, P : BaseMVPPresenter<V>>
    : AppCompatActivity(), BaseMVPView {

    abstract var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attachView(this as V)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}