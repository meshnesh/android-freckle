package com.example.antonynganga.androidfreckle.model.auth

import java.io.Serializable

data class Token(val scope: String,
                 val token_type: String,
                 var expires_in: Long,
                 val id_token: String,
                 val access_token: String) : Serializable
