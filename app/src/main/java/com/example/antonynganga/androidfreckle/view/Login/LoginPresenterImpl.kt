package com.example.antonynganga.androidfreckle.view.Login

import com.example.antonynganga.androidfreckle.api.auth.AuthFetcher
import br.com.mirabilis.oauth2authentication.model.oauth.request.Auth
import com.example.antonynganga.androidfreckle.R
import com.example.antonynganga.androidfreckle.base.BaseMVPPresenterImpl
import com.example.antonynganga.androidfreckle.model.auth.Token
import com.example.antonynganga.androidfreckle.utils.Authentication
import timber.log.Timber

class LoginPresenterImpl : BaseMVPPresenterImpl<LoginContract.LoginView>(),
        LoginContract.LoginPresenter {

    private var authFetcher: AuthFetcher.AuthFetcherImpl?= null

    override fun login(email: String, password: String) {
        authFetcher = AuthFetcher.AuthFetcherImpl(getContext(), object : AuthFetcher.Listener {
            override fun onSuccess(token: Token?) {
                Timber.d("This is the token!", token);
                if(token == null) {
                    view?.let { view -> call(view,
                            getContext().getString(R.string.auth_invalid),
                            view::onFailed)
                    }
                } else {
                    Authentication.save(getContext(), token)
                    view?.let { view -> call(view, view::onSuccess)}
                }
            }
            override fun onError(throwable: Throwable) {
                view?.let { view -> call(view, throwable, view::onError) }
            }
        })
        authFetcher?.auth(Auth(email, password))
    }

    override fun cancel() {
        authFetcher?.cancel()
    }
}