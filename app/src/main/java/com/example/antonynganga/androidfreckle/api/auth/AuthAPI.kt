package com.example.antonynganga.androidfreckle.api.auth

import br.com.mirabilis.oauth2authentication.model.oauth.request.Auth
import com.example.antonynganga.androidfreckle.model.auth.Token
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthAPI {

    /**
     * Your endpoint of auth
     */
    @POST("/api/auth/login")
    fun auth(@Body auth: Auth): Call<Token>
}