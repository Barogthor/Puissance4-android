package com.tpandroid.esgi.puissance4

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.games.Games



class MultiActivity : AppCompatActivity() {

    private val RC_ACHIEVEMENT_UI = 9003

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.multi_layout)
    }

    fun goToGameBoardOffline(view: View?){
        var intent = Intent(this,PlayBoardActivity::class.java).apply {  }
        intent.putExtra("player1","Florian")
        intent.putExtra("player2","Invité")
        startActivity(intent)
    }

    fun goToGameboardOnline(view: View?){
        Games.getAchievementsClient(this, GoogleSignIn.getLastSignedInAccount(this)!!)
            .achievementsIntent
            .addOnSuccessListener { intent -> startActivityForResult(intent, RC_ACHIEVEMENT_UI) }
        Log.i("TEST_RESULT", "Open Achievements Client")
    }
}
