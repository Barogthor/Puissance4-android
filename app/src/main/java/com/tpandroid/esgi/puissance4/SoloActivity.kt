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
        val intent = Intent(this,PlayBoardActivity::class.java).apply {  }
        intent.putExtra("player1","Florian")
        intent.putExtra("player2","IA Facile")

        intent.putExtra("type", "ai")
        intent.putExtra("complexity", 2)

        startActivity(intent)
    }

    fun playNormal(view: View?){
        val intent = Intent(this,PlayBoardActivity::class.java).apply {  }
        intent.putExtra("player1","Florian")
        intent.putExtra("player2","IA Normal")

        intent.putExtra("type", "ai")
        intent.putExtra("complexity", 4)

        startActivity(intent)
    }

    fun playHard(view: View?){
        val intent = Intent(this,PlayBoardActivity::class.java).apply {  }
        intent.putExtra("player1","Florian")
        intent.putExtra("player2","IA Difficile")

        intent.putExtra("type", "ai")
        intent.putExtra("complexity", 5)

        startActivity(intent)
    }

}
