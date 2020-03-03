package com.pg.purvapractical

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.notimp.util.DialogUtil
import com.notimp.util.NetworkUtils
import com.pg.purvapractical.model.request_model.LoginRequest
import com.pg.rxjava.webservice.NetworkCallback
import com.pg.rxjava.webservice.RestClient
import com.pg.rxjava.webservice.response.LoginResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*
import java.util.regex.Pattern


class LoginActivity : AppCompatActivity(), NetworkCallback, View.OnClickListener {
    private var TAG = "LoginActivity"
    private val disposable = CompositeDisposable()

    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        btnGoNextScreen.setOnClickListener(this)
        btnLogin.setOnClickListener(this)
    }

    /**
     * Call login api
     */
    private fun callLoginApi() {
        DialogUtil.showProgress(getString(R.string.login_dialog), this)
        //Call api
        val loginRequest = LoginRequest()
        loginRequest.email = edtEmail.text.toString()
        loginRequest.password = edtPassword.text.toString()
        val subscribe = RestClient.get()!!
            .performLogin(loginRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ loginResponse ->
                this.onSuccess(
                    "LoginResponse",
                    loginResponse
                )
                DialogUtil.dismissProgress()
            },
                { throwable ->
                    this.onFail("LoginResponse", throwable)
                    DialogUtil.dismissProgress()

                    Toast.makeText(this, getString(R.string.error_ws), Toast.LENGTH_LONG).show()
                })

        disposable.add(subscribe)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    private fun isValidPassword(password: String?): Boolean {
        return !password.isNullOrBlank() && password.length > 4
    }

    private fun isValidEmailId(email: String): Boolean {
        return Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        ).matcher(email).matches()
    }

    override fun onSuccess(serviceName: String, responseObject: Any) {
        val loginResponse = responseObject as LoginResponse
        Log.d(TAG, "Login response ${loginResponse.email}")
        Toast.makeText(this, getString(R.string.welcome), Toast.LENGTH_LONG).show()
        DialogUtil.dismissProgress()
        Handler().postDelayed({
            //Add dela to check login successful

            startActivity(Intent(this, UserListingActivity::class.java))
        }, 1000)
    }

    override fun onFail(serviceName: String, throwable: Throwable) {
    }

    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.btnGoNextScreen -> {
                startActivity(Intent(this, UserListingActivity::class.java))
            }
            R.id.btnLogin -> {
                textInputPassword.error = ""
                textInputEmail.error = ""
                if (isValidEmailId(edtEmail.text.toString().trim())) {  //Check email validation
                    if (isValidPassword(edtPassword.text.toString().trim())) { //Check password validation
                        if (NetworkUtils.checkInternetAndShowToast(this)) {
                            callLoginApi()  //Call login api
                        }
                    } else { //Invalid email
                        if (edtPassword.text.toString().length < 4) {
                            edtPassword.requestFocus()
                            textInputPassword.error = getString(R.string.invalid_password_length)
                        } else {
                            if (!isValidPassword(edtPassword.text.toString())) {
                                edtPassword.requestFocus()
                                textInputPassword.error =
                                    getString(R.string.invalid_password_length)
                            } else {
                                callLoginApi()
                            }
                        }

                    }
                } else {        //Invalid password
                    edtEmail.requestFocus()
                    textInputEmail.error = getString(R.string.invalid_email)
                }
            }
        }
    }

}
