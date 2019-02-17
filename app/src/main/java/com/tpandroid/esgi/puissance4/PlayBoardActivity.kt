package com.tpandroid.esgi.puissance4

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import android.widget.TextView
import com.tpandroid.esgi.puissance4.Game.*
import java.util.*

class PlayBoardActivity : AppCompatActivity() {

    private var scoreP1: TextView? = null
    private var scoreP2: TextView? = null
    private var actual : Board.Token = Board.Token.Player1
    private val switchPlayer = mapOf(
        Pair(Board.Token.Player1, Board.Token.Player2),
        Pair(Board.Token.Player2, Board.Token.Player1)
    )

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

        val grid = findViewById<GridLayout>(R.id.playgrid)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            grid.getChildAt(0).background = resources.getDrawable(R.drawable.pawn_yellow, this.theme)
        } else {
            grid.getChildAt(0).background = resources.getDrawable(R.drawable.pawn_yellow)
        }

        val subject = Minimax(arrayOf(CenterAgent(), CountAgent()), Board.Token.Player2, Board.Token.Player1)
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

        val complexity = 7

        // val game = SoloAi(board, complexity, subject)
        val game = Multi(board)

        repeat(6) { i ->
            repeat(7) { j ->
                val index = i + j * 6
                grid.getChildAt(index).isClickable = true
                grid.getChildAt(index).setOnClickListener {
                    val winner = game.play(j)
                    if(winner == null) {
                        board.draw()
                    } else {
                        val intent = Intent(this,ProfilActivity::class.java).apply {  }
                        startActivity(intent)
                    }
                }
            }
        }

        board.draw()
    }
}
