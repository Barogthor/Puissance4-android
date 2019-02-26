package com.tpandroid.esgi.puissance4

//import android.content.Intent
import android.app.Activity
import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
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
import android.support.v4.app.FragmentActivity
import android.widget.Button
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.games.Games

import com.tpandroid.esgi.puissance4.Game.Cache

class MainActivity : AppCompatActivity() {

    /* Request code used to invoke sign in user interactions. */
    private val RC_SIGN_IN = 9001

    // Client used to sign in with Google APIs
    private var mGoogleSignInClient: GoogleSignInClient? = null

    var database = FirebaseDatabase.getInstance()
    var myRef = database.getReference("message")

    // tag for debug logging
    private val TEST_RESULT = "TEST_RESULT"

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    var account = GoogleSignInAccount.createDefault()

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_layout)

        val cache = Cache(cacheDir)

        cache.setScore(1, Pair(5, 5))

        Log.i("cacheuu", cache.getScore(1).toString())


        startSignInIntent()

    }

    private fun startSignInIntent() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN)
            .requestServerAuthCode("839161475425-40m7ffku0dsd3723dv3ph13njuuplosq.apps.googleusercontent.com")
            .requestProfile()
            .build()

        val signInClient = GoogleSignIn.getClient(this, gso)
        val intent = signInClient.signInIntent
        startActivityForResult(intent, RC_SIGN_IN)
    }

    fun goToSolo(view: View?){
        Log.i(TEST_RESULT, "Is Signed In" + isSignedIn())
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

        if(isSignedIn())
        {
            var player = GoogleSignIn.getLastSignedInAccount(this)!!
            Games.getAchievementsClient(this, player).unlock(getString(R.string.achievement_test_achievement))
            Log.i(TEST_RESULT, "Achievement Unlock")
        }
    }

    private fun isSignedIn(): Boolean {
        return GoogleSignIn.getLastSignedInAccount(this) != null
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            var task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)

        }
    }

    private fun handleSignInResult(completedTask:Task<GoogleSignInAccount>) {
        try
        {
            val account = completedTask.getResult(ApiException::class.java)
        }
        catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TEST_RESULT, "signInResult:failed code=" + e.getStatusCode())
        }
    }
}
