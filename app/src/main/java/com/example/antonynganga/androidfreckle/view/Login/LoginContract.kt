package com.example.antonynganga.androidfreckle.view.Login

import com.example.antonynganga.androidfreckle.base.BaseMVPPresenter
import com.example.antonynganga.androidfreckle.base.BaseMVPView

object LoginContract {

    interface LoginView : BaseMVPView {
        fun onSuccess()
        fun onFailed(e: String)
        fun onError(e: Throwable)
    }

    interface LoginPresenter : BaseMVPPresenter<LoginView> {
        fun login(email: String, password: String)
        fun cancel()
    }
}