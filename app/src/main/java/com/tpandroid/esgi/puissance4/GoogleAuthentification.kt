package com.tpandroid.esgi.puissance4

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

class GoogleAuthentification{
    private val TEST_RESULT = "TEST_RESULT"
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN)
        .requestServerAuthCode("839161475425-40m7ffku0dsd3723dv3ph13njuuplosq.apps.googleusercontent.com")
        .requestProfile()
        .build()

    val RC_SIGN_IN = 9001

    private lateinit var activity: AppCompatActivity

    constructor(activity: AppCompatActivity){
        this.activity = activity
    }

    fun isSignedIn(): Boolean {
        return GoogleSignIn.getLastSignedInAccount(activity) != null
    }

    fun startSignInIntent() {
        val signInClient = GoogleSignIn.getClient(activity, gso)

        val intent = signInClient.signInIntent
        activity.startActivityForResult(intent, RC_SIGN_IN)
    }

    fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {

        try
        {
            val account = completedTask.getResult(ApiException::class.java)
            Log.i(TEST_RESULT,"YOU ARE CONNECTED")
        }
        catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TEST_RESULT, "signInResult:failed code=" + e.getStatusCode())

        }
    }

}
