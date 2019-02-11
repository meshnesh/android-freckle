package com.example.antonynganga.androidfreckle.view.Login

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import com.example.antonynganga.androidfreckle.R.layout.login_activity
import com.example.antonynganga.androidfreckle.base.BaseMVPActivity
import com.example.antonynganga.androidfreckle.view.DashBoard.MainActivity
import kotlinx.android.synthetic.main.login_activity.*

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : BaseMVPActivity<LoginContract.LoginView, LoginContract.LoginPresenter>(),
    LoginContract.LoginView {

    override var presenter: LoginContract.LoginPresenter = LoginPresenterImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(login_activity)

        login_button.setOnClickListener {
            if(isValid()){
                loading(true)

                presenter.login(txtEmail.text.toString(), txtPassword.text.toString())
            }
        }
    }

    private fun isValid(): Boolean {
        if(txtEmail?.text.toString().isNullOrEmpty()) return false
        if(txtPassword?.text.toString().isNullOrEmpty()) return false
        return true
    }

    override fun onSuccess() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun loading(value: Boolean){
        if(value) progressBar.visibility = View.VISIBLE
        else progressBar.visibility = View.GONE

        if(!value){
            txtEmail.text.clear()
            txtPassword.text.clear()
        }

        if(!value) login_layout_form.visibility = View.VISIBLE
        else login_layout_form.visibility = View.GONE
    }

    override fun onFailed(e: String) {
        loading(false)
        Snackbar.make(container, e, Snackbar.LENGTH_LONG)
    }

    override fun onError(e: Throwable) {
        loading(false)
        Snackbar.make(container, e.message.toString(), Snackbar.LENGTH_LONG)
    }
}