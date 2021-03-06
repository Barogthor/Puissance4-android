package com.tpandroid.esgi.puissance4

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import android.widget.TextView
import com.tpandroid.esgi.puissance4.Game.*
import com.tpandroid.esgi.puissance4.firebase.Score
import java.util.*

class PlayBoardActivity : AppCompatActivity() {

    private var scoreP1: TextView? = null
    private var scoreP2: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.plateau_layout)

        val player1 = intent.getStringExtra("player1")
        val player2 = intent.getStringExtra("player2")

        val viewP1 = findViewById<TextView>(R.id.NameJ1)
        val viewP2 = findViewById<TextView>(R.id.NameJ2)

        scoreP1 = findViewById(R.id.streakJ1)
        scoreP2 = findViewById(R.id.streakJ2)

        viewP1.text = player1
        viewP2.text = player2

        val grid = findViewById<GridLayout>(R.id.playgrid)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            grid.getChildAt(0).background = resources.getDrawable(R.drawable.pawn_yellow, this.theme)
        } else {
            grid.getChildAt(0).background = resources.getDrawable(R.drawable.pawn_yellow)
        }

        val victories = arrayOf(
            HorizontalWin(),
            VerticalWin(),
            LeftDiagonalWin(),
            RightDiagonalWin()
        )

        val gravity = NewtonGravity()
        val pawns = mapOf(
            Pair(Board.Token.Empty, resources.getDrawable(R.drawable.back)),
            Pair(Board.Token.Player1, resources.getDrawable(R.drawable.pawn_yellow)),
            Pair(Board.Token.Player2, resources.getDrawable(R.drawable.pawn_red))
        )
        val displayer = GridLayoutDisplayer(grid, pawns)
        val board = Board(gravity, victories, displayer)


        val game = if (intent.getStringExtra("type") == "ai") {
                     aiGame(board, intent.getIntExtra("complexity", 5))
                   }
                   else {
                     playerGame(board)
                   }

        repeat(6) { i ->
            repeat(7) { j ->
                val index = i + j * 6
                grid.getChildAt(index).isClickable = true
                grid.getChildAt(index).setOnClickListener {
                    val winner = game.play(j)
                    board.draw()
                    if(winner != null) {
                        val intent = Intent(this,ScoreActivity::class.java).apply {  }
                        intent.putExtra("victory",winner==Board.Token.Player1)
                        if(this.intent.hasExtra("complexity"))
                            intent.putExtra("complexity",this.intent.getIntExtra("complexity",5))
                        startActivity(intent)
                    }
                }
            }
        }

        board.draw()
    }

    fun playerGame(board : Board) : Game {
        val game = Multi(board)

        return game
    }

    fun aiGame(board : Board, complexity : Int) : Game {
        val subject = Minimax(
            arrayOf(
                CenterAgent(),
                CountAgent(),
                RandomAgent()),
            Board.Token.Player2,
            Board.Token.Player1
        )

        return SoloAi(board, complexity, subject)
    }
}
