package com.example.antonynganga.androidfreckle.api.auth

import android.content.Context
import com.example.antonynganga.androidfreckle.api.APISettings
import com.example.antonynganga.androidfreckle.api.base.APICreator
import br.com.mirabilis.oauth2authentication.model.oauth.request.Auth
import com.example.antonynganga.androidfreckle.R
import com.example.antonynganga.androidfreckle.model.auth.Token
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object AuthFetcher {

    class AuthFetcherImpl(private val context: Context,
                          private val listener: Listener
    ){

        private var callback: Call<Token>? = null

        fun auth(auth: Auth) {
            val authFetcher = APICreator(AuthAPI::class.java, APISettings.base).generate()
            callback = authFetcher.auth(auth)
            callback?.enqueue(object : Callback<Token> {

                override fun onResponse(call: Call<Token>?, response: Response<Token>?) {
                    if(response != null){
                        if(response.isSuccessful){
                            listener.onSuccess(response.body())
                        }else{
                            listener.onSuccess(null)
                        }
                    }else{
                        listener.onError(Throwable(context.getString(R.string.auth_error)))
                    }
                }

                override fun onFailure(call: Call<Token>?, t: Throwable?) {
                    val msg = context.getString(R.string.auth_error)
                    listener.onError(Throwable("$msg : ${t?.message}"))
                }
            })
        }

        fun cancel(){
            callback?.cancel()
        }
    }

    interface Listener {
        fun onSuccess(token: Token?)

        fun onError(throwable: Throwable)
    }
}