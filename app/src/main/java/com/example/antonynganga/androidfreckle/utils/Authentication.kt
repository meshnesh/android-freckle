package com.example.antonynganga.androidfreckle.utils

import android.content.Context
import android.widget.Toast
import com.example.antonynganga.androidfreckle.model.auth.Token
import com.google.gson.Gson
import timber.log.Timber
import java.util.*


object Authentication {

    private val authentication = "authentication"
    private val token = "token"

    private fun put(context: Context, obj: Token): Boolean {
        val preferences = context.getSharedPreferences(authentication, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        return editor.putString(token, Gson().toJson(obj)).commit()
    }

    fun get(context: Context): Token? {
        val preferences = context.getSharedPreferences(authentication, Context.MODE_PRIVATE)
        val json = preferences.getString(token, null)
        if(json != null) return Gson().fromJson(json, Token::class.java)
        return null
    }

    fun save(context: Context, obj: Token): Boolean {
        val calendar = GregorianCalendar.getInstance()
        var expiresIn: Long = calendar.time.time
        expiresIn += obj.expires_in * 1000
        obj.expires_in = expiresIn

        return put(context, obj)
    }

    fun isExpired(context: Context): Boolean {
        val token = get(context)
        if(token != null) {
            val calendar = GregorianCalendar.getInstance()
            val currentTime = calendar.time.time
            val expiresIn = token.expires_in
            if (expiresIn == 0L) throw WithoutAuthenticatedException()
            return currentTime > expiresIn
        } else {
            throw WithoutAuthenticatedException()
        }
    }

    fun isAuthenticated(context: Context): Boolean = !isExpired(context)

    class WithoutAuthenticatedException : Exception()

    fun delete(context: Context) {
        val preferences = context.getSharedPreferences(authentication, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.remove(token).apply()
    }
}