package com.tpandroid.esgi.puissance4.Game

import android.util.Log
import java.lang.Math.ceil
import kotlin.math.log
import kotlin.math.roundToInt

abstract class Game(open val board : Board) {
    abstract fun play(index : Int) : Board.Token?
}

class OnlineGame(override val board : Board) : Game(board) {
    override fun play(index : Int) : Board.Token? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class Multi(override val board : Board) : Game(board) {
    var player = Board.Token.Player1
    val playerSwitcher = mapOf(
        Pair(Board.Token.Player2, Board.Token.Player1),
        Pair(Board.Token.Player1, Board.Token.Player2)
    )

    override fun play(index : Int) : Board.Token? {
        return if(board.insert(index, player)) {
            player = playerSwitcher[player]!!
            if(board.victory() == null) {
                null
            } else {
                playerSwitcher[player]!!
            }
        } else {
            null
        }
    }
}

class SoloAi(override val board : Board, private val complexity : Int, private val algo : Minimax) : Game(board) {
    val player = Board.Token.Player1
    val ai = Board.Token.Player2
    var turn = 0

    override fun play(index : Int) : Board.Token? {
        return if(board.insert(index, player)) {
            turn += 1
            if(board.victory() == null) {
                val intention = algo.minimax(complexity + log4(turn), board)
                board.insert(intention, ai)

                if(board.victory() == null) { null } else { ai }
            } else {
                player
            }
        } else {
            null
        }
    }

    private fun log4(x : Int) : Int {
        return ceil(log(x.toDouble(), 4.0)).roundToInt()
    }
}
