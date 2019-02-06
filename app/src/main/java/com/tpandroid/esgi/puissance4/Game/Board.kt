package com.tpandroid.esgi.puissance4.Game

import java.util.*

class Board(
    private val gravity : Gravitable,
    private val win_conditions : Array<Winable>,
    val board : Array<Token> = Array(6 * 7) { Token.Empty }
) : Cloneable {
    enum class Token {
        Empty,
        Player1,
        Player2
    }

    fun insert(position : Int, piece : Token) : Boolean {
        assert(position in 0..6)

        if(board[position] != Token.Empty) { return false }

        board[position] = piece
        gravity()

        return true
    }

    private fun gravity() {
        gravity.fall(board, 6, 7)
    }

    fun victory() : Winable.Tokens? {
        win_conditions.forEach {
            if(it.win(board, 6, 7)) {
                return it.token()
            }
        }

        return null
    }

    public override fun clone(): Any {
        return Board(gravity, win_conditions, board.clone())
    }

    fun scoring(agents : Array<ScoreAgent>, current : Board.Token, enemy: Board.Token) : Int {
        return agents.fold(0) { acc, scoreAgent ->
            acc + scoreAgent.calculate(board, 6, 7, current)
        } - agents.fold(0) { acc, scoreAgent ->
            acc + scoreAgent.calculate(board, 6, 7, enemy)
        }
    }

    override fun toString() : String {
        val builder = StringBuilder();

        builder.append("----------\n")
        repeat(6) { i ->
            repeat(7) { j ->
                val piece = board[i * 7 + j]
                var value = "."

                if(piece == Token.Player1) { value = "${"\u001B[32m"}X${"\u001B[0m"}" }
                if(piece == Token.Player2) { value = "${"\u001B[36m"}O${"\u001B[0m"}" }

                builder.append("| ${value} ")
            }
            builder.append("\n----------\n")
        }

        builder.append("\n\n= 0 | 1 | 2 | 3 | 4 | 5 | 6 |")

        return builder.toString()
    }
}
