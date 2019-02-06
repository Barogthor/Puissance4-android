package com.tpandroid.esgi.puissance4

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class PlayBoardActivity : AppCompatActivity() {

    private var scoreP1: TextView? = null
    private var scoreP2: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.plateau_layout)

        val player1 = intent.getStringExtra("player1")
        val player2 = intent.getStringExtra("player2")

        if(intent.hasExtra("difficulte")) {
            val difficulte: Difficulte = intent.getSerializableExtra("difficulte") as Difficulte
            println("D:$difficulte")
        }
        val viewP1 = findViewById<TextView>(R.id.NameJ1)
        val viewP2 = findViewById<TextView>(R.id.NameJ2)
        scoreP1 = findViewById(R.id.streakJ1)
        scoreP2 = findViewById(R.id.streakJ2)

        viewP1.text = player1
        viewP2.text = player2

    }
    
}
