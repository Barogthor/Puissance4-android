package com.tpandroid.esgi.puissance4

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class SoloActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.solo_layout)
    }


    fun playEasy(view: View?){
        var intent = Intent(this,PlayBoardActivity::class.java).apply {  }
        intent.putExtra("difficulte",Difficulte.EASY)
        intent.putExtra("player1","Florian")
        intent.putExtra("player2","IA Facile")
        startActivity(intent)
    }

    fun playNormal(view: View?){
        var intent = Intent(this,PlayBoardActivity::class.java).apply {  }
        intent.putExtra("difficulte",Difficulte.NORMAL)
        intent.putExtra("player1","Florian")
        intent.putExtra("player2","IA Normal")
        startActivity(intent)
    }

    fun playHard(view: View?){
        var intent = Intent(this,PlayBoardActivity::class.java).apply {  }
        intent.putExtra("difficulte",Difficulte.HARD)
        intent.putExtra("player1","Florian")
        intent.putExtra("player2","IA Difficile")
        startActivity(intent)
    }

}
