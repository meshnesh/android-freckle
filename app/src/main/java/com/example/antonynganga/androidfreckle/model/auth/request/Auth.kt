package br.com.mirabilis.oauth2authentication.model.oauth.request

import java.io.Serializable

data class Auth(val email: String,
                val password: String) : Serializable
