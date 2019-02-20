package com.tpandroid.esgi.puissance4

import android.content.Intent
import android.app.Activity
import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes
import com.google.android.gms.auth.api.signin.GoogleSignInApi
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
//import android.support.test.orchestrator.junit.BundleJUnitUtils.getResult
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
//import org.junit.experimental.results.ResultMatchers.isSuccessful
import com.google.android.gms.tasks.Task
import android.support.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener
import android.content.Intent
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInResult










class MainActivity : AppCompatActivity() {

    /* Request code used to invoke sign in user interactions. */
    private val RC_SIGN_IN = 0

    var database = FirebaseDatabase.getInstance()
    var myRef = database.getReference("message")

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    // Configure Google Sign In
    val signInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN)
    var signInOpt = signInOptions.build()

    var gso = signInOptions.requestServerAuthCode("839161475425-40m7ffku0dsd3723dv3ph13njuuplosq.apps.googleusercontent.com")
        .build()

    var account = GoogleSignInAccount.createDefault()

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_layout)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Obtain the FirebaseAnalytics instance.
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)

        //Login Events
        val bundle = Bundle()
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.LOGIN, bundle)

        val ctl = GoogleSignIn.getClient(this, gso)
        Log.i("TEST_RESULT_CLIENT :", ctl.toString())

        Log.i("TEST_RESULT :", "Blablabla")
        if(isSignedIn()) {
            signInSilently()
        } else {
            Log.i("TEST_RESULT_ISSIGNIN :", "Is Not Sign In")
            startSignInIntent()
        }

        //Unlock Achievements
        //bundle.putString(FirebaseAnalytics.Param.ACHIEVEMENT_ID, achievementId);
        //firebaseAnalytics.logEvent(FirebaseAnalytics.Event.UNLOCK_ACHIEVEMENT, bundle);

    }

    fun goToSolo(view: View?){
        var intent = Intent(this,SoloActivity::class.java).apply {  }
        startActivity(intent)
    }

    fun goToMulti(view: View?){
        var intent = Intent(this,MultiActivity::class.java).apply {  }
        startActivity(intent)
    }

    fun goToProfil(view: View?){
        var intent = Intent(this,ProfilActivity::class.java).apply {  }
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        Log.i("TEST_RESULT :", "Blablabla22222")
        var signInClient = GoogleSignIn.getClient(this,
            gso)
        signInClient.silentSignIn()
        /*if(isSignedIn())
            signInSilently()
        else {
            Log.i("TEST_RESULT_ISSIGNIN :", "Is Not Sign In")
            startSignInIntent()
        }*/
    }

    private fun isSignedIn(): Boolean {
        return GoogleSignIn.getLastSignedInAccount(this) != null
    }

    private fun signInSilently() {
        var signInClient = GoogleSignIn.getClient(this, gso)
        signInClient.silentSignIn().addOnCompleteListener(this
        ) { task ->
            if (task.isSuccessful) {
                // The signed in account is stored in the task's result.
                val signedInAccount = task.result
                Log.i("TEST_RESULT success :", signedInAccount.toString())
            } else {
                // Player will need to sign-in explicitly using via UI
                Log.i("TEST_RESULT failure :", task.exception.toString())
                startSignInIntent()
            }
        }
    }

    private fun startSignInIntent() {
        val signInClient = GoogleSignIn.getClient(
            this,
            gso
        )
        val intent = signInClient.signInIntent
        startActivityForResult(intent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            if (result.isSuccess) {
                // The signed in account is stored in the result.
                val signedInAccount = result.signInAccount
            } else {
                var message = result.status.statusMessage
                if (message == null || message.isEmpty()) {
                    message = getString(R.string.project_id)
                }
                AlertDialog.Builder(this).setMessage(message)
                    .setNeutralButton(android.R.string.ok, null).show()
            }
        }
    }
}
