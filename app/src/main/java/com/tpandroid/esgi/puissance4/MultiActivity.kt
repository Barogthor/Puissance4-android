package com.tpandroid.esgi.puissance4

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MultiActivity : AppCompatActivity() {

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

    }
}
