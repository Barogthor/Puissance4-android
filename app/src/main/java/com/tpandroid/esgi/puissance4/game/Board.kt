package com.tpandroid.esgi.puissance4.game

class Board(private val gravity : Gravitable, private val win_conditions : Array<Winable>) {
    enum class Token {
        Empty,
        Player1,
        Player2
    }

    private val board = Array(6 * 7) { Token.Empty }

    fun insert(position : Int, piece : Token) : Boolean {
        assert(position in 0..6)

        if(board[position] != Token.Empty) { return false; }

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

    override fun toString() : String {
        val builder = StringBuilder();

        builder.append("----------\n")
        repeat(6) { i ->
            repeat(7) { j ->
                builder.append("| ${board[i * 7 + j]} ")
            }
            builder.append("\n----------\n")
        }

        return builder.toString()
    }
}
