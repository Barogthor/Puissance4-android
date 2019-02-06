package com.tpandroid.esgi.puissance4

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class ProfilActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profil_layout)
    }

    fun goToScore(view: View?){
        var intent = Intent(this,ScoreActivity::class.java).apply {  }
        startActivity(intent)
    }

    fun goToLeaderBoard(view: View?){
        var intent = Intent(this,LeaderboardActivity::class.java).apply {  }
        startActivity(intent)
    }
}
